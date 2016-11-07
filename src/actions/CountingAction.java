/*
 * Just plugin - see how IntellijIdea improving your performance!
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

package actions;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.Messages;
import stuff.Counter;

public class CountingAction  extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        Counter counter = Counter.getInstance();
        if (counter != null) {
	        String name = e.getData(PlatformDataKeys.VIRTUAL_FILE).getPresentableName();
            Messages.showInfoMessage("Symbols typed in file " + name + ": "
		            + counter.getTypedSymbolsCount(name), "Just Plugin");
        } else {
            Messages.showErrorDialog("Somewhy counter component is null", "Just Plugin");
        }
    }
}
