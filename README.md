# PerfomanceMetricsPlugin
------------
Plugin for IntellijIdea IDE. See how IntellijIdea improving your performance!
# How to get it
------------
* Get sources.
* Run ''./gradlew build'' to perform initial build.
* Also you can set up run cofiguration in IntellijIdea IDE. To do this follow these steps below
	* Open 'Run' tab
	* Press 'Edit configurations' button
	* Add new gradle configuration
		* Gradle project: PerfomanceMetricsPlugin
		* Tasks: runIdea
		* (optional) Script parameters: --stacktrace
# Summary
------------
Features as is:
* Counting symbols typed manually

#Changelog
------------
* Alpha-release v.0.01:
	* Counting typed symbols
	* Saves data between IDE restarts
# License
------------
PerfomanceMetricsPlugin is licensed under the terms of the GNU Public license version 2.