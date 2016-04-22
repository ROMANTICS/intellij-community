/*
 * Copyright 2000-2016 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.intellij.openapi.editor.impl.view;

import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldRegion;
import com.intellij.openapi.editor.Inlay;
import com.intellij.openapi.editor.SoftWrap;
import com.intellij.openapi.editor.ex.FoldingModelEx;
import com.intellij.openapi.editor.ex.util.EditorUtil;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.editor.impl.SoftWrapModelImpl;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Iterator over visual line's fragments. Fragment's text has the same font and directionality. Collapsed fold regions are also represented
 * as fragments.
 */
class VisualLineFragmentsIterator implements Iterator<VisualLineFragmentsIterator.Fragment> {

  static Iterable<Fragment> create(final EditorView view, final int offset, final boolean beforeSoftWrap) {
    return new Iterable<Fragment>() {
      @Override
      public Iterator<Fragment> iterator() {
        return new VisualLineFragmentsIterator(view, offset, beforeSoftWrap, null);
      }
    };
  }
  
  /**
   * If <code>quickEvaluationListener</code> is provided, quick approximate iteration mode becomes enabled, listener will be invoked
   * if approximation will in fact be used during width calculation.
   */
  static Iterable<Fragment> create(final EditorView view, @NotNull final VisualLinesIterator visualLinesIterator, 
                                   @Nullable final Runnable quickEvaluationListener) {
    return new Iterable<Fragment>() {
      @Override
      public Iterator<Fragment> iterator() {
        return new VisualLineFragmentsIterator(view, visualLinesIterator, quickEvaluationListener);
      }
    };
  }
  
  private EditorView myView;
  private Document myDocument;
  private FoldRegion[] myRegions;
  private Fragment myFragment = new Fragment();
  private int myVisualLineStartOffset;
  private Runnable myQuickEvaluationListener;
  
  private int mySegmentStartOffset;
  private int mySegmentEndOffset;
  private int myCurrentFoldRegionIndex;
  private Iterator<LineLayout.VisualFragment> myFragmentIterator;
  private List<Inlay> myInlays;
  private int myCurrentInlayIndex;
  private boolean myIsInlay;
  private float myInlaysTotalWidth;
  private float myCurrentX;
  private int myCurrentVisualColumn;
  private LineLayout.VisualFragment myDelegate;
  private FoldRegion myFoldRegion;
  private int myCurrentStartLogicalLine;
  private int myCurrentEndLogicalLine;
  private int myNextWrapOffset;

  private VisualLineFragmentsIterator(EditorView view, int offset, boolean beforeSoftWrap, @Nullable Runnable quickEvaluationListener) {
    EditorImpl editor = view.getEditor();
    int visualLineStartOffset = EditorUtil.getNotFoldedLineStartOffset(editor, offset);
    
    SoftWrapModelImpl softWrapModel = editor.getSoftWrapModel();
    List<? extends SoftWrap> softWraps = softWrapModel.getRegisteredSoftWraps();
    int currentOrPrevWrapIndex = softWrapModel.getSoftWrapIndex(offset);
    if (currentOrPrevWrapIndex < 0) {
      currentOrPrevWrapIndex = - currentOrPrevWrapIndex - 2;
    }
    else if (beforeSoftWrap) {
      currentOrPrevWrapIndex--;
    }
    SoftWrap currentOrPrevWrap = currentOrPrevWrapIndex < 0 || currentOrPrevWrapIndex >= softWraps.size() ? null :
                                 softWraps.get(currentOrPrevWrapIndex);
    if (currentOrPrevWrap != null && currentOrPrevWrap.getStart() > visualLineStartOffset) {
      visualLineStartOffset = currentOrPrevWrap.getStart();
    }
    
    int nextFoldingIndex = editor.getFoldingModel().getLastCollapsedRegionBefore(visualLineStartOffset) + 1;
    
    init(view, 
         visualLineStartOffset, 
         editor.getDocument().getLineNumber(visualLineStartOffset), 
         currentOrPrevWrapIndex, 
         nextFoldingIndex, 
         quickEvaluationListener);
  }

