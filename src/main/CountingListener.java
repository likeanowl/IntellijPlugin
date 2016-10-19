package main;

import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

public class CountingListener implements DocumentListener {
    private int symbolsCount = 0;

    @Override
    public void beforeDocumentChange(DocumentEvent documentEvent) {

    }

    @Override
    public void documentChanged(DocumentEvent documentEvent) {
        int changeSize = documentEvent.getNewLength() - documentEvent.getOldLength();
        if (changeSize == 1)
            symbolsCount += 1;
        else if (changeSize < 0)
            symbolsCount += changeSize;
    }

    public int getSymbolsCount() {
        return symbolsCount;
    }
}
