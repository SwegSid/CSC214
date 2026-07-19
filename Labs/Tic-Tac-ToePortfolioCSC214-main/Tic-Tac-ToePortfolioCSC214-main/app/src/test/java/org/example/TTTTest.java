package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TTTTest {

    @Test
    public void testGameInitialization() {
        String[][] board = {{"  1  ", "  2  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Board boardObj = new Board(board);
        Rules rules = new Rules(board, boardObj);
        Human humanX = new Human(board, "  X  ", boardObj);
        Human humanO = new Human(board, "  O  ", boardObj);
        ComputerPlayer computerX = new ComputerPlayer(board, "  X  ", boardObj, rules);
        ComputerPlayer computerO = new ComputerPlayer(board, "  O  ", boardObj, rules);

        TTT ttt = new TTT(boardObj, humanX, humanO, computerX, computerO, rules, board);

        assertNotNull(ttt);
        assertEquals(boardObj, ttt.board);
        assertEquals(humanX, ttt.humanX);
        assertEquals(humanO, ttt.humanO);
        assertEquals(computerX, ttt.computerX);
        assertEquals(computerO, ttt.computerO);
        assertEquals(rules, ttt.rules);
        assertArrayEquals(board, ttt.currentBoard);
    }
}