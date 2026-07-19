package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    public void testBoardInitialization() {
        String[][] initialBoard = {{"  1  ", "  2  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Board board = new Board(initialBoard);
        assertArrayEquals(initialBoard, board.board);
    }

    @Test
    public void testPrintBoard() {
        String[][] initialBoard = {{"  1  ", "  2  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Board board = new Board(initialBoard);
        assertDoesNotThrow(() -> board.printBoard(initialBoard));
    }
}