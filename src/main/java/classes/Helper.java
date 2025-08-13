package classes;

public class Helper {

    /***
     * Initializes the boundaries with the given user input .
     * @param userInput
     * @return
     */
    public RobotBoundaries getBoundaries(String[] userInput) {
        try {
            if (userInput.length != 2) {
                throw new IllegalArgumentException("Please enter exactly two numbers with space for boundaries.");
            }

            return new RobotBoundaries(Integer.parseInt(userInput[0]),
                    Integer.parseInt(userInput[1]));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new IllegalArgumentException("Invalid boundary input. Please enter two integers with space.");
        }
    }

    /***
     * Initializes the robot with the given user input and boundaries.
     * @param userInput
     * @param boundaries
     * @return
     */
    public Robot initialiseRobot(String[] userInput, RobotBoundaries boundaries) {
        try {
            if (userInput.length != 3) {
                throw new IllegalArgumentException("Please enter exactly three values: x, y, and direction.");
            }
            if (userInput[2].length() != 1 || !"EWNS".contains(userInput[2].toUpperCase())) {
                throw new IllegalArgumentException("Direction must be one of E, W, N, S.");
            }

            return  new Robot(Integer.parseInt(userInput[0]), Integer.parseInt(userInput[1]),
                    userInput[2].charAt(0),
                    boundaries);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Robot initiation failed");
        }
    }

    /**
     * Executes the commands on the robot based on the given string of commands.
     * @param commands
     * @param robot
     */
    public void executeCommandOnRobot(String commands, Robot robot) {
        if (commands.isEmpty()) {
            System.out.println("No commands entered. Exiting.");
            return;
        }
        try {
            if (commands.length() > 100) {
                throw new IllegalArgumentException("Commands length cannot be more than 100 characters.");
            }

            for (char commandCharacter : commands.toUpperCase().strip().toCharArray()) {
                robot.setDirectionUsingOrientation(Orientations.valueOf(String.valueOf(commandCharacter)));
//            System.out.print(index + commandCharacter + "\n" + robot.getDirection() + "\n");
                if (robot.isLost()) break;
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Invalid command");
        }
    }
}
