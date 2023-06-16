Create a Snake and Ladder application that simulates the game. The application should accept the following inputs:

    Number of snakes (s) followed by s lines, each containing the head and tail positions of a snake.
    Number of ladders (l) followed by l lines, each containing the start and end positions of a ladder.
    Number of players (p) followed by p lines, each containing a player's name.

Once the inputs are provided, the application should execute the game and display the moves in the following format:

<player_name> rolled a <dice_value> and moved from <initial_position> to <final_position>

When a player wins the game, the application should print:

<player_name> wins the game

The rules of the game are as follows:

    The board consists of 100 cells numbered from 1 to 100.
    A six-sided dice is used, providing a random number between 1 and 6 upon rolling.
    Each player's piece starts at position 0 and moves forward based on the dice roll.
    To move, the player advances their piece by the number rolled on the dice.
    The game ends when a player reaches position 100 exactly.
    If a piece is supposed to move beyond position 100, it remains in its current position.
    Snakes and ladders are present on the board.
    When a piece lands on the head of a snake, it moves down to the tail position.
    When a piece lands on the start of a ladder, it moves up to the end position.
    Additional snakes or ladders can be present at the tail of a snake or the end position of a ladder.

Assumptions and Optional Requirements:

You can make the following assumptions in addition to the ones mentioned in the rules:

    There is no snake at position 100.
    There is no overlap of snakes and ladders at the same start/head position.
    Winning the game by reaching position 100 is possible.
    Snakes and ladders do not form an infinite loop.

Optional Requirements (to be implemented if time allows):

    Introduce two dice for a single move, allowing a total dice value between 2 and 12.
    Customize the board size by taking the size as input before other inputs (snakes, ladders, players).
    If there are more than two players, continue the game until only one player remains.
    Implement special rules for rolling a 6, such as getting an extra turn, or cancelling three consecutive 6 rolls.
    Programmatically generate snakes and ladders when starting the application, adhering to the rules mentioned.