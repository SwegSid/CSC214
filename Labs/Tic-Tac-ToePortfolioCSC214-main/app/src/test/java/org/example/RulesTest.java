package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RulesTest {

    @Test
    public void testHasContestantWon_Rows() {
        String[][] board = {{"  X  ", "  X  ", "  X  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Rules rules = new Rules(board, new Board(board));
        assertTrue(rules.hasContestantWon(board, "  X  "));
    }

    @Test
    public void testHasContestantWon_Columns() {
        String[][] board = {{"  O  ", "  2  ", "  3  "}, {"  O  ", "  5  ", "  6  "}, {"  O  ", "  8  ", "  9  "}};
        Rules rules = new Rules(board, new Board(board));
        assertTrue(rules.hasContestantWon(board, "  O  "));
    }

    @Test
    public void testHasContestantWon_Diagonals() {
        String[][] board = {{"  X  ", "  2  ", "  3  "}, {"  4  ", "  X  ", "  6  "}, {"  7  ", "  8  ", "  X  "}};
        Rules rules = new Rules(board, new Board(board));
        assertTrue(rules.hasContestantWon(board, "  X  "));
    }

    @Test
    public void testIsGameFinished_Win() {
        String[][] board = {{"  X  ", "  X  ", "  X  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
        Rules rules = new Rules(board, new Board(board));
        assertTrue(rules.isGameFinished(board));
    }

    @Test
    public void testIsGameFinished_Tie() {
        String[][] board = {{"  X  ", "  O  ", "  X  "}, {"  O  ", "  X  ", "  O  "}, {"  O  ", "  X  ", "  O  "}};
        Rules rules = new Rules(board, new Board(board));
        assertTrue(rules.isGameFinished(board));
    }

    @Test
    public void testIsGameFinished_NotFinished() {
        String[][] board = {{"  1  ", "  O  ", "  X  "}, {"  4  ", "  X  ", "  O  "}, {"  7  ", "  8  ", "  9  "}};
        Rules rules = new Rules(board, new Board(board));
        assertFalse(rules.isGameFinished(board));
    }

    @Test
    public void testSetPlayerXPlaysNext() {
        Rules.setPlayerXPlaysNext(false);
        assertFalse(Rules.playerXPlaysNext);
        Rules.setPlayerXPlaysNext(true);
        assertTrue(Rules.playerXPlaysNext);
    }
}