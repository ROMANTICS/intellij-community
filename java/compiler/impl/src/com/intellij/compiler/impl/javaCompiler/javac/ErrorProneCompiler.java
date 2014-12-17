/*
 * Copyright 2000-2014 JetBrains s.r.o.
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
package com.intellij.compiler.impl.javaCompiler.javac;

import com.intellij.compiler.impl.javaCompiler.BackendCompiler;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.fileTypes.StdFileTypes;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.jps.model.java.compiler.JavaCompilers;

import java.util.Collections;
import java.util.Set;

/**
 * @author lambdamix
 */
public class ErrorProneCompiler implements BackendCompiler {
  private final Project myProject;

  public ErrorProneCompiler(Project project) {
    myProject = project;
  }
  @NotNull
  @Override
  public String getId() {
    return JavaCompilers.PWA_ID;
  }

  @NotNull
  @Override
  public String getPresentableName() {
    return "PWA Analysis";
  }

  @NotNull
  @Override
  public Configurable createConfigurable() {
    return new JavacConfigurable(JavacConfiguration.getOptions(myProject, JavacConfiguration.class));
  }

  @NotNull
  @Override
  public Set<FileType> getCompilableFileTypes() {
    return Collections.<FileType>singleton(StdFileTypes.JAVA);
  }
}
