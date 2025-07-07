package pvz;

public class Sun extends GameObject {
    private int x;
    private int y;
    private int value; // Amount of sun this object provides

    public Sun(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public void update() {
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
