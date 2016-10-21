/*
 * JustPlugin - let me show you that IDE is better than just text editor!
 * Copyright (C) 2016 Svitkov Sergey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package handlers;

import com.intellij.codeInsight.editorActions.TypedHandlerDelegate;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.NotNull;

public class CountingTypedHandler extends TypedHandlerDelegate {
    private int typedSymbols = 0;

    @Override
    public Result charTyped(char c, final Project project, final @NotNull Editor editor, @NotNull final PsiFile file) {
        ApplicationManager.getApplication().runReadAction((Runnable) () -> typedSymbols++);
        return Result.CONTINUE;
    }

    public int getTypedSymbols() {
        return typedSymbols;
    }
}
