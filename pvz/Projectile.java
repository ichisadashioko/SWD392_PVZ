package pvz;

public class Projectile extends GameObject {
    private int x;
    private int y;
    private int speed;
    private int direction; // Angle in degrees

    public Projectile(int x, int y, int speed, int direction) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direction = direction;
    }

    @Override
    public void update() {
        // Update the position based on speed and direction
        x += speed * Math.cos(Math.toRadians(direction));
        y += speed * Math.sin(Math.toRadians(direction));
    }

    @Override
    public void render(java.awt.Graphics g) {
        // Draw the projectile as a simple circle
        g.fillOval(x, y, 10, 10); // Example size of 10x10 pixels
    }
    // Additional methods for collision detection, etc. can be added here
}
