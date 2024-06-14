import java.util.ArrayList;

/** A cycle. */
public class Cycle {

    /**
     * Half the height of a cycle. (This is needed more often than the full
     * height.)
     */
    public static final double HALF_HEIGHT = 0.0055;
    /**
     * Half the width of a cycle. (This is needed more often than the full
     * width.)
     */
    public static final double HALF_WIDTH = 0.0055;

    /** Y coordinate of this cycle. */
    private double y;
    /** X coordinate of this cycle. */
    private double x;

    /** Array to store the trail of the cycle */
    private ArrayList<double[]> trail;

    public Cycle(double x, double y) {
        this.y = y;
        this.x = x;
        this.trail = new ArrayList<>();
        // add the current position to the trail
        double[] initialPosition = { x, y };

        trail.add(initialPosition);
    }

    /** Returns the y coordinate of this cycle. */
    public double getY() {
        return y;
    }

    /** Returns the x coordinate of this cycle. */
    public double getX() {
        return x;
    }

    public double[] getCurrentPosition() {
        return new double[]{x, y};
    }

    /**
     * Adjusts this cycle's position by dy. The cycle will not move beyond the
     * top or bottom of the screen.
     */
    public void moveV(double dy) {
        y += dy;
        if (y + HALF_HEIGHT > 1.0) {
            y = 1.0 - HALF_HEIGHT;
        }
        if (y < HALF_HEIGHT) {
            y = HALF_HEIGHT;
        }
        // Update trail
        addToTrail(new double[] { x, y });
    }

    /**
     * Adjusts this cycle's position by dx. The cycle will not move beyond the
     * sides of the screen.
     */
    public void moveH(double dx) {
        x += dx;
        if (x + HALF_WIDTH > 1.0) {
            x = 1.0 - HALF_WIDTH;
        }
        if (x < HALF_WIDTH) {
            x = HALF_WIDTH;
        }
        // Update trail
        addToTrail(new double[] { x, y });
    }

    /**
     * Saves the x and y positions of the cycle as the trail.
     *
     * @return The trail of the cycle.
     */
    public ArrayList<double[]> getTrail() {
        return trail;
    }

    /**
     * Adds a position to the trail of the cycle.
     *
     * @param position The position to add to the trail.
     */
    public void addToTrail(double[] position) {
        trail.add(position);
    }

    /**
     * Sets the position of the cycle.
     *
     * @param newX The new x-coordinate of the cycle.
     * @param newY The new y-coordinate of the cycle.
     */
    public void setPosition(double newX, double newY) {
        x = newX;
        y = newY;
    }

    public boolean intersectsPastPosition() {
        double[] currentPosition = getCurrentPosition();

        // Iterate over the trail, skipping the last position as it's the current position
        for (int i = 0; i < trail.size() - 1; i++) {
            double[] pastPosition = trail.get(i);

            // Check if the current position matches any past position
            if (currentPosition[0] == pastPosition[0] && currentPosition[1] == pastPosition[1]) {
                return true; // Intersection found
            }
        }

        return false;
    }
}
