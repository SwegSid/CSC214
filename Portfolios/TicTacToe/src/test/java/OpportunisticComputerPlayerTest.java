

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class OpportunisticComputerPlayerTest {

    private Board board;
    private OpportunisticComputerPlayer computer;

    @BeforeEach
    void setUp() {
        board = new Board();
        // Fixed seed so "random" choices are deterministic for testing
        computer = new OpportunisticComputerPlayer(new Random(42));
    }

    // --- Rule 1: first move takes a corner ---

    @Test
    void firstMoveTakesACorner() {
        int move = computer.getMove(board, 'X', 'O');
        assertTrue(move == 1 || move == 3 || move == 7 || move == 9,
                "First move should be a corner, but was " + move);
    }

    // --- Rule 2: second move takes center if available ---

    @Test
    void secondMoveTakesCenterWhenAvailable() {
        board.mark(1, 'X'); // simulate the first move already made
        int move = computer.getMove(board, 'O', 'X');
        assertEquals(5, move);
    }

    @Test
    void secondMoveFallsThroughWhenCenterUnavailable() {
        board.mark(1, 'X');
        board.mark(5, 'O'); // center already taken
        int move = computer.getMove(board, 'X', 'O');
        assertNotEquals(5, move);
        assertTrue(board.isCellAvailable(move) || move == 1);
    }

    // --- Rule 3: take a winning move ---

    @Test
    void takesWinningMoveWhenAvailable() {
        board.mark(1, 'X');
        board.mark(2, 'X');
        board.mark(9, 'O');
        board.mark(5, 'O');
        // X can win by playing 3
        int move = computer.getMove(board, 'X', 'O');
        assertEquals(3, move);
    }

    @Test
    void winningMoveTakesPriorityOverBlocking() {
        // Computer (O) can both win and block; winning should take priority
        board.mark(4, 'O');
        board.mark(5, 'O');
        // O wins by playing 6
        board.mark(1, 'X');
        board.mark(2, 'X');
        // X threatens to win by playing 3, but O should still win instead
        int move = computer.getMove(board, 'O', 'X');
        assertEquals(6, move);
    }

    // --- Rule 4: block opponent's winning move ---

    @Test
    void blocksOpponentWinningMove() {
        board.mark(1, 'X');
        board.mark(2, 'X');
        board.mark(9, 'O');
        // X threatens to win at 3; O has no winning move of its own
        int move = computer.getMove(board, 'O', 'X');
        assertEquals(3, move);
    }

    // --- Rule 5: random move when no other rule applies ---

    @Test
    void makesAvailableRandomMoveWhenNoRuleApplies() {
        board.mark(1, 'X');
        board.mark(5, 'O');
        board.mark(9, 'X');
        board.mark(3, 'O');
        // No immediate win or block exists for either symbol here
        int move = computer.getMove(board, 'X', 'O');
        assertTrue(board.isCellAvailable(move), "Move should land on an available cell");
    }

    @Test
    void neverChoosesAnAlreadyOccupiedCell() {
        board.mark(1, 'X');
        board.mark(2, 'O');
        board.mark(3, 'X');
        board.mark(4, 'O');
        board.mark(5, 'X');
        board.mark(6, 'O');
        board.mark(7, 'X');
        // Only cell 8 and 9 remain, and 9 is not a winning/blocking cell for either
        int move = computer.getMove(board, 'O', 'X');
        assertTrue(board.isCellAvailable(move));
    }
}
