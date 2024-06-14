import java.awt.event.KeyEvent;

public class Tron {

    public static void main(String[] args) {
        new MovementHandler.Tron().run();
    }

    private TronModel model;
    private MovementHandler movementHandler;

    public Tron() {
        model = new TronModel();
        movementHandler = new MovementHandler(TronModel.CYClE_SPEED);
    }

    public boolean checkGameover() {
        Cycle c = model.getCycle();

        if (c.intersectsPastPosition()) {
            return true;
        }

        // Check if the cycle is outside the boundaries of the game window
        return c.getX() < 0 || c.getX() > 1 || c.getY() < 0 || c.getY() > 1;
    }

    // creates a title screen before the game starts
    public void titleScreen() {
        StdDraw.clear(StdDraw.BLACK);
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.75, "Welcome to Tron!");
        StdDraw.text(0.5, 0.5, "Use W/A/S/D to move.");
        StdDraw.text(0.5, 0.4, "Use Q to decelerate, and E to accelerate.");
        StdDraw.text(0.5, 0.3, "Press 1 to return to normal speed.");
        StdDraw.text(0.5, 0.2, "Avoid colliding with the walls and your own trail.");
        StdDraw.text(0.5, 0.1, "Press space to start.");
        StdDraw.show();
        while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
            // wait for spacebar to be pressed in order to start the game
        }
    }

    public void draw() {
        model.draw();
    }

    public void run() {
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-0.25, 1.25); // so that the player understands better the outline of the game
        titleScreen();

        while (!checkGameover()) {
            draw();
            movementHandler.updateDirection();
            model.advance(movementHandler.getDirection());
            StdDraw.pause(100);
        }

        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.5, "Game Over!");
        StdDraw.show();
    }
}
