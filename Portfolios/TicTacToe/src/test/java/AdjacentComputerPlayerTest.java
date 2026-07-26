
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AdjacentComputerPlayerTest {

    @Test
    public void takesRightCellWhenAvailable() {
        Board board = new Board();
        board.mark(1, 'X'); // previous move was cell 1; cell 2 (right) is open
        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());

        int move = player.getMove(board, 'O', 'X');

        assertEquals(2, move);
    }

    @Test
    public void takesLeftCellWhenRightIsUnavailable() {
        Board board = new Board();
        board.mark(2, 'X');
        board.mark(3, 'O'); // right of 2 (cell 3) is taken; left (cell 1) is open
        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());

        int move = player.getMove(board, 'O', 'X');

        assertEquals(1, move);
    }

    @Test
    public void takesAboveCellWhenRightAndLeftAreUnavailable() {
        Board board = new Board();
        board.mark(5, 'X');
        board.mark(6, 'O'); // right of 5
        board.mark(4, 'O'); // left of 5
        board.mark(5, 'X'); // re-mark so lastMove is still 5 after these setup moves
        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());

        int move = player.getMove(board, 'O', 'X');

        assertEquals(2, move); // cell above 5 is 2
    }

    @Test
    public void takesBelowCellWhenRightLeftAndAboveAreUnavailable() {
        Board board = new Board();
        board.mark(2, 'O'); // above 5, will be taken
        board.mark(6, 'O'); // right of 5, will be taken
        board.mark(4, 'O'); // left of 5, will be taken
        board.mark(5, 'X'); // previous move is now 5

        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());
        int move = player.getMove(board, 'O', 'X');

        assertEquals(8, move); // cell below 5 is 8
    }

    @Test
    public void rightCellDoesNotWrapAroundToNextRow() {
        Board board = new Board();
        board.mark(3, 'X'); // rightmost column, no cell to the right
        board.mark(2, 'O'); // left of 3 is taken
        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());

        int move = player.getMove(board, 'O', 'X');

        // Above 3 does not exist (top row); below 3 is cell 6.
        assertEquals(6, move);
    }

    @Test
    public void leftCellDoesNotWrapAroundToPreviousRow() {
        Board board = new Board();
        board.mark(4, 'X'); // leftmost column, no cell to the left
        board.mark(5, 'O'); // right of 4 is taken
        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());

        int move = player.getMove(board, 'O', 'X');

        // Above 4 is cell 1.
        assertEquals(1, move);
    }

    @Test
    public void fallsBackToRandomWhenNoAdjacentCellsAreAvailable() {
        Board board = new Board();
        board.mark(5, 'X');
        board.mark(6, 'O'); // right
        board.mark(4, 'O'); // left
        board.mark(2, 'O'); // above
        board.mark(8, 'O'); // below
        board.mark(5, 'X'); // restore lastMove to 5

        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random(42));
        int move = player.getMove(board, 'O', 'X');

        assertTrue(board.isCellAvailable(move) || move == 1 || move == 3 || move == 7 || move == 9);
    }

    @Test
    public void picksRandomlyWhenNoPreviousMoveExists() {
        Board board = new Board();
        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random(1));

        int move = player.getMove(board, 'X', 'O');

        assertTrue(move >= 1 && move <= 9);
        assertTrue(board.isCellAvailable(move));
    }

    @Test
    public void neverReturnsAnUnavailableCell() {
        Board board = new Board();
        board.mark(1, 'X');
        board.mark(2, 'O');
        board.mark(4, 'X');
        board.mark(5, 'O'); // lastMove is 5; right(6) open, so should take 6

        AdjacentComputerPlayer player = new AdjacentComputerPlayer(new Random());
        int move = player.getMove(board, 'X', 'O');

        assertTrue(board.isCellAvailable(move));
        assertEquals(6, move);
    }
}
