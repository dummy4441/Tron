import java.util.ArrayList;

public class TronModel {

    public static double CYClE_SPEED = 0.02;
    private Cycle cycle;

    public TronModel() {
        cycle = new Cycle(0.5, 0.15);
    }


    //
    public void advance(double[] direction) {
        double newX = cycle.getX() + direction[0];
        double newY = cycle.getY() + direction[1];
        cycle.setPosition(newX, newY);
        cycle.addToTrail(new double[]{newX, newY});
    }

    public Cycle getCycle() {
        return cycle;
    }


    // Draws the grid of the game
    public void draw() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.BLUE);

        int gridSize = 20; // Number of grid lines
        double cellSize = 1.0 / gridSize; // Size of each grid cell

        // Draw vertical grid lines
        for (int i = 0; i <= gridSize; i++) {
            double x = i * cellSize;
            StdDraw.line(x, 0, x, 1);
        }

        // Draw horizontal grid lines
        for (int j = 0; j <= gridSize; j++) {
            double y = j * cellSize;
            StdDraw.line(0, y, 1, y);
        }

        StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);

        Cycle c = getCycle();
        double cycleSize = Math.min(cellSize, Cycle.HALF_HEIGHT * 2); // Size of the cycle

        // Draw cycle
        StdDraw.filledSquare(c.getX(), c.getY(), cycleSize);

        ArrayList<double[]> trail = c.getTrail();
        for (double[] trailPoint : trail) {
            // Draw trail squares
            StdDraw.filledSquare(trailPoint[0], trailPoint[1], cycleSize);
        }

        StdDraw.show();
    }
}
