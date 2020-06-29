# API Record Scanner
This application serves as a scanner that reads one API and displays the data in another API. 

Unfortunately the data when sent back to the API will not display the details but will show the correct quantity
of records that have been found. I am still work on a solution to the problem I am facing. 

The information from the first API has been successfully requested and output into an ArrayList.
The data can be viewed through one of the several System.out.Print functions that have been commented out. 

## Setup

### Requirements

- JDK 8 - available via [Oracle](https://www.oracle.com/java), or alternatives such as [OpenJDK](https://openjdk.java.net/)


### Building

This project makes use of [Lombok](https://projectlombok.org/). To get this project to build in InteliJ Idea you
will need to download the Lombok plugin and enable annotation processors. 
 - The Lombok plugin can be found under `Preferences -> Plugins` and then search for the 'Lombok' plugin. 
 You will need to restart your IDE to activate this plugin.
 - To enable annotation processing you need to tick the checkbox in the preferences. This can be found under
 `Preferences` by searching for `Annotation Processors`. If this does not display the setting, then you can find it
 manually under `Preferences -> Build, Execution, Deployment -> Compiler -> Annotation Processors`. 

This project is built using [Gradle](https://gradle.org). The Gradle wrapper has been included with the project
so Gradle does not need to be downloaded to build the project.

To build with the Gradle wrapper enter the following into a terminal
```zsh
./gradlew clean build
```

### Running

The application makes use of multiple configuration profiles which are switched out or overridden depending on the
environment that the application is to be run in. The environment to run the application in can be declared in a couple
of ways.

- In Intellij Idea Ultimate edition you can simply enter the name of the environment into the 'Active Profiles' text
box of your Spring Boot run configuration.
- If you have Intellij Idea community edition then you can manually add the parameter 'spring.profiles.active' and then
provide a profile such as 'dev' or 'prod'.
- If running from the command line then use the following command to start the application with the profile set.
```zsh
./gradlew bootRun --args ' --spring.profiles.active={profile}'
```
Replace {profile} with the desired profile.

e.g.
```
./gradlew bootRun --args ' --spring.profiles.active=dev'
```