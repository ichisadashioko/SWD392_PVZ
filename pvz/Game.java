package pvz;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
    public int width;
    public int height;
    public JFrame frame;
    public JPanel panel;

    public Timer timer;

    // sample circle props
    public int circleX = 100;
    public int circleY = 100;
    public int circleRadius = 50;
    public int velocityX = 2; // Speed in the X direction
    public int velocityY = 2; // Speed in the Y direction

    public Game() {

        int width = 1280;
        int height = 720;
        this.width = width;
        this.height = height;

        System.out.println("Game initialized with width: " + width + " and height: " + height);

        // create a game window or canvas
        frame = new JFrame("PVZ Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(width, height);

        panel =
                new JPanel() {
                    @Override
                    public void paintComponent(java.awt.Graphics g) {
                        super.paintComponent(g);
                        // Custom rendering logic can go here
                        g.fillOval(circleX, circleY, circleRadius, circleRadius);
                    }
                };
        frame.add(panel);
        frame.setVisible(true);
        // Initialize game state, load resources, etc.
        timer =
                new Timer(
                        1000 / 60,
                        new java.awt.event.ActionListener() {
                            @Override
                            public void actionPerformed(java.awt.event.ActionEvent e) {
                                update();
                                panel.repaint(); // Trigger a repaint to render the game state
                            }
                        });

        System.out.println("Game timer started at 60 FPS.");
        timer.start();
        System.out.println("Game initialized successfully.");
    }

    public void start() {
        // Start the game logic, initialize game objects, etc.
        System.out.println("Game started.");
    }

    public void update() {
        // Update game state, handle input, etc.
        System.out.println("Game updated.");

        // Move the circle
        circleX += velocityX;
        circleY += velocityY;

        // Bounce off the edges
        if (circleX < 0 || circleX + circleRadius > width) {
            velocityX = -velocityX;
        }
        if (circleY < 0 || circleY + circleRadius > height) {
            velocityY = -velocityY;
        }
    }

    public void render() {
        // Render the game state to the screen
        System.out.println("Game rendered.");
    }

    public void addPlant(Sunflower sunflower) {
        // Logic to add a sunflower plant to the game
        System.out.println("Sunflower added to the game.");
    }

    public void addZombie(Zombie zombie) {
        // Logic to add a zombie to the game
        System.out.println("Zombie added to the game.");
    }

    public boolean isRunning() {
        // Check if the game is still running
        return true; // Placeholder for actual game state check
    }

    public void end() {
        // End the game, clean up resources, etc.
        System.out.println("Game ended.");
    }
}
