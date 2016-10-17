package main;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.editor.actionSystem.EditorActionManager;
import com.intellij.openapi.editor.actionSystem.TypedAction;
import com.intellij.openapi.externalSystem.model.Key;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.KeyEventProcessor;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CountingAction extends AnAction {
    private final KeyPressedHandler keyPressedHandler;
    private int symbolsTyped;

    public CountingAction() {
        super("Counting");
        keyPressedHandler = new KeyPressedHandler();
        symbolsTyped = 0;
    }

    @Override
    public void update(@NotNull final AnActionEvent anActionEvent) {
        final EditorActionManager actionManager = EditorActionManager.getInstance();
        final TypedAction typedAction = actionManager.getTypedAction();
        typedAction.setupHandler(keyPressedHandler);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        this.symbolsTyped = keyPressedHandler.getSymbolsTyped();
        Messages.showInfoMessage("Symbols typed: " + symbolsTyped, "Symbols typed");
    }
}
