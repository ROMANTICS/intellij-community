/*
 * Copyright 2000-2015 JetBrains s.r.o.
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
package com.intellij.openapi.diff.impl.patch.formove;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.FilePath;
import com.intellij.openapi.vcs.VcsException;
import com.intellij.openapi.vcs.changes.Change;
import com.intellij.openapi.vcs.changes.ContentRevision;
import com.intellij.openapi.vcs.changes.LocalChangeList;
import com.intellij.openapi.vcs.changes.ui.RollbackChangesDialog;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

public class UndoApplyPatchDialog extends RollbackChangesDialog {


  private static final Logger LOG = Logger.getInstance(UndoApplyPatchDialog.class);

  private UndoApplyPatchDialog(@Nullable Project project, @NotNull List<Change> changes) {
    super(project, ContainerUtil.<LocalChangeList>emptyList(), changes, true, null, false);
    myBrowser.setChangesToDisplay(changes);
    setTitle("Undo Applying Patch Action for Selected Files");
    setOKButtonText("Undo & Revert Changes");
    init();
  }

  @Override
  protected void doOKAction() {
    processDoNotAskOnOk(OK_EXIT_CODE);

    if (getOKAction().isEnabled()) {
      close(OK_EXIT_CODE);
    }

    Collection<Change> selectedToRevert = myBrowser.getChangesIncludedInAllLists();
    for (Change change : selectedToRevert) {
      ContentRevision afterRevision = change.getAfterRevision();
      final VirtualFile vf = afterRevision != null ? afterRevision.getFile().getVirtualFile() : null;
      final ContentRevision beforeRevision = change.getBeforeRevision();
      if (vf != null && beforeRevision != null) {
        try {
          final String beforeContent = beforeRevision.getContent();
          if (beforeContent != null) {
            ApplicationManager.getApplication().runWriteAction(new Runnable() {
              @Override
              public void run() {
                try {
                  VfsUtil.saveText(vf, beforeContent);
                  FilePath beforeApplyFilePath = beforeRevision.getFile();
                  VirtualFile beforeApplyParent = beforeApplyFilePath.getVirtualFileParent();
                  if (beforeApplyParent == null || !beforeApplyParent.exists()) {
                    LOG.warn("File content was reverted, but couldn't move file " + vf.getName() + "to previous location");
                    return;
                  }
                  new PathsVerifier.MovedFileData(beforeApplyParent, vf, beforeApplyFilePath.getName()).doMove();
                }
                catch (IOException e) {
                  LOG.error("Couldn't revert file " + vf.getName());
                }
              }
            });
          }
        }
        catch (VcsException e) {
          LOG.error("Couldn't load before patch content for " + vf.getName());
        }
      }
      //added
      else if (vf != null && (myDeleteLocallyAddedFiles == null || myDeleteLocallyAddedFiles.isSelected())) {
        ApplicationManager.getApplication().runWriteAction(new Runnable() {
          @Override
          public void run() {
            try {
              vf.delete(this);
            }
            catch (IOException e) {
              LOG.warn("Couldn't delete newly created file " + vf.getName() + "\n Please, remove manually.");
            }
          }
        });
      }
      //deleted
    }
  }

  public static void undo(@NotNull Project project, @NotNull List<Change> changes) {
    UndoApplyPatchDialog dialog = new UndoApplyPatchDialog(project, changes);
    dialog.show();
  }
}
