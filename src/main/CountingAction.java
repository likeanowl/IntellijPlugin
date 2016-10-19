package main;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.ui.Messages;

public class CountingAction extends AnAction {

    private CountingListener countingListener;

    public CountingAction() {
        super("Counting");
        countingListener = new CountingListener();
    }

    @Override
    public void actionPerformed(AnActionEvent anActionEvent) {
        Document document = anActionEvent.getRequiredData(CommonDataKeys.EDITOR).getDocument();
        document.addDocumentListener(countingListener);
        Messages.showInfoMessage("Symbols typed: " + countingListener.getSymbolsCount()
                + "\nTotal symbols count: " + document.getTextLength(), "Symbols typed");
    }
}
