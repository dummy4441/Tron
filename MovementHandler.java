import java.awt.event.KeyEvent;

public class MovementHandler {
    // boolean flags to indicate movement direction
    private boolean isMovingLeft, isMovingRight, isMovingUp, isMovingDown;

    // array to store the current movement direction
    private double[] direction;

    // constructor to initialize the movement direction with a given initial speed
    public MovementHandler(double initialSpeed) {
        direction = new double[]{initialSpeed, 0}; // initially moving right with the given speed
        isMovingLeft = false;
        isMovingRight = false;
        isMovingUp = true;  // initially moving up
        isMovingDown = false;
    }

    // method to update the movement direction based on pressed keys
    public void updateDirection() {
        // check if keys are pressed to update movement flags
        if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
            isMovingLeft = true;
            isMovingRight = false;
            isMovingUp = false;
            isMovingDown = false;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
            isMovingLeft = false;
            isMovingRight = true;
            isMovingUp = false;
            isMovingDown = false;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_W)) {
            isMovingLeft = false;
            isMovingRight = false;
            isMovingUp = true;
            isMovingDown = false;
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
            isMovingLeft = false;
            isMovingRight = false;
            isMovingUp = false;
            isMovingDown = true;
        }

        // update speed based on pressed keys
        updateSpeed();
    }

    // method to update speed based on additional keys pressed
    private void updateSpeed() {
        // increase, decrease, or return to normal speed based on key pressed
        if (StdDraw.isKeyPressed(KeyEvent.VK_E)) {
            TronModel.CYClE_SPEED = 0.022; // set a faster speed
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_Q)) {
            TronModel.CYClE_SPEED = 0.01; // set a slower speed
        } else if (StdDraw.isKeyPressed(KeyEvent.VK_1)) {
            TronModel.CYClE_SPEED = 0.02; // return to base speed
        }

        // update movement direction based on current flags and speed
        if (isMovingLeft && direction[0] != TronModel.CYClE_SPEED) {
            direction = new double[]{-TronModel.CYClE_SPEED, 0}; // set direction to left
        } else if (isMovingRight && direction[0] != -TronModel.CYClE_SPEED) {
            direction = new double[]{TronModel.CYClE_SPEED, 0}; // set direction to right
        } else if (isMovingUp && direction[1] != -TronModel.CYClE_SPEED) {
            direction = new double[]{0, TronModel.CYClE_SPEED}; // set direction upwards
        } else if (isMovingDown && direction[1] != TronModel.CYClE_SPEED) {
            direction = new double[]{0, -TronModel.CYClE_SPEED}; // set direction downwards
        }
    }

    // method to get the current movement direction
    public double[] getDirection() {
        return direction;
    }
}
