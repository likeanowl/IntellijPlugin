/*
 * PerfomanceMetricsPlugin - see how IntellijIdea improving your performance!
 * Copyright 2016 Svitkov Sergey
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.likeanowl.ProductivityMetricsPlugin.handlers;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import io.github.likeanowl.ProductivityMetricsPlugin.state.PluginState;
import org.jetbrains.annotations.NotNull;

import static java.lang.System.currentTimeMillis;

public final class CountingHandler extends TypedHandlerDelegate {
	public Result beforeCharTyped(char c, final @NotNull Project project, final @NotNull Editor editor, final PsiFile file
			, final FileType fileType) {
		if (firstTime == 0) firstTime = currentTimeMillis();
		return Result.CONTINUE;
	}

	@Override
	public Result charTyped(char c, final @NotNull Project project, final @NotNull Editor editor
			, final PsiFile file) {
		final PluginState pluginState = PluginState.getInstance();
		final Document currentDocument = editor.getDocument();
		final VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(currentDocument);
		assert virtualFile != null;
		final String openedFileName = virtualFile.getName();
		ApplicationManager.getApplication().assertIsDispatchThread();
		pluginState.increment(openedFileName);
		firstTime = currentTimeMillis() - firstTime;
		if (firstTime > 0 && firstTime < 1000) {
			pluginState.incrementTypingTime(firstTime);
			firstTime = currentTimeMillis();
		} else firstTime = 0;
		return Result.CONTINUE;
	}

	long firstTime = 0;
}
