package classes;

public  class RobotBoundaries {
   protected static int MAX_BOUNDARY_SIZE = 50;
    private int xAxisBoundary;
    private String boundaryError = "\n Boundary values must be between 1 and 50 inclusive. \n";

    public int getyAxisBoundary() {
        return yAxisBoundary;
    }

    public void setyAxisBoundary(int yAxisBoundary) {
        this.yAxisBoundary = yAxisBoundary;
    }

    private int yAxisBoundary;

    public int getxAxisBoundary() {
        return xAxisBoundary;
    }

    public void setxAxisBoundary(int xAxisBoundary) {
        this.xAxisBoundary = xAxisBoundary;
    }

    private void validateBoundaries(int xAxisBoundary, int yAxisBoundary) {
        if (xAxisBoundary < 1 || xAxisBoundary > RobotBoundaries.MAX_BOUNDARY_SIZE) {
            boundaryError += String.format("\n X axis Boundary values cannot be {0}", xAxisBoundary > 50 ?
                    "greater than 50 \n" : "less than 1 \n");
        }
        if (yAxisBoundary < 1 || yAxisBoundary > RobotBoundaries.MAX_BOUNDARY_SIZE) {
            boundaryError += String.format("\n Y axis Boundary values cannot be {0}", xAxisBoundary > 50 ?
                    "greater than 50 \n" : "less than 1 \n");
        }
        throw new IllegalArgumentException(boundaryError);
    }
    public RobotBoundaries(int xAxisBoundary, int yAxisBoundary) {
        this.xAxisBoundary = xAxisBoundary;
        this.yAxisBoundary = yAxisBoundary;
    }
}
