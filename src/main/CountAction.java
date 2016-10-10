package main;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.wm.KeyEventProcessor;
import org.jetbrains.annotations.NotNull;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class CountAction extends AnAction implements KeyListener{
    private int symbolCounter = 0;

    public CountAction() {
        super("Just Plugin");
    }

    public void actionPerformed(AnActionEvent event) {
        Project project = event.getData(PlatformDataKeys.PROJECT);
        Messages.showMessageDialog(project, "You typed : " + symbolCounter, "Just Plugin", Messages.getInformationIcon());
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        char ch = keyEvent.getKeyChar();
        if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z' || ch >= '0' && ch <= '9') {
            symbolCounter++;
        } else if (keyEvent.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            symbolCounter--;
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