  public VisualLineFragmentsIterator(@NotNull EditorView view, @NotNull VisualLinesIterator visualLinesIterator, 
                                     @Nullable Runnable quickEvaluationListener) {
    assert !visualLinesIterator.atEnd();
    init(view, 
         visualLinesIterator.getVisualLineStartOffset(), 
         visualLinesIterator.getStartLogicalLine(), 
         visualLinesIterator.getStartOrPrevWrapIndex(), 
         visualLinesIterator.getStartFoldingIndex(),
         quickEvaluationListener);
  }

  private void init(EditorView view, int startOffset, int startLogicalLine, int currentOrPrevWrapIndex, int nextFoldingIndex,
                    @Nullable Runnable quickEvaluationListener) {
    myQuickEvaluationListener = quickEvaluationListener;
    myView = view;
    EditorImpl editor = view.getEditor();
    myDocument = editor.getDocument();
    FoldingModelEx foldingModel = editor.getFoldingModel();
    FoldRegion[] regions = foldingModel.fetchTopLevel();
    myRegions = regions == null ? FoldRegion.EMPTY_ARRAY : regions;
    SoftWrapModelImpl softWrapModel = editor.getSoftWrapModel();
    List<? extends SoftWrap> softWraps = softWrapModel.getRegisteredSoftWraps();
    SoftWrap currentOrPrevWrap = currentOrPrevWrapIndex < 0 || currentOrPrevWrapIndex >= softWraps.size() ? null :
                                 softWraps.get(currentOrPrevWrapIndex);
    SoftWrap followingWrap = (currentOrPrevWrapIndex + 1) < 0 || (currentOrPrevWrapIndex + 1) >= softWraps.size() ? null :
                             softWraps.get(currentOrPrevWrapIndex + 1);

    myVisualLineStartOffset = mySegmentStartOffset = startOffset;

    myCurrentFoldRegionIndex = nextFoldingIndex;
    myCurrentEndLogicalLine = startLogicalLine;
    myCurrentX = myView.getInsets().left;
    if (mySegmentStartOffset == 0) {
      myCurrentX += myView.getPrefixTextWidthInPixels();
    }
    else if (currentOrPrevWrap != null && mySegmentStartOffset == currentOrPrevWrap.getStart()) {
      myCurrentX += currentOrPrevWrap.getIndentInPixels();
      myCurrentVisualColumn = currentOrPrevWrap.getIndentInColumns();
    }
    myNextWrapOffset = followingWrap == null ? Integer.MAX_VALUE : followingWrap.getStart();
    setFragmentIterator();
  }

  private void setFragmentIterator() {
    mySegmentEndOffset = getCurrentFoldRegionStartOffset();
    if (mySegmentEndOffset > mySegmentStartOffset) {
      int line = myDocument.getLineNumber(mySegmentStartOffset);
      mySegmentEndOffset = Math.min(myNextWrapOffset, Math.min(mySegmentEndOffset, myDocument.getLineEndOffset(line)));
      int lineStartOffset = myDocument.getLineStartOffset(line);
      myFragmentIterator = myView.getTextLayoutCache().getLineLayout(line).
        getFragmentsInVisualOrder(myView, line, myCurrentX, myCurrentVisualColumn,
                                  mySegmentStartOffset - lineStartOffset, mySegmentEndOffset - lineStartOffset,
                                  myQuickEvaluationListener);
    }
  }

  private int getCurrentFoldRegionStartOffset() {
    if (myCurrentFoldRegionIndex >= myRegions.length) {
      return Integer.MAX_VALUE;
    }
    int nextFoldingOffset = myRegions[myCurrentFoldRegionIndex].getStartOffset();
    return nextFoldingOffset < myNextWrapOffset ? nextFoldingOffset : Integer.MAX_VALUE;
  }
  
  private float getFoldRegionWidthInPixels(FoldRegion foldRegion) {
    return myView.getFoldRegionLayout(foldRegion).getWidth();
  }

  private static int getFoldRegionWidthInColumns(FoldRegion foldRegion) {
    return foldRegion.getPlaceholderText().length();
  }

  private int[] getVisualColumnForXInsideFoldRegion(FoldRegion foldRegion, float x) {
    LineLayout layout = myView.getFoldRegionLayout(foldRegion);
    for (LineLayout.VisualFragment fragment : layout.getFragmentsInVisualOrder(0)) {
      if (x <= fragment.getEndX()) {
        return fragment.xToVisualColumn(x);
      }
    }
    return new int[] {getFoldRegionWidthInColumns(foldRegion), 1};
  }

