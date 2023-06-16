import model.Ladder;
import model.Player;
import model.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * The Game class represents a game board and the players involved in the game.
 */
public class Game {

    private final int boardSize;
    private final List<Player> players;
    private Boolean gameWon;
    private Random random;

    private HashMap<Integer, Snake> snakeHashMap;

    private HashMap<Integer, Ladder> ladderHashMap;

    /**
     * Constructs a new Game object with the specified board size, number of players, number of snakes, and number of ladders.
     *
     * @param boardSize the size of the game board
     * @param players the number of players in the game
     * @param snakes the number of snakes in the game
     * @param ladder the number of ladders in the game
     */
    public Game(int boardSize, int players, int snakes, int ladder) {
        this.boardSize = boardSize;
        this.players = new ArrayList<>(players);
        this.snakeHashMap = new HashMap<>(snakes);
        this.ladderHashMap = new HashMap<>(ladder);
        this.gameWon = Boolean.FALSE;
        random = new Random(Integer.MAX_VALUE);
        this.initialiseGame(players, snakes, ladder);
    }

    private void initialiseGame(int player, int snake, int ladder) {

        for (int i = 0; i < snake; i++) {
            int tail = random.nextInt(5, 80);
            int head = random.nextInt(tail + 1, boardSize - 1);
            Snake newSnake = new Snake(head, tail);
            snakeHashMap.put(head, newSnake);
        }

        for (int i = 0; i < ladder; i++) {
            int start = random.nextInt(5, boardSize - 2);
            int end = random.nextInt(start + 1, boardSize);
            Ladder newLadder = new Ladder(start, end);
            ladderHashMap.put(start, newLadder);
        }

        for (int i = 0; i < player; i++) {
            String name = "Player-" + i;
            Player newplayer = new Player(name);
            players.add(newplayer);
        }

        System.out.println("Game initialised");
    }

    /**
     * Rolls the dice and returns the value obtained.
     *
     * @return the value obtained from rolling the dice
     */
    public int rollDice() {
        return random.nextInt(6) + 1;
    }

    /**
     * Calculates the new position on the game board based on the current position and the dice value.
     *
     * @param currentPosition the current position of the player
     * @param diceValue the value obtained from rolling the dice
     * @return the new position on the game board
     */
    public int newPosition(int currentPosition, int diceValue) {
        int newPosition = currentPosition + diceValue;

        if (newPosition > boardSize) {
            return currentPosition;
        }

        if (snakeHashMap.containsKey(newPosition)) {
            Snake snake = snakeHashMap.get(newPosition);
            return snake.getTail();
        }

        if (ladderHashMap.containsKey(newPosition)) {
            Ladder ladder = ladderHashMap.get(newPosition);
            return ladder.getEnd();
        }

        return newPosition;
    }

    /**
     * Checks if the specified player has won the game.
     *
     * @param player the player to check
     * @return true if the player has won the game, false otherwise
     */
    public boolean isGameOwn(Player player) {
        return player.getPosition() == boardSize;
    }

    /**
     * Plays the game until a player wins.
     */
    public void playGame() {
        while (Boolean.FALSE.equals(gameWon)) {
            for (Player player : players) {

                int diceValue = rollDice();
                int currentPosition = player.getPosition();
                int newPosition = newPosition(currentPosition, diceValue);
                player.setPosition(newPosition);
                System.out.println("Player -" + player.getName() + " rolled a dice - " + diceValue + " and its newPosition is - " + newPosition);

                if (isGameOwn(player)) {
                    System.out.println("Player -" + player.getName() + " won the game");
                    gameWon = true;
                    return;
                }
            }
        }
    }
}
