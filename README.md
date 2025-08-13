# Robotic Simulation

A Java console application to simulate a robot moving within user-defined rectangular boundaries.

## Features

- Set custom boundaries for the robot.
- Initialize robot position and direction (E, W, N, S).
- Input command sequences to move the robot.
- Handles invalid input and boundary violations.

## Requirements

- Java 8 or higher
- IntelliJ IDEA (recommended) or any Java IDE

## How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA.
3. Build the project.
4. Run the `Executor` class (`src/main/java/Executor.java`).

**Or from the command line:**
```sh
javac -d out src/main/java/Executor.java src/main/java/classes/*.java
java -cp out Executor

```

## Usage
1. Enter the rectangular boundaries (e.g., 5 5).Note: with space between the inputs.
2. Enter the robot's initial position and direction (e.g., 1 2 N). Note: with space between the inputs.
3. Enter a command sequence (e.g., LMLMLMLMM).
4. View the robot's final position and status.
5. Choose to continue or exit(Yes or No option).
   
![execution.png](Resources/execution.png)
## Project Structure
````
src/main/java/classes/RobotBoundaries.java — Handles boundary logic.
src/main/java/classes/Helper.java — Input parsing and robot initialization.
src/main/java/classes/Robot.java — Robot state and movement.
src/main/java/Executor.java — Main entry point.