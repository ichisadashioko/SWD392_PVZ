package pvz;

public class Sunflower extends Plant {
    private int sunProductionRate; // Rate at which the sunflower produces sun
    private int sunAmount; // Amount of sun produced

    public Sunflower() {
        this.sunProductionRate = 25; // Example rate, can be adjusted
        this.sunAmount = 0;
    }

    @Override
    public void update() {
        // Produce sun over time
        sunAmount += sunProductionRate;
        if (sunAmount >= 100) { // Example threshold for producing a sun
            produceSun();
            sunAmount = 0; // Reset after producing
        }
    }

    private void produceSun() {
        // Logic to produce and add a sun to the game
        System.out.println("Sunflower produced a sun!");
        int x = 100; // Example x position
        int y = 100; // Example y position
        Sun newSun = new Sun(x, y, 50); // Create a new sun
        Game.getInstance().addSun(newSun); // Add the sun to the game
    }

    @Override
    public void render(java.awt.Graphics g) {
        // Render the sunflower (placeholder logic)
        g.drawString("Sunflower", 10, 10);
    }
}
