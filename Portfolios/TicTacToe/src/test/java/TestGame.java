

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestGame {

    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    // --- isCellAvailable ---

    @Test
    void cellsAreAvailableOnNewBoard() {
        for (int i = 1; i <= 9; i++) {
            assertTrue(board.isCellAvailable(i), "Cell " + i + " should be available on a new board");
        }
    }

    @Test
    void cellNotAvailableAfterMarkingX() {
        board.mark(5, 'X');
        assertFalse(board.isCellAvailable(5));
    }

    @Test
    void cellNotAvailableAfterMarkingO() {
        board.mark(1, 'O');
        assertFalse(board.isCellAvailable(1));
    }

    // --- hasWinner ---

    @Test
    void noWinnerOnNewBoard() {
        assertFalse(board.hasWinner());
    }

    @Test
    void detectsTopRowWin() {
        board.mark(1, 'X');
        board.mark(2, 'X');
        board.mark(3, 'X');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsMiddleRowWin() {
        board.mark(4, 'O');
        board.mark(5, 'O');
        board.mark(6, 'O');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsBottomRowWin() {
        board.mark(7, 'X');
        board.mark(8, 'X');
        board.mark(9, 'X');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsLeftColumnWin() {
        board.mark(1, 'O');
        board.mark(4, 'O');
        board.mark(7, 'O');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsMiddleColumnWin() {
        board.mark(2, 'X');
        board.mark(5, 'X');
        board.mark(8, 'X');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsRightColumnWin() {
        board.mark(3, 'O');
        board.mark(6, 'O');
        board.mark(9, 'O');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsTopLeftDiagonalWin() {
        board.mark(1, 'X');
        board.mark(5, 'X');
        board.mark(9, 'X');
        assertTrue(board.hasWinner());
    }

    @Test
    void detectsTopRightDiagonalWin() {
        board.mark(3, 'O');
        board.mark(5, 'O');
        board.mark(7, 'O');
        assertTrue(board.hasWinner());
    }

    @Test
    void noWinnerWithMixedSymbols() {
        // X O X / O X O / O X O — no three in a row same symbol
        board.mark(1, 'X'); board.mark(2, 'O'); board.mark(3, 'X');
        board.mark(4, 'O'); board.mark(5, 'X'); board.mark(6, 'O');
        board.mark(7, 'O'); board.mark(8, 'X'); board.mark(9, 'O');
        assertFalse(board.hasWinner());
    }

    // --- isFull ---

    @Test
    void boardNotFullOnStart() {
        assertFalse(board.isFull());
    }

    @Test
    void boardNotFullWhenPartiallyFilled() {
        board.mark(1, 'X');
        board.mark(2, 'O');
        assertFalse(board.isFull());
    }

    @Test
    void boardIsFullWhenAllCellsMarked() {
        char[] symbols = {'X','O','X','O','X','O','O','X','O'};
        for (int i = 1; i <= 9; i++) {
            board.mark(i, symbols[i - 1]);
        }
        assertTrue(board.isFull());
    }

    // --- reset ---

    @Test
    void resetClearsAllMarks() {
        board.mark(1, 'X');
        board.mark(5, 'O');
        board.mark(9, 'X');
        board.reset();
        for (int i = 1; i <= 9; i++) {
            assertTrue(board.isCellAvailable(i), "Cell " + i + " should be available after reset");
        }
    }

    @Test
    void resetClearsWinState() {
        board.mark(1, 'X');
        board.mark(2, 'X');
        board.mark(3, 'X');
        assertTrue(board.hasWinner());
        board.reset();
        assertFalse(board.hasWinner());
    }
}
