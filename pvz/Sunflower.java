package pvz;

public class Sunflower extends Plant {
    private int sunProductionRate; // Rate at which the sunflower produces sun
    private int sunAmount; // Amount of sun produced

    public Sunflower() {
        this.sunProductionRate = 25; // Example rate, can be adjusted
        this.sunAmount = 0;
    }

    private long last_production_time = 0; // Last time sun was produced
    private long production_interval = 5000; // Interval in milliseconds for producing sun

    @Override
    public void update() {
        // Produce sun over time
        long currentTime = System.currentTimeMillis();
        if (currentTime - last_production_time >= production_interval) {
            produceSun();
            last_production_time = currentTime; // Update last production time
        }
        // sunAmount += sunProductionRate;
        // if (sunAmount >= 100) { // Example threshold for producing a sun
        // produceSun();
        // sunAmount = 0; // Reset after producing
        // }
    }

    boolean debug_click_on_sun = true;
    private int produced_count = 0;

    public SunWorld sun_world;

    private void produceSun() {
        produced_count += 1;
        if (debug_click_on_sun) {
            if (produced_count > 1) {
                return;
            }
        }
        // Logic to produce and add a sun to the game
        System.out.println("Sunflower produced a sun!");
        if (sun_world != null) {
            int y = 100; // Example x position
            int x = (int) (Math.random() * sun_world.getWidth());
            int final_y = y + (int) (Math.random() * sun_world.getHeight() - y);
            // System.out.println("final_y: " + final_y);
            Sun newSun = new Sun(x, y, 50, final_y); // Create a new sun
            sun_world.addSun(newSun); // Add the sun to the game
        }
    }

    public static String image_path = "sunflower.png";

    @Override
    public void render(java.awt.Graphics g) {
        // Render the sunflower (placeholder logic)
        // g.drawString("Sunflower", 10, 10);
        // get width and height from the image
        if (image_path == null || image_path.isEmpty()) {
            System.err.println("Image path is not set or is empty.");
            return;
        }

        if (!new java.io.File(image_path).exists()) {
            System.err.println("Image file does not exist: " + image_path);
            return;
        }

        java.awt.Image sunflowerImage = new javax.swing.ImageIcon(image_path).getImage();
        if (sunflowerImage != null) {
            g.drawImage(sunflowerImage, x, y, width, height, null);
        } else {
            System.err.println("Failed to load sunflower image from: " + image_path);
        }
    }
}
