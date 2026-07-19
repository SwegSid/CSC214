package org.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PrintScore {
    static int tieCounter = 0;
    static int playerXWinCounter = 0;
    static int playerOWinCounter = 0;

    static public void winAndTieCountDisplay(){
        System.out.printf("\nPlayer X Wins   %d", playerXWinCounter);
        System.out.printf("\nPlayer O Wins   %d", playerOWinCounter);
        System.out.printf("\nTies            %d%n", tieCounter);
    }

    static public void winAndTieToFile(){
        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(Paths.get("game.txt")))) {
            writer.printf("Player X Wins   %d", playerXWinCounter);
            writer.printf("\nPlayer O Wins   %d", playerOWinCounter);
            writer.printf("\nTies            %d%n", tieCounter);

            System.out.println("\nWriting the game log to disk. Please see game.txt for the final statistics!\n");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
