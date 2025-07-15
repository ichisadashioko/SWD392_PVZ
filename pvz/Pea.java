package pvz;

public class Pea extends GameObject {
    public int x;
    public int y;
    public float damage;
    public int width;
    public int height;
    // move vector
    public int move_x = 100; // Speed of the pea in the x direction
    public int move_y = 0; // Speed of the pea in the y direction
    public long last_updated_time_ms = 0;
    public static String image_path = "ProjectilePea.png";

    @Override
    public void update() {
        // System.out.println("Pea updated called");
        long time_ms = System.currentTimeMillis();
        if (last_updated_time_ms == 0) {
            last_updated_time_ms = time_ms;
        }

        // Calculate the time difference since the last update
        long delta_time_ms = time_ms - last_updated_time_ms;
        if (delta_time_ms > 0) {
            // Update the position of the pea based on its speed
            x += (int) (((double) move_x) * ((double) delta_time_ms) / 1000.0); // Convert milliseconds to seconds
            y += move_y * delta_time_ms / 1000.0; // Convert milliseconds to seconds

            // Update the last updated time
            last_updated_time_ms = time_ms;
        }
    }

    @Override
    public void render(java.awt.Graphics g) {
        // Validate x, y, width, and height
        if (x < 0 || y < 0 || width <= 0 || height <= 0) {
            System.out
                    .println("Invalid Pea dimensions: x=" + x + ", y=" + y + ", width=" + width + ", height=" + height);
            return;
        }

        // Check if the image path is set and the file exists
        if (image_path == null || image_path.isEmpty()) {
            System.err.println("Image path is not set or is empty.");
            return;
        }
        if (!new java.io.File(image_path).exists()) {
            System.err.println("Image file does not exist: " + image_path);
            return;
        }

        // System.out
        // .println("Rendering Pea at position: (" + x + ", " + y + ") with dimensions:
        // " + width + "x" + height);

        // Load the image and draw it
        java.awt.Image peaImage = new javax.swing.ImageIcon(image_path).getImage();
        g.drawImage(peaImage, x, y, width, height, null);
        g.drawOval(x, y, 20, 20); // Draw an oval around the pea for debugging
    }
}
