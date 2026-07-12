

import java.util.Scanner;

public class HumanPlayer implements Player {
    private final Scanner scanner;

    public HumanPlayer(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public int getMove(Board board, char symbol, char opponentSymbol) {
        while (true) {
            System.out.print("What is your move?  ");
            String line = scanner.nextLine();

            if (line.isBlank()) { printInvalid(); continue; }
            String trimmed = line.trim();
            if (!trimmed.matches("\\d+")) { printInvalid(); continue; }

            int cell;
            try {
                cell = Integer.parseInt(trimmed);
            } catch (NumberFormatException e) { printInvalid(); continue; }

            if (cell < 1 || cell > 9) { printInvalid(); continue; }
            if (!board.isCellAvailable(cell)) { printInvalid(); continue; }

            return cell;
        }
    }

    private void printInvalid() {
        System.out.println("\nThat is not a valid move! Try again.\n");
    }
}