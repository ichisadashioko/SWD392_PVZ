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

    public final int CELL_WIDTH = 80; // Width of each cell in the grid
    public final int CELL_HEIGHT = 100; // Height of each cell in the grid
    public final int GRID_COLS = 9; // Number of columns in the grid
    public final int GRID_ROWS = 5; // Number of rows in the grid
    public Plant[][] plantGrid =
            new Plant[GRID_ROWS][GRID_COLS]; // 2D array to represent the grid of plants

    public boolean placePlant(Plant plant, int row, int col) {
        // Check if the position is within bounds and not already occupied
        if (row < 0
                || row >= GRID_ROWS
                || col < 0
                || col >= GRID_COLS
                || plantGrid[row][col] != null) {
            return false; // Invalid position or already occupied
        }
        // Place the plant in the grid
        plantGrid[row][col] = plant;
        // Set the plant's position based on the grid cell
        plant.x = col * CELL_WIDTH; // Calculate X position based on column
        plant.y = row * CELL_HEIGHT; // Calculate Y position based on row
        plant.width = CELL_WIDTH; // Set width to cell width
        plant.height = CELL_HEIGHT; // Set height to cell height
        plant.image_path = "peashooter.png"; // Set the image path for the plant
        plant.active = true; // Mark the plant as active
        plant.update(); // Update the plant's state if necessary
        // addPlant(plant); // Add to the game objects for rendering
        return true; // Successfully placed the plant
    }

    public void render_plant_grid(java.awt.Graphics g) {
        // Render the plant grid
        for (int r = 0; r < GRID_ROWS; r++) {
            for (int c = 0; c < GRID_COLS; c++) {
                Plant plant = plantGrid[r][c];
                if (plant != null) {
                    if (!plant.active) {
                        continue; // Skip rendering if the plant is not active
                    }
                    try {
                        // Render the plant at the corresponding cell position
                        int x = c * CELL_WIDTH;
                        int y = r * CELL_HEIGHT;
                        g.setColor(java.awt.Color.GREEN); // Example color for plants
                        g.fillRect(x, y, CELL_WIDTH, CELL_HEIGHT); // Draw a rectangle for the plant
                        plant.render(g); // Call the render method of the plant
                    } catch (Exception e) {
                        System.err.println(
                                "Error updating plant at ("
                                        + r
                                        + ", "
                                        + c
                                        + "): "
                                        + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
    }

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
                        // paint the background to rgb code
                        g.setColor(java.awt.Color.decode("#222222")); // Sky blue background
                        g.fillRect(0, 0, width, height);
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

                            // render the plant grid
                            render_plant_grid(g);
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
        // Example of placing a plant in the grid
        Plant sunflower = new Plant();
        boolean placed = placePlant(sunflower, 0, 0); // Place sunflower
        if (placed) {
            System.out.println("Sunflower placed at (0, 0) in the grid.");
        } else {
            System.out.println("Failed to place sunflower at (0, 0) in the grid.");
        }
        // Example of placing another plant
        Plant peashooter = new Plant();
        boolean placedPeashooter = placePlant(peashooter, 1, 1);
        if (placedPeashooter) {
            System.out.println("Peashooter placed at (1, 1) in the grid.");
        } else {
            System.out.println("Failed to place Peashooter at (1, 1) in the grid.");
        }
    }

    public void update() {
        // Update game state, handle input, etc.
        // System.out.println("Game updated.");

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
