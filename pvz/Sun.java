package pvz;

import java.awt.Point;
import java.awt.Rectangle;

public class Sun extends GameObject implements Clickable, ZIndexProvider {
    private int x;
    private int y;
    public int render_width = 20; // Width of the sun object
    public int render_height = 20; // Height of the sun object
    public int final_y;
    private int value; // Amount of sun this object provides

    private int move_speed = 1; // Speed at which the sun moves downwards

    public Sun(int x, int y, int value, int final_y) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.final_y = final_y; // The final y position where the sun will stop moving
    }

    private int z_index = 0;

    @Override
    public int getZIndex() {
        return z_index;
    }

    @Override
    public boolean onClick(int mouseX, int mouseY) {
        Rectangle sun_rect = new Rectangle(x, y, render_width, render_height);
        Point click_point = new Point(mouseX, mouseY);

        System.out.println("sun_mouse_click: " + sun_rect + " - click_point: " + click_point);
        // Check if the click is within the bounds of the sun object

        // boolean is_clicked = sun_rect.contains(click_point);
        boolean is_clicked =
                (mouseX > x)
                        && (mouseX < (x + render_width))
                        && (mouseY > y)
                        && (mouseY < (y + render_height));
        System.out.println("is_clicked: " + is_clicked);
        if (is_clicked) {
            // Logic for when the sun is clicked, e.g., collecting it
            System.out.println("Sun collected at position: (" + x + ", " + y + ")");
            this.active = false;
            Game.getInstance().removeSun(this); // Remove the sun from the game
            return true; // Click was handled
        }
        return false; // Click was not on this sun object
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
