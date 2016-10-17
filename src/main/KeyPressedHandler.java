package main;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.TypedActionHandler;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

public class KeyPressedHandler implements TypedActionHandler {
    private int textLength = 0;
    private int symbolsTyped = 0;

    @Override
    public void execute(@NotNull Editor editor, char charTyped, @NotNull DataContext dataContext) {
        final Document document = editor.getDocument();
        Project project = editor.getProject();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ApplicationManager.getApplication().runReadAction(this);
                textLength = document.getTextLength();
                symbolsTyped++;
            }
        };
    }

    public int getTextLength() {
        return textLength;
    }
    public int getSymbolsTyped() {
        return symbolsTyped;
    }
}
