import model.Ladder;
import model.Player;
import model.Snake;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Game {

    private final int boardSize;
    private final List<Player> players;
    private final List<Snake> snakes;
    private final List<Ladder> ladders;
    private Boolean gameWon;
    private Random random;

    private HashMap<Integer, Snake> snakeHashMap;

    private HashMap<Integer, Ladder> ladderHashMap;

    public Game(int boardSize, int players, int snakes, int ladder) {
        this.boardSize = boardSize;
        this.players = new ArrayList<>(players);
        this.snakes = new ArrayList<>(snakes);
        this.ladders = new ArrayList<>(ladder);
        this.snakeHashMap = HashMap.newHashMap(snakes);
        this.ladderHashMap = HashMap.newHashMap(ladder);
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
            snakes.add(newSnake);
        }

        for (int i = 0; i < ladder; i++) {
            int start = random.nextInt(5, boardSize - 2);
            int end = random.nextInt(start + 1, boardSize);
            Ladder newLadder = new Ladder(start, end);
            ladderHashMap.put(start, newLadder);
            ladders.add(newLadder);
        }

        for (int i = 0; i < player; i++) {
            String name = "Player-" + i;
            Player newplayer = new Player(name);
            players.add(newplayer);
        }

        System.out.println("Game initialised");
    }

    public int rollDice() {
        return random.nextInt(6) + 1;
    }

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

    public boolean isGameOwn(Player player) {
        return player.getPosition() == boardSize;
    }

    public void playGame() {
        while (Boolean.FALSE.equals(gameWon)) {
//            System.out.println("Playing game started"+ players.size());
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
