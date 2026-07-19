package org.example;

public class App {

    public static void main(String[] args) {
        System.out.println("\nWelcome!\nI Hope You Have Lots of Fun Playing Tic-Tac-Toe!");
        boolean playAgainValue = true;
        while (playAgainValue) {
            String[][] boardSetup = new String[][]{{"  1  ", "  2  ", "  3  "}, {"  4  ", "  5  ", "  6  "}, {"  7  ", "  8  ", "  9  "}};
            Board board = new Board(boardSetup);
            Rules rules = new Rules(boardSetup, board);
            Player humanX = new Human(boardSetup, "  X  ", board);
            Player humanO = new Human(boardSetup, "  O  ", board);
            Player computerX = new ComputerPlayer(boardSetup, "  X  ", board, rules);
            Player computerO = new ComputerPlayer(boardSetup, "  O  ", board, rules);
            TTT ttt = new TTT(board, humanX, humanO,computerX,computerO, rules, boardSetup);
            ttt.startGame();
            playAgainValue = PlayAgain.checkYesNo();
        }
        PrintScore.winAndTieToFile();
        System.out.println("I Hope You Had Fun!\nGoodbye!");
    }
}