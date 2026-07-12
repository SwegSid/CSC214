

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private final Board board;
    private char currentPlayer;
    private Player playerX;
    private Player playerO;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.board = new Board();
        this.currentPlayer = 'X';
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        chooseGameMode();
        do {
            playGame();
        } while (askPlayAgain());
        System.out.println("\nGoodbye!");
    }

    private void chooseGameMode() {
        System.out.println("\nWhat kind of game would you like to play?\n");
        System.out.println("1. Human vs. Human");
        System.out.println("2. Human vs. Computer");
        System.out.println("3. Computer vs. Human");
        System.out.println();

        int selection = getMenuSelection();

        switch (selection) {
            case 1:
                playerX = new HumanPlayer(scanner);
                playerO = new HumanPlayer(scanner);
                break;
            case 2:
                playerX = new HumanPlayer(scanner);
                playerO = new OpportunisticComputerPlayer();
                System.out.println("\nGreat! You will go first.");
                break;
            case 3:
                playerX = new OpportunisticComputerPlayer();
                playerO = new HumanPlayer(scanner);
                System.out.println("\nGreat! The computer will go first.");
                break;
        }
    }

    private int getMenuSelection() {
        while (true) {
            System.out.print("What is your selection? ");
            String line = scanner.nextLine();

            if (line.isBlank()) { printInvalidSelection(); continue; }
            String trimmed = line.trim();
            if (!trimmed.matches("\\d+")) { printInvalidSelection(); continue; }

            int selection;
            try {
                selection = Integer.parseInt(trimmed);
            } catch (NumberFormatException e) { printInvalidSelection(); continue; }

            if (selection < 1 || selection > 3) { printInvalidSelection(); continue; }
            return selection;
        }
    }

    private void printInvalidSelection() {
        System.out.println("\nThat is not a valid selection! Try again.\n");
    }

    private void playGame() {
        board.reset();
        currentPlayer = 'X';
        board.print();

        while (true) {
            Player active = (currentPlayer == 'X') ? playerX : playerO;
            char opponentSymbol = (currentPlayer == 'X') ? 'O' : 'X';

            int move = active.getMove(board, currentPlayer, opponentSymbol);
            board.mark(move, currentPlayer);
            board.print();

            if (board.hasWinner()) {
                System.out.println("Player " + currentPlayer + " wins!");
                System.out.println();
                return;
            }
            if (board.isFull()) {
                System.out.println("It's a draw!");
                System.out.println();
                return;
            }
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean askPlayAgain() {
        while (true) {
            System.out.print("Would you like to play again (yes/no)?  ");
            String line = scanner.nextLine();

            if (line.isBlank()) { printInvalidEntry(); continue; }
            String trimmed = line.trim().toLowerCase();

            if (trimmed.equals("yes")) return true;
            if (trimmed.equals("no")) return false;

            printInvalidEntry();
        }
    }

    private void printInvalidEntry() {
        System.out.println("\nThat is not a valid entry!\n");
    }
}