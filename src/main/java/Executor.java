
import classes.Helper;

import classes.Robot;
import classes.RobotBoundaries;

import java.util.Scanner;

public class Executor {


    /**
     * Main method to execute the robot simulation.
     * It initializes the robot with boundaries and position, then processes commands.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        RobotBoundaries boundaries = null;
        String continueExecutionUserInput= null;
        Robot robot;
        Scanner scanner = new Scanner(System.in);
        Helper helper = new Helper();

        //set boundaries
        System.out.println("Enter the rectangular boundaries of the robot (x y): - " +
                "Please keep space between user input");
        String[] userInput = scanner.nextLine().split(" ");
        boundaries = helper.getBoundaries(userInput);

        do {
            System.out.println("Initialise new Robot -Enter the Robot position of the robot (x y,direction{E,W,N,S}): " +
                    "Please keep space between user input");
            userInput = scanner.nextLine().split(" ");

            //initialise robot with initial position
            robot = helper.initialiseRobot(userInput, boundaries);
            System.out.println("Enter the command  for robot without any space: ");

            //initialise command on robot
            String commands = scanner.nextLine().strip();
            helper.executeCommandOnRobot(commands, robot);
            System.out.println("Output-->Current Position: (" + robot.getxAxis() + ", " + robot.getyAxis() + ", " +
                    robot.getDirection().value + ")" + (robot.isLost() ? " - Lost" : ""));

            System.out.println("Do you want to continue? (YES/NO)");
            continueExecutionUserInput = scanner.nextLine().strip();
        }while (continueExecutionUserInput.equalsIgnoreCase("YES"));
        System.out.print("Programme ended!!");
    }
}

