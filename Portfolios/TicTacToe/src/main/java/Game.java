

import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private final Board board;
    private char currentPlayer;

    public Game(Scanner scanner) {
        this.scanner = scanner;
        this.board = new Board();
        this.currentPlayer = 'X';
    }

    public void start() {
        System.out.println("Welcome to Tic-Tac-Toe!");
        do {
            playGame();
        } while (askPlayAgain());
        System.out.println("\nGoodbye!");
    }

    private void playGame() {
        board.reset();
        currentPlayer = 'X';
        board.print();

        while (true) {
            int move = getMove();
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

    private int getMove() {
        while (true) {
            System.out.print("What is your move?  ");
            String line = scanner.nextLine();

            // Reject blank / whitespace-only input
            if (line.isBlank()) {
                printInvalid();
                continue;
            }

            String trimmed = line.trim();

            // Must be all digits (reject decimals, symbols, letters, whitespace inside)
            if (!trimmed.matches("\\d+")) {
                printInvalid();
                continue;
            }

            int cell;
            try {
                cell = Integer.parseInt(trimmed);
            } catch (NumberFormatException e) {
                printInvalid();
                continue;
            }

            if (cell < 1 || cell > 9) {
                printInvalid();
                continue;
            }

            if (!board.isCellAvailable(cell)) {
                printInvalid();
                continue;
            }

            return cell;
        }
    }

    private boolean askPlayAgain() {
        while (true) {
            System.out.print("Would you like to play again (yes/no)?  ");
            String line = scanner.nextLine();

            if (line.isBlank()) {
                printInvalidEntry();
                continue;
            }

            String trimmed = line.trim().toLowerCase();

            if (trimmed.equals("yes")) return true;
            if (trimmed.equals("no")) return false;

            printInvalidEntry();
        }
    }

    private void printInvalid() {
        System.out.println("\nThat is not a valid move! Try again.\n");
    }

    private void printInvalidEntry() {
        System.out.println("\nThat is not a valid entry!\n");
    }
}
