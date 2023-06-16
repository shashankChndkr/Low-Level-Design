/**
 * The Main class is the entry point of the game application.
 */
public class Main {

    /**
     * The main method initializes and starts the game.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        System.out.println("Setting up game");

        Game game = new Game(100, 3, 20, 10);

        System.out.println("Start playing game");

        game.playGame();

    }
}
