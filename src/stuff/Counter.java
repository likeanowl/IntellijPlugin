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

package stuff;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@State(name = "Counter",
        storages = {
                @Storage(id = "dir"
		                , file = "counter.xml"
		                , scheme = StorageScheme.DIRECTORY_BASED)
        })
public class Counter implements ApplicationComponent, PersistentStateComponent<Counter.CounterState> {

	public static class CounterState {
		public CounterState() {}

		public int foo;
	}

    @Nullable
    @Override
    public CounterState getState() {
        return counterState;
    }

    @Override
    public void loadState(CounterState state) {
        this.counterState = state;
    }

    public static Counter getInstance() {
	    return ServiceManager.getService(Counter.class);
    }

    public void increment () {
        counterState.foo++;
    }

    public int getTypedSymbolsCount() {
        return counterState.foo;
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

	public CounterState counterState = new CounterState();
}
