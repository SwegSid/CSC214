package org.example;

import java.util.Scanner;

public class TTT {
    Board board;
    Player humanX;
    Player humanO;
    Player computerX;
    Player computerO;
    Rules rules;
    String[][] currentBoard;
    String gameTypeInput = "0";
    Scanner scanner = new Scanner(System.in);

    public TTT(Board board, Player humanX, Player humanO, Player computerX, Player computerO, Rules rules, String[][] boardSetup) {
        this.board = board;
        this.humanX = humanX;
        this.humanO = humanO;
        this.computerX = computerX;
        this.computerO = computerO;
        this.rules = rules;
        currentBoard = boardSetup;
    }

    public void startGame() {
        while (true) {
            System.out.println("\nWhat kind of game would you like to play?");
            System.out.println("\n1. Human vs. Human");
            System.out.println("2. Human vs. Computer");
            System.out.println("3. Computer vs. Human");
            System.out.println("what is your selection?");
            gameTypeInput = scanner.nextLine();
            switch (gameTypeInput) {
                case "1":
                    HumanVsHuman();
                    return;
                case "2":
                    HumanVsComputer();
                    return;
                case "3":
                    ComputerVsHuman();
                    return;
                default:
                    System.out.println(gameTypeInput + " is not a valid Option.");
            }
        }
    }

    public void HumanVsHuman() {
        board.printBoard(currentBoard);
        Rules.setPlayerXPlaysNext(true);
        while (true) {
            if (Rules.playerXPlaysNext) {
                humanX.move();
                if (rules.isGameFinished(currentBoard)) {
                    break;
                }
                Rules.playerXPlaysNext = false;
                board.printBoard(currentBoard);
            }
            if (!Rules.playerXPlaysNext) {
                humanO.move();
                if (rules.isGameFinished(currentBoard)) {
                    break;
                }
                Rules.playerXPlaysNext = true;
                board.printBoard(currentBoard);
            }
        }
    }

    public void HumanVsComputer() {
        board.printBoard(currentBoard);
        Rules.setPlayerXPlaysNext(true);
        while (true) {
            if (Rules.playerXPlaysNext) {
                humanX.move();
                if (rules.isGameFinished(currentBoard)) {
                    break;
                }
                Rules.playerXPlaysNext = false;
                board.printBoard(currentBoard);
            }
            if (!Rules.playerXPlaysNext) {
                computerO.move();
                if (rules.isGameFinished(currentBoard)) {
                    break;
                }
                Rules.playerXPlaysNext = true;
                board.printBoard(currentBoard);
            }
        }
    }

    public void ComputerVsHuman() {
        board.printBoard(currentBoard);
        Rules.setPlayerXPlaysNext(true);
        while (true) {
            if (Rules.playerXPlaysNext) {
                computerX.move();
                if (rules.isGameFinished(currentBoard)) {
                    break;
                }
                Rules.playerXPlaysNext = false;
                board.printBoard(currentBoard);
            }
            if (!Rules.playerXPlaysNext) {
                humanO.move();
                if (rules.isGameFinished(currentBoard)) {
                    break;
                }
                Rules.playerXPlaysNext = true;
                board.printBoard(currentBoard);
            }
        }
    }
}