  private float getXForVisualColumnInsideFoldRegion(FoldRegion foldRegion, int column) { 
    LineLayout layout = myView.getFoldRegionLayout(foldRegion);
    for (LineLayout.VisualFragment fragment : layout.getFragmentsInVisualOrder(0)) {
      if (column <= fragment.getEndVisualColumn()) {
        return fragment.visualColumnToX(column);
      }
    }
    return getFoldRegionWidthInPixels(foldRegion);
  }
  
  // offset is absolute
  private float getXForOffsetInsideFoldRegion(FoldRegion foldRegion, int offset) {
    return offset < foldRegion.getEndOffset() ? 0 : getFoldRegionWidthInPixels(foldRegion);
  }

  @Override
  public boolean hasNext() {
    return mySegmentStartOffset == getCurrentFoldRegionStartOffset()
           || myInlays != null && (myCurrentInlayIndex < myInlays.size() ||
                                   myIsInlay && myInlays.get(myCurrentInlayIndex - 1).getOffset() < myDelegate.getMaxOffset() + myDocument.getLineStartOffset(myCurrentStartLogicalLine))
           || myFragmentIterator.hasNext();
  }

  @Override
  public Fragment next() {
    if (!hasNext()) throw new NoSuchElementException();
    if (mySegmentStartOffset == getCurrentFoldRegionStartOffset()) {
      myDelegate = null;
      myInlays = null;
      myCurrentInlayIndex = 0;
      myIsInlay = false;
      myFoldRegion = myRegions[myCurrentFoldRegionIndex];
      assert myFoldRegion.isValid();
      
      mySegmentStartOffset = myFoldRegion.getEndOffset();
      myCurrentX += getFoldRegionWidthInPixels(myFoldRegion);
      myCurrentVisualColumn += getFoldRegionWidthInColumns(myFoldRegion);
      myCurrentStartLogicalLine = myCurrentEndLogicalLine;
      myCurrentEndLogicalLine = myDocument.getLineNumber(mySegmentStartOffset);
      myCurrentFoldRegionIndex++;
      setFragmentIterator();
    }
    else {
      if (myInlays != null) {
        if (!myIsInlay && myCurrentInlayIndex < myInlays.size() ||
            myIsInlay && myInlays.get(myCurrentInlayIndex).getOffset() < myDelegate.getMaxOffset() + myDocument.getLineStartOffset(myCurrentStartLogicalLine)) {
          if (myIsInlay) myCurrentInlayIndex++;
          myIsInlay = !myIsInlay;
        }
        else {
          for (Inlay inlay : myInlays) {
            myInlaysTotalWidth += inlay.getWidthInPixels();
          }
          myInlays = null;
        }
      }
      if (myInlays == null) {
        myDelegate = myFragmentIterator.next();
        myFoldRegion = null;
        myCurrentX = myDelegate.getEndX();
        myCurrentVisualColumn = myDelegate.getEndVisualColumn();
        myCurrentStartLogicalLine = myCurrentEndLogicalLine;
        int lineStartOffset = myDocument.getLineStartOffset(myCurrentStartLogicalLine);
        myInlays = myView.getEditor().getInlayModel().getInlineElementsInRange(myDelegate.getMinOffset() + lineStartOffset, myDelegate.getMaxOffset() + lineStartOffset);
        myCurrentInlayIndex = 0;
        myIsInlay = false;
        if (!myFragmentIterator.hasNext()) {
          mySegmentStartOffset = mySegmentEndOffset;
        }
      }
    }
    return myFragment;
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException();
  }

  class Fragment {    
    int getVisualLineStartOffset() {
      return myVisualLineStartOffset;
    }
    
    boolean isCollapsedFoldRegion() {
      return myDelegate == null;
    }
    
