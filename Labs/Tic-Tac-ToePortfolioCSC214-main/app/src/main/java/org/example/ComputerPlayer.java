package org.example;

import java.util.Objects;
import java.util.Random;

public class ComputerPlayer implements Player {

    String[][] board;
    Board boardSetup;
    Rules rules;
    String symbol;
    Random rand = new Random();
    int count = 0;
    public ComputerPlayer(String[][] inBoard, String playerSymbol, Board inBoardSetup, Rules rules) {
        board = inBoard;
        symbol = playerSymbol;
        boardSetup = inBoardSetup;
        this.rules = rules;
    }

    @Override
    public void move() {
        count++;
        if (Rules.playerXPlaysNext && count == 1) {
            firstMove();
            return;
        }else if (Objects.equals(board[1][1], "  5  ") && count == 1) {
            board[1][1] = symbol;
            printComputerPosition(1,1);
            return;
        }
        if (winCheck()) {
            return;
        }
        if (blockCheck()) {
            return;
        }
        randomPosition();
    }

    private void firstMove() {
        int randomNumber = rand.nextInt(4) + 1;
        switch (randomNumber) {
            case 1:
                board[0][0] = symbol;
                printComputerPosition(0,0);
                break;
            case 2:
                board[0][2] = symbol;
                printComputerPosition(0,2);
                break;
            case 3:
                board[2][0] = symbol;
                printComputerPosition(2,0);
                break;
            case 4:
                board[2][2] = symbol;
                printComputerPosition(2,2);
                break;
            default:
                System.out.println("Random num did work:" + randomNumber);
        }
    }

    public boolean winCheck() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!Objects.equals(board[i][j], "  X  ") && !Objects.equals(board[i][j], "  O  ")) {
                    board[i][j] = symbol;
                    if (rules.hasContestantWon(board, symbol)) {
                        printComputerPosition(i,j);
                        return true;
                    }
                    replaceNumber(i, j);
                }
            }
        }
        return false;
    }

    public void replaceNumber(int row, int col) {
        int number = row * 3 + col + 1;
        board[row][col] = "  " + number + "  ";
    }

    public boolean blockCheck() {
        String opponentSymbol = (Objects.equals(symbol, "  X  ")) ? "  O  " : "  X  ";
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!Objects.equals(board[i][j], "  X  ") && !Objects.equals(board[i][j], "  O  ")) {
                    board[i][j] = opponentSymbol;
                    if (rules.hasContestantWon(board, opponentSymbol)) {
                        board[i][j] = symbol;
                        printComputerPosition(i,j);
                        return true;
                    }
                    replaceNumber(i, j);
                }
            }
        }
        return false;
    }

    void randomPosition() {
        while (true) {
            int i = rand.nextInt(3);
            int j = rand.nextInt(3);

            if (!Objects.equals(board[i][j], "  X  ") && !Objects.equals(board[i][j], "  O  ")) {
                board[i][j] = symbol;
                printComputerPosition(i,j);
                return;
            }
        }
    }

    public void printComputerPosition(int row, int col){
        int number = row * 3 + col + 1;
        System.out.println("The computer put" + symbol + "on position:" + number);
    }
}
