/*
 * JustPlugin - let it show you that IDE is better than just text editor!
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

package actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.ui.Messages;
import stuff.Counter;

public class CountingAction  extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Counter counter = e.getProject().getComponent(Counter.class);
        if (counter != null) {
            Messages.showInfoMessage("Symbols typed: " + counter.getTypedSymbolsCount()
                                    + "\nTotal symbols: " + e.getData(CommonDataKeys.EDITOR)
                                    .getDocument().getTextLength(), "Just Plugin");
        } else {
            Messages.showErrorDialog("Somewhy counter component is null", "Just Plugin");
        }
    }
}
