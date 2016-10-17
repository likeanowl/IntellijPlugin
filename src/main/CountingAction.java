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
    private final KeyPressedHandler keyPressedHandler = new KeyPressedHandler();
    private final EditorActionManager actionManager = EditorActionManager.getInstance();
    private final TypedAction typedAction = actionManager.getTypedAction();

    public CountingAction() {
        super("Counting");
        typedAction.setupHandler(keyPressedHandler);
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Messages.showInfoMessage("Symbols typed: " + keyPressedHandler.getSymbolsTyped(), "Symbols typed");
    }
}
