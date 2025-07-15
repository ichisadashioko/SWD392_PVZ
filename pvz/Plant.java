package pvz;

public class Plant extends GameObject {
    public String image_path = "peashooter.png";
    public int x; // X position of the plant
    public int y; // Y position of the plant
    public int width = 50; // Width of the plant
    public int height = 50; // Height of the plant

    // Plant-specific properties and methods can be added here
    // For example, health, damage, type, etc.

    @Override
    public void update() {
        // Update logic for the plant
        // This method should be overridden by subclasses if needed
    }

    @Override
    public void render(java.awt.Graphics g) {
        // Render logic for the plant
        // This method should be overridden by subclasses if needed
        // validate x, y, width, and height
        if (x < 0 || y < 0 || width <= 0 || height <= 0) {
            return;
        }

        // Draw the plant using the image path
        // check if the image exists
        if (image_path == null || image_path.isEmpty()) {
            System.err.println("Image path is not set or is empty.");
            return;
        }
        if (!new java.io.File(image_path).exists()) {
            System.err.println("Image file does not exist: " + image_path);
            return;
        }

        // Load the image and draw it
        // Using javax.swing.ImageIcon to load the image
        // Note: This is a simple way to load images, but for better performance,
        // consider using a dedicated image loading library or framework.
        // Also, ensure that the image is in the correct format and accessible.
        java.awt.Image plantImage = new javax.swing.ImageIcon(image_path).getImage();
        g.drawImage(plantImage, x, y, width, height, null);
    }
}