    int getMinLogicalColumn() {
      return myDelegate == null ? myView.offsetToLogicalPosition(myFoldRegion.getStartOffset()).column :
             myIsInlay ? myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex).getOffset()).column :
             myCurrentInlayIndex == 0 ? myDelegate.getMinLogicalColumn() :
             myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex - 1).getOffset()).column;
    }
    
    int getMaxLogicalColumn() {
      return myDelegate == null ? myView.offsetToLogicalPosition(myFoldRegion.getEndOffset()).column :
             myIsInlay ? myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex).getOffset()).column :
             myCurrentInlayIndex == myInlays.size() ? myDelegate.getMaxLogicalColumn() :
             myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex).getOffset()).column;
    }
    
    int getStartLogicalColumn() {
      return myDelegate == null ? myView.offsetToLogicalPosition(myFoldRegion.getStartOffset()).column :
             myIsInlay ? myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex).getOffset()).column :
             myCurrentInlayIndex == 0 ? myDelegate.getStartLogicalColumn() :
             myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex - 1).getOffset()).column;
    }

    int getEndLogicalColumn() {
      return myDelegate == null ? myView.offsetToLogicalPosition(myFoldRegion.getEndOffset()).column :
             myIsInlay ? myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex).getOffset()).column :
             myCurrentInlayIndex == myInlays.size() ? myDelegate.getEndLogicalColumn() :
             myView.offsetToLogicalPosition(myInlays.get(myCurrentInlayIndex).getOffset()).column;
    }
    
    int getStartVisualColumn() {
      return myDelegate == null ? myCurrentVisualColumn - getFoldRegionWidthInColumns(myFoldRegion):
              myDelegate.logicalToVisualColumn(getStartLogicalColumn());
    }

    int getEndVisualColumn() {
      return myDelegate == null ? myCurrentVisualColumn :
              myDelegate.logicalToVisualColumn(getEndLogicalColumn());
    }
    
    int getStartLogicalLine() {
      return myCurrentStartLogicalLine;
    }

    int getEndLogicalLine() {
      return myCurrentEndLogicalLine;
    }
    
    float getStartX() {
      if (myDelegate == null) {
        return myInlaysTotalWidth + myCurrentX - getFoldRegionWidthInPixels(myFoldRegion);
      }
      return offsetToX(getMinOffset()) - (myIsInlay ? myInlays.get(myCurrentInlayIndex).getWidthInPixels() : 0);
    }

    float getEndX() {
      return offsetToX(getMaxOffset());
    }

    // column is expected to be between minLogicalColumn and maxLogicalColumn for this fragment
    int logicalToVisualColumn(int column) {
      return myDelegate == null ? myCurrentVisualColumn - getFoldRegionWidthInColumns(myFoldRegion) :
             myDelegate.logicalToVisualColumn(column);
    }

    // column is expected to be between startVisualColumn and endVisualColumn for this fragment
    int visualToLogicalColumn(int column) {
      return myDelegate == null ? (column == myCurrentVisualColumn ? getEndLogicalColumn() : getStartLogicalColumn()) :
             myDelegate.visualToLogicalColumn(column);
    }

    // returns array of two elements 
    // - first one is visual column, 
    // - second one is 1 if target location is closer to larger columns and 0 otherwise
    int[] xToVisualColumn(float x) {
      if (myDelegate == null) {
        int[] column = getVisualColumnForXInsideFoldRegion(myFoldRegion, x - getStartX());
        column[0] += getStartVisualColumn();
        return column;
      }
      if (myIsInlay) {
        boolean closerToStart = x < (getStartX() + getEndX()) / 2;
        return new int[]{getEndVisualColumn(), closerToStart ? 0 : 1};
      }
      x -= myInlaysTotalWidth;
      for (int i = 0; i < myCurrentInlayIndex; i++) x -= myInlays.get(i).getWidthInPixels();
      return myDelegate.xToVisualColumn(x);
    }

    float visualColumnToX(int column) {
      if (myDelegate == null) {
        return getStartX() +
               getXForVisualColumnInsideFoldRegion(myFoldRegion, column - getStartVisualColumn());
      }
      if (myIsInlay) {
        return getEndX();
      }
      float inlaysSize = 0;
      for (int i = 0; i < myCurrentInlayIndex; i++) {
        inlaysSize += myInlays.get(i).getWidthInPixels();
      }
      return myInlaysTotalWidth + inlaysSize + myDelegate.visualColumnToX(column);
    }

    // absolute
    int getStartOffset() {
      return myDelegate == null ? myFoldRegion.getStartOffset() :
             myIsInlay ? myInlays.get(myCurrentInlayIndex).getOffset() :
             myCurrentInlayIndex == 0 ? myDelegate.getStartOffset() + myDocument.getLineStartOffset(myCurrentStartLogicalLine) :
             myInlays.get(myCurrentInlayIndex - 1).getOffset();
    }

    // absolute
    int getEndOffset() {
      return myDelegate == null ? myFoldRegion.getEndOffset() :
             myIsInlay ? myInlays.get(myCurrentInlayIndex).getOffset() :
             myCurrentInlayIndex == myInlays.size() ? myDelegate.getEndOffset() + myDocument.getLineStartOffset(myCurrentStartLogicalLine) :
             myInlays.get(myCurrentInlayIndex).getOffset();
    }

    // absolute
    int getMinOffset() {
      return myDelegate == null ? myFoldRegion.getStartOffset() :
             myIsInlay ? myInlays.get(myCurrentInlayIndex).getOffset() :
             myCurrentInlayIndex == 0 ? myDelegate.getMinOffset() + myDocument.getLineStartOffset(myCurrentStartLogicalLine) :
             myInlays.get(myCurrentInlayIndex - 1).getOffset();
    }

    // absolute
    int getMaxOffset() {
      return myDelegate == null ? myFoldRegion.getEndOffset() :
             myIsInlay ? myInlays.get(myCurrentInlayIndex).getOffset() :
             myCurrentInlayIndex == myInlays.size() ? myDelegate.getMaxOffset() + myDocument.getLineStartOffset(myCurrentStartLogicalLine) :
             myInlays.get(myCurrentInlayIndex).getOffset();
    }

    // offset is absolute
    float offsetToX(int offset) {
      if (myDelegate == null) {
        return getStartX() + getXForOffsetInsideFoldRegion(myFoldRegion, offset);
      }
      float inlaysSize = 0;
      for (int i = 0; i < myCurrentInlayIndex + (myIsInlay ? 1 : 0); i++) {
        inlaysSize += myInlays.get(i).getWidthInPixels();
      }
      return myInlaysTotalWidth + inlaysSize + myDelegate.offsetToX(offset - myDocument.getLineStartOffset(myCurrentStartLogicalLine));
    }

    // offsets are absolute
    float offsetToX(float startX, int startOffset, int offset) {
      assert myDelegate != null;
      if (myIsInlay) return startX + myInlays.get(myCurrentInlayIndex).getWidthInPixels();
      int lineStartOffset = myDocument.getLineStartOffset(myCurrentStartLogicalLine);
      return myDelegate.offsetToX(startX, startOffset - lineStartOffset, offset - lineStartOffset);
    }
    
    boolean isRtl() {
      return myDelegate != null && myDelegate.isRtl();
    }
    
    FoldRegion getCurrentFoldRegion() {
      return myFoldRegion;
    }

    Inlay getCurrentInlay() {
      return myIsInlay ? myInlays.get(myCurrentInlayIndex) : null;
    }

    // columns are visual (relative to fragment's start)
    void draw(Graphics2D g, float x, float y, int startRelativeColumn, int endRelativeColumn) {
      if (myDelegate == null) {
        for (LineLayout.VisualFragment fragment : myView.getFoldRegionLayout(myFoldRegion).getFragmentsInVisualOrder(x)) {
          int fragmentStart = fragment.getStartVisualColumn();
          int fragmentEnd = fragment.getEndVisualColumn();
          if (fragmentStart < endRelativeColumn && fragmentEnd > startRelativeColumn) {
            fragment.draw(g, fragment.getStartX(), y, 
                          Math.max(0, startRelativeColumn - fragmentStart), Math.min(fragmentEnd, endRelativeColumn) - fragmentStart);
          }
        }
      }
      else if (!myIsInlay) {
        int columnShift = myCurrentInlayIndex == 0 ? 0 : myInlays.get(myCurrentInlayIndex - 1).getOffset() - myDelegate.getStartOffset() - myDocument.getLineStartOffset(myCurrentStartLogicalLine);
        myDelegate.draw(g, x, y, startRelativeColumn + columnShift, endRelativeColumn + columnShift);
      }
    }
  }
}
