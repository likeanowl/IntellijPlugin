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

package stuff;

import com.intellij.openapi.components.ProjectComponent;
import org.jetbrains.annotations.NotNull;

public class Counter implements ProjectComponent {
    private int typedSymbolsCount;

    public Counter() {
        this.typedSymbolsCount = 0;
    }

    public void increment () {
        typedSymbolsCount++;
    }

    public int getTypedSymbolsCount() {
        return typedSymbolsCount;
    }

    @Override
    public void projectOpened() {
        new Counter();
    }

    @Override
    public void projectClosed() {

    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {

    }

    @NotNull
    @Override
    public String getComponentName() {
        return String.valueOf(Counter.class);
    }
}
