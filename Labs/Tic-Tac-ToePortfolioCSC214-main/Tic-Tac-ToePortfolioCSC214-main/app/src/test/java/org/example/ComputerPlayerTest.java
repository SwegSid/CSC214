package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class ComputerPlayerTest {

    @Test
    public void testWinCheck() {
        String[][] board = {{"  X  ", "  X  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Board boardObj = new Board(board);
        Rules rules = new Rules(board, boardObj);
        ComputerPlayer computer = new ComputerPlayer(board, "  X  ", boardObj, rules);

        assertTrue(computer.winCheck());
        assertEquals("  X  ", board[0][2]);
    }

    @Test
    public void testBlockCheck() {
        String[][] board = {{"  O  ", "  O  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Board boardObj = new Board(board);
        Rules rules = new Rules(board, boardObj);
        ComputerPlayer computer = new ComputerPlayer(board, "  X  ", boardObj, rules);

        assertTrue(computer.blockCheck());
        assertEquals("  X  ", board[0][2]);
    }

    @Test
    public void testFirstMove() {
        String[][] board = {{"  1  ", "  2  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Board boardObj = new Board(board);
        Rules rules = new Rules(board, boardObj);
        ComputerPlayer computer = new ComputerPlayer(board, "  X  ", boardObj, rules);

        Rules.setPlayerXPlaysNext(true);
        computer.count = 0;
        computer.move();

        boolean movedToCorner = board[0][0].equals("  X  ") || board[0][2].equals("  X  ") ||
                board[2][0].equals("  X  ") || board[2][2].equals("  X  ");
        assertTrue(movedToCorner);
    }

}