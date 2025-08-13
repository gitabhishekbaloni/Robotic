package classes;

import java.util.ArrayList;
import java.util.List;


public class Robot implements Axis {

    private static List<Coordinates> forbiddenCoordinates = new ArrayList<>();

    private boolean isLost = false;

    private int xAxis;

    private int yAxis;

    private final int xAxisBoundary;

    private final int yAxisBoundary;

    //only get allowed
    public int getyAxisBoundary() {
        return yAxisBoundary;
    }

    //only get allowed
    public int getxAxisBoundary() {
        return xAxisBoundary;
    }

    public Robot(int xAxis, int yAxis, Character direction, RobotBoundaries robotBoundaries) {
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        setUserDirections(direction);
        if (robotBoundaries == null) {
            throw new IllegalArgumentException("Robot boundaries cannot be null");
        }
        this.xAxisBoundary = robotBoundaries.getxAxisBoundary();
        this.yAxisBoundary = robotBoundaries.getyAxisBoundary();
    }

    public int getyAxis() {
        return yAxis;
    }

    public void setyAxis(int yAxis) {
        this.yAxis = yAxis;
    }

    public int getxAxis() {
        return xAxis;
    }

    public void setxAxis(int xAxis) {
        this.xAxis = xAxis;
    }


    private Direction direction;

    /**
     * Returns the current direction of the robot.
     *
     * @return
     */
    public Direction getDirection() {
        if (this.direction == null) {
            this.direction = Direction.East; // Default direction
        }
        return direction;
    }


    /***
     *
     * Sets the direction of the robot based on a string input.
     * @param direction
     */
    public void setUserDirections(char direction) {
        switch (direction) {
            case 'E':
                this.direction = Direction.East;
                break;
            case 'W':
                this.direction = Direction.West;
                break;
            case 'N':
                this.direction = Direction.North;
                break;
            case 'S':
                this.direction = Direction.South;
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
    }

    /**
     * Sets the direction of the robot based on a Direction enum.
     *
     * @param direction
     */
    public void setDirectionAndMoveForward(Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("Direction cannot be null");
        }
        switch (direction) {
            case East:
                this.direction = Direction.East;
                if (this.xAxis + 1 > this.xAxisBoundary) {
                    System.out.println("Robot is lost after X coordinates: (" + this.xAxis + ", " + this.yAxis + ")");
                    setLost(true);
                } else {
                    this.xAxis = this.xAxis + 1;
                }
                break;
            case West:
                this.direction = Direction.West;
                if (this.xAxis - 1 < 0) {
                    setLost(true);
                    System.out.println("Robot is lost after X coordinates: (" + this.xAxis + ", " + this.yAxis + ")");
                } else {
                    this.xAxis = this.xAxis - 1;
                }
                break;
            case North:
                this.direction = Direction.North;
                if (this.yAxis + 1 > this.yAxisBoundary) {
                    setLost(true);
                    System.out.println("Robot is lost after Y coordinates: (" + this.xAxis + ", " + this.yAxis + ")");
                } else {
                    this.yAxis = this.yAxis + 1;
                }
                break;
            case South:
                this.direction = Direction.South;
                if (this.yAxis - 1 < 0) {
                    setLost(true);
                    System.out.println("Robot is lost after Y coordinates: (" + this.xAxis + ", " + this.yAxis + ")");
                } else {
                    this.yAxis = this.yAxis - 1;
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid direction: " + direction);
        }
        // If the robot is lost, add its coordinates to the forbidden list
        if (this.isLost) {
            Coordinates coordinates = new Coordinates(this.xAxis, this.yAxis);
            if (!forbiddenCoordinates.contains(coordinates)) {
                forbiddenCoordinates.add(coordinates);
            }
        }
    }

    /**
     * @param orientations
     */
    public void setDirectionUsingOrientation(Orientations orientations) {
        if (direction == null) {
            throw new IllegalArgumentException("Movement cannot be null");
        }
        switch (orientations) {
            case L:
                if (Direction.East.equals(this.direction)) {
                    this.direction = Direction.North;
                } else if (Direction.North.equals(this.direction)) {
                    this.direction = Direction.West;
                } else if (Direction.West.equals(this.direction)) {
                    this.direction = Direction.South;
                } else if (Direction.South.equals(this.direction)) {
                    this.direction = Direction.East;
                }
                break;
            case R:
                if (Direction.East.equals(this.direction)) {
                    this.direction = Direction.South;
                } else if (Direction.North.equals(this.direction)) {
                    this.direction = Direction.East;
                } else if (Direction.West.equals(this.direction)) {
                    this.direction = Direction.North;
                } else if (Direction.South.equals(this.direction)) {
                    this.direction = Direction.West;
                }
                break;
            case F:
                setDirectionAndMoveForward(this.direction);
                break;
            default:
                throw new IllegalArgumentException("Invalid movement: " + orientations);
        }

    }

    public boolean isLost() {
        return isLost;
    }

    public void setLost(boolean lost) {
        isLost = lost;
    }
}
