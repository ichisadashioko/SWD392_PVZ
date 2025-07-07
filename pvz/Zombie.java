package pvz;

import java.awt.Graphics;

public class Zombie extends GameObject {
    public int x;
    public int y;
    public int move_speed = 1;

    public Zombie(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void update() {
        this.x -= move_speed;
    }

    @Override
    public void render(Graphics g) {
        g.drawString("zombie", x, y);
    }
}
