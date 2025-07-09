package pvz;

public class Sun extends GameObject {
    private int x;
    private int y;
    public int final_y;
    private int value; // Amount of sun this object provides

    private int move_speed = 1; // Speed at which the sun moves downwards

    public Sun(int x, int y, int value, int final_y) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.final_y = final_y; // The final y position where the sun will stop moving
    }

    private int update_count = 0;

    @Override
    public void update() {
        update_count++;
        // Move the sun downwards
        // System.out.println("Sun updated at position: (" + x + ", " + y + ")");
        // System.out.println("update_count: " + update_count);
        y += move_speed;
        y = Math.min(y, final_y); // Ensure it doesn't go below the final_y limit
        // System.out.println("(x: " + x + ", y: " + y + ", final_y: " + final_y + ")");
        // Update logic for the sun, if needed
        // For example, it could move or disappear after a certain time
    }

    @Override
    public void render(java.awt.Graphics g) {
        // Draw the sun as a simple circle with a specific color
        g.setColor(java.awt.Color.YELLOW);
        g.fillOval(x, y, 20, 20); // Example size of 20x20 pixels
    }

    public int getValue() {
        return value;
    }
}
