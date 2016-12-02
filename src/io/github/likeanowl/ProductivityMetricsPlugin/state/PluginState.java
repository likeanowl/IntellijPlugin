/*
 * PerfomanceMetricsPlugin - see how IntellijIdea improving your performance!
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

package io.github.likeanowl.ProductivityMetricsPlugin.state;

import com.intellij.openapi.components.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Serialization state
 */
@State(name = "PluginState",
		storages = {
				@Storage(id = "dir"
						, file = "counter.xml"
						, scheme = StorageScheme.DIRECTORY_BASED)
		})
public final class PluginState implements ApplicationComponent, PersistentStateComponent<PluginState.InnerState> {

	/**
	 * Nested class for saving serialization state.
	 * Map is used for state saving for each class, existing in project.
	 * Key is filename in form: package_name.classname (for Java) and object is just count of typed symbols
	 */
	public static class InnerState {
		public InnerState() {
			totalState = new HashMap<>();
		}

		@NotNull
		public Map<String, Integer> totalState;
	}

	@Nullable
	@Override
	public InnerState getState() {
		return innerState;
	}

	@Override
	public void loadState(@NotNull InnerState state) {
		this.innerState = state;
	}

	@NotNull
	public static PluginState getInstance() {
		return ServiceManager.getService(PluginState.class);
	}

	/**
	 * Incrementing related to filename count of typed symbols
	 *
	 * @param fileName
	 */
	public void increment(@NotNull String fileName) {
		if (innerState.totalState.get(fileName) != null)
			innerState.totalState.put(fileName, innerState.totalState.get(fileName) + 1);
		else innerState.totalState.put(fileName, 1);
		if (currentSessionState.get(fileName) != null)
			currentSessionState.put(fileName, currentSessionState.get(fileName) + 1);
		else
			currentSessionState.put(fileName, 1);
	}

	/**
	 * Decrementing related to filename count of typed symbols
	 *
	 * @param fileName
	 */
	public void decrement(@NotNull String fileName) {
		final Map<String, Integer> stateMap = innerState.totalState;
		if (stateMap.get(fileName) != null)
			innerState.totalState.put(fileName, innerState.totalState.get(fileName) - 1);
		else innerState.totalState.put(fileName, 0);
		if (currentSessionState.get(fileName) != null)
			currentSessionState.put(fileName, currentSessionState.get(fileName) - 1);
		else
			currentSessionState.put(fileName, 0);
	}

	/**
	 * Returns typed symbols count related to specified file name
	 *
	 * @param fileName
	 * @return
	 */
	public int getSymbolsTypedCountTotal(@NotNull String fileName) {
		if (innerState.totalState.get(fileName) != null)
			return innerState.totalState.get(fileName);
		else
			return 0;
	}

	public int getSymbolsTypedCountThisSession(@NotNull String fileName) {
		if (currentSessionState.get(fileName) != null)
			return currentSessionState.get(fileName);
		else
			return 0;
	}

	public void incrementTypingTime(long time) {
		typingTime += time;
	}

	public long getTypingTime() {
		return typingTime;
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
		return String.valueOf(PluginState.class);
	}

	@NotNull
	public InnerState innerState = new InnerState();
	@NotNull
	private final Map<String, Integer> currentSessionState = new HashMap<>();
	private long typingTime = 0;
}
