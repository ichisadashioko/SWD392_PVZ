package pvz;

public class Game {
    public Game() {
        // Initialize game state, load resources, etc.
    }

    public void start() {
        // Start the game logic, initialize game objects, etc.
        System.out.println("Game started.");
    }

    public void update() {
        // Update game state, handle input, etc.
        System.out.println("Game updated.");
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
