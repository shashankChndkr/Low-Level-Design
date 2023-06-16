public class Main {


    public static void main(String[] args) {
        System.out.println("Setting up game");

        Game game = new Game(100, 3, 20, 10);

        System.out.println("Start playing game");

        game.playGame();

    }
}