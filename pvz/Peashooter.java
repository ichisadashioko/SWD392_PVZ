package pvz;

public class Peashooter extends Plant {
    public float damage = 20.0f; // Damage dealt by the Peashooter
    public int fireRate = 1000; // Fire rate in milliseconds
    public int last_fire_time = 0; // Last time the Peashooter fired
    public ProjectileListener projectileListener = null;

    public void fire() {
        Pea pea = new Pea();
        pea.x = this.x + this.width; // Position the pea to the right of the Peashooter
        pea.y = this.y + this.height / 2; // Center the pea vertically
        pea.width = 10; // Width of the pea
        pea.height = 5; // Height of the pea
        pea.damage = this.damage; // Set the damage of the pea
        if (projectileListener != null) {
            System.out.println("Peashooter firing pea projectile");
            projectileListener.addProjectile(pea);
        }
    }

    @Override
    public void update() {
        super.update();
        // System.out.println("Peashooter update called");
        // Update logic specific to Peashooter
        // Check if it's time to fire
        int currentTime = (int) System.currentTimeMillis();
        if (currentTime - last_fire_time >= fireRate) {

            System.out.println("Peashooter firing");
            fire();
            last_fire_time = currentTime; // Update the last fire time
        }
    }
}
