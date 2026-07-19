package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PrintScoreTest {

    @Test
    public void testWinAndTieCountDisplay() {
        PrintScore.playerXWinCounter = 2;
        PrintScore.playerOWinCounter = 1;
        PrintScore.tieCounter = 3;

        assertDoesNotThrow(PrintScore::winAndTieCountDisplay);
    }

    @Test
    public void testWinAndTieToFile() {
        PrintScore.playerXWinCounter = 2;
        PrintScore.playerOWinCounter = 1;
        PrintScore.tieCounter = 3;

        assertDoesNotThrow(PrintScore::winAndTieToFile);
    }

    @Test
    public void testStaticCounters() {
        int originalX = PrintScore.playerXWinCounter;
        int originalO = PrintScore.playerOWinCounter;
        int originalTie = PrintScore.tieCounter;

        PrintScore.playerXWinCounter++;
        PrintScore.playerOWinCounter++;
        PrintScore.tieCounter++;

        assertEquals(originalX + 1, PrintScore.playerXWinCounter);
        assertEquals(originalO + 1, PrintScore.playerOWinCounter);
        assertEquals(originalTie + 1, PrintScore.tieCounter);

        // Reset counters
        PrintScore.playerXWinCounter = originalX;
        PrintScore.playerOWinCounter = originalO;
        PrintScore.tieCounter = originalTie;
    }
}