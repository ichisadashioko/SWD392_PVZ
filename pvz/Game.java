package pvz;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game {
    // singleton instance
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) {
            instance = new Game();
        }
        return instance;
    }

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

    public ArrayList<GameObject> gameObjects = new ArrayList<>();
    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Plant> plants = new ArrayList<>();
    public ArrayList<Zombie> zombies = new ArrayList<>();
    public ArrayList<Sun> suns = new ArrayList<>();

    private Game() {
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
                        for (int i = 0; i < gameObjects.size(); i++) {
                            GameObject go = gameObjects.get(i);
                            if (go == null) {
                                continue;
                            }
                            try {
                                if (go.active) {
                                    go.render(g);
                                }
                            } catch (Exception e) {
                                // TODO: handle exception
                                System.err.println(e.getMessage());
                                System.err.println(e.getStackTrace());
                            }
                        }
                    }
                };
        frame.add(panel);
        frame.setVisible(true);

        // Initialize game state, load resources, etc.
        this.start();
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
        // Example of adding a plant and a zombie
        addPlant(new Sunflower());
        addZombie(new Zombie(1200, 200));
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

        for (int i = 0; i < gameObjects.size(); i++) {
            GameObject gameObject = gameObjects.get(i);
            if (gameObject == null) {
                continue;
            }
            if (gameObject.active) {
                try {
                    gameObject.update();
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    System.err.println(e.getStackTrace());
                }
            }
        }
    }

    public void render() {
        // Render the game state to the screen
        System.out.println("Game rendered.");
    }

    public void addSun(Sun sun) {
        suns.add(sun);
        gameObjects.add(sun);
    }

    public void addPlant(Plant plant) {
        // Logic to add a sunflower plant to the game
        System.out.println("Plant added to the game.");
        plants.add(plant);
        gameObjects.add(plant); // Add to the game objects list for rendering
    }

    public void addZombie(Zombie zombie) {
        // Logic to add a zombie to the game
        System.out.println("Zombie added to the game.");
        zombies.add(zombie);
        gameObjects.add(zombie);
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
