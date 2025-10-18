# Empty Phrases 1.0.0

![Empty Phrases Logo](https://github.com/NeuralCortex/Empty_Phrases/blob/main/images/ep.png)

## Overview

Empty Phrases is a fun JavaFX application that generates random, politician-like phrases using word fragments from a database. Think of it as a playful tool to create amusing, empty rhetoric—perfect for a lighthearted take on political speech. This project is inspired by the 1985 *SFB Computerclub* TV show, where similar concepts were explored using BASIC on a Commodore C64. Watch the inspiration here: [SFB Computerclub 1985 Part 2](https://www.youtube.com/watch?v=jIzq_ws3wys&t=53s).

## Technology Used

This JavaFX project was developed using the Apache NetBeans 27 IDE ([NetBeans 27](https://netbeans.apache.org/)).

Required frameworks:
- Java SDK ([Java 24](https://www.oracle.com/java/technologies/))
- SceneBuilder for GUI development ([Gluon SceneBuilder](https://gluonhq.com/products/scene-builder/))
- JavaFX SDK ([JavaFX](https://gluonhq.com/products/javafx/))

## Prerequisites

- Install **Java 24** and ensure the `java` command is accessible in your system's PATH.
- Download and extract the **JavaFX SDK** to a known location (e.g., `C:\javafx-sdk-24`).
- Optionally, install **Apache NetBeans 27** for development or **SceneBuilder** for GUI editing.

## Installation

1. **Download the project**: Clone or download the repository to your local machine.
2. **Locate the JAR file**: Find the `Empty_Phrases-1.0.0.jar` (or similarly named) file in the project's `target` directory.
3. **Copy dependencies**: Ensure any required `lib` or resource folders are in the same directory as the JAR file.

## Running the Application

### Windows
Use the following batch file to launch Empty Phrases:

1. Save the following as `run_emptyphrases.bat` in the same directory as `Empty_Phrases-1.0.0.jar`:

```batch
@echo off
REM Batch file to launch the Empty Phrases JavaFX application

REM Set the path to the Empty Phrases JAR file
SET JAR_FILE=Empty_Phrases-1.0.0.jar

REM Set the path to the JavaFX SDK lib folder (update to match your JavaFX SDK location)
SET JAVAFX_LIB=C:\path\to\javafx-sdk-24\lib

REM Specify the main class (update if different or remove if specified in the JAR's manifest)
SET MAIN_CLASS=your.main.Main

REM Run the Empty Phrases application with required JavaFX modules
java --module-path "%JAVAFX_LIB%" --add-modules javafx.controls,javafx.fxml -jar %JAR_FILE% %MAIN_CLASS%

REM Pause to keep the window open for debugging (optional, remove if not needed)
pause
```

2. Update `JAVAFX_LIB` to point to your JavaFX SDK `lib` folder (e.g., `C:\javafx-sdk-24\lib`).
3. If the main class is not specified in the JAR's manifest, update `MAIN_CLASS` to the correct fully qualified class name (e.g., `your.main.Main`). Otherwise, remove `%MAIN_CLASS%` from the `java` command.
4. Double-click `run_emptyphrases.bat` or run it from the command prompt.

### Linux/macOS
Use the following shell script to launch Empty Phrases:

1. Save the following as `run_emptyphrases.sh` in the same directory as `Empty_Phrases-1.0.0.jar`:

```bash
#!/bin/bash
# Script to launch the Empty Phrases JavaFX application

# Set the path to the Empty Phrases JAR file
JAR_FILE=Empty_Phrases-1.0.0.jar

# Set the path to the JavaFX SDK lib folder (update to match your JavaFX SDK location)
JAVAFX_LIB=/path/to/javafx-sdk-24/lib

# Specify the main class (update if different or remove if specified in the JAR's manifest)
MAIN_CLASS=your.main.Main

# Run the Empty Phrases application with required JavaFX modules
java --module-path "$JAVAFX_LIB" --add-modules javafx.controls,javafx.fxml -jar $JAR_FILE $MAIN_CLASS
```

2. Update `JAVAFX_LIB` and `MAIN_CLASS` as needed.
3. Make the script executable: `chmod +x run_emptyphrases.sh`.
4. Run the script: `./run_emptyphrases.sh`.

## Using the Application

1. Launch the application using the batch or shell script.
2. Generate random phrases through the graphical interface.
3. Enjoy the humorous, politician-style output—perfect for a laugh!