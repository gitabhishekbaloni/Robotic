import classes.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RobotTests {

    Helper helper = new Helper();

    @Test
    void testBoundaryParsing() {
        String[] input = {"50", "50"};

        RobotBoundaries boundaries = helper.getBoundaries(input);
        assertEquals(50, boundaries.getxAxisBoundary());
        assertEquals(50, boundaries.getyAxisBoundary());
    }

    @Test
    void testRobotInitialization() {
        RobotBoundaries boundaries = new RobotBoundaries(50, 50);
        String[] input = {"10", "20", "N"};
        Robot robot = helper.initialiseRobot(input, boundaries);
        assertEquals(10, robot.getxAxis());
        assertEquals(20, robot.getyAxis());
        assertEquals(Direction.North, robot.getDirection());
        assertFalse(robot.isLost());
    }

    @Test
    void testCommandExecutionWithinBounds() {
        RobotBoundaries boundaries = new RobotBoundaries(50, 50);
        String[] input = {"0", "0", "E"};
        Robot robot = helper.initialiseRobot(input, boundaries);
        helper.executeCommandOnRobot("FFFF", robot); // Move east 4 times
        assertEquals(4, robot.getxAxis());
        assertEquals(0, robot.getyAxis());
        assertFalse(robot.isLost());
    }

    @Test
    void testCommandExecutionAtBoundary() {
        RobotBoundaries boundaries = new RobotBoundaries(2, 2);
        String[] input = {"2", "2", "N"};
        Robot robot = helper.initialiseRobot(input, boundaries);
        helper.executeCommandOnRobot("LRF", robot); // Move north, should be lost
        assertEquals(2, robot.getxAxis());
        assertEquals(2, robot.getyAxis());
        assertTrue(robot.isLost());
    }

    @Test
    void testTurnAndMove() {
        RobotBoundaries boundaries = new RobotBoundaries(5, 5);
        String[] input = {"1", "1", "N"};
        Robot robot = helper.initialiseRobot(input, boundaries);
        helper.executeCommandOnRobot("RFF", robot); // Turn east, move 2
        assertEquals(3, robot.getxAxis());
        assertEquals(1, robot.getyAxis());
        assertEquals(Direction.East, robot.getDirection());
        assertFalse(robot.isLost());
    }

    @Test
    void testInvalidInitialization() {
        RobotBoundaries boundaries = new RobotBoundaries(5, 5);
        String[] input = {"10", "10", "SF"}; // Out of bounds
        Exception exception = assertThrows(RuntimeException.class, () -> {
            helper.initialiseRobot(input, boundaries);
        });
        assertTrue(exception.getMessage().contains("Robot initiation failed"));
    }
}
