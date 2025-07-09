package pvz;

public class Sunflower extends Plant {
    private int sunProductionRate; // Rate at which the sunflower produces sun
    private int sunAmount; // Amount of sun produced

    public Sunflower() {
        this.sunProductionRate = 25; // Example rate, can be adjusted
        this.sunAmount = 0;
    }

    private long last_production_time = 0; // Last time sun was produced
    private long production_interval = 1000; // Interval in milliseconds for producing sun

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
        //     produceSun();
        //     sunAmount = 0; // Reset after producing
        // }
    }

    private void produceSun() {
        // Logic to produce and add a sun to the game
        System.out.println("Sunflower produced a sun!");
        int y = 100; // Example x position
        int x = (int) (Math.random() * Game.getInstance().width);
        int final_y = y + (int) (Math.random() * Game.getInstance().height - y);
        System.out.println("final_y: " + final_y);
        Sun newSun = new Sun(x, y, 50, final_y); // Create a new sun
        Game.getInstance().addSun(newSun); // Add the sun to the game
    }

    @Override
    public void render(java.awt.Graphics g) {
        // Render the sunflower (placeholder logic)
        g.drawString("Sunflower", 10, 10);
    }
}
