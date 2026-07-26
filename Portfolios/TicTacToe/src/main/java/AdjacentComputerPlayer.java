
import java.util.List;
import java.util.Random;

public class AdjacentComputerPlayer implements Player {
    private final Random random;

    public AdjacentComputerPlayer() {
        this.random = new Random();
    }

    // Allows deterministic testing by injecting a seeded Random.
    public AdjacentComputerPlayer(Random random) {
        this.random = random;
    }

    @Override
    public int getMove(Board board, char symbol, char opponentSymbol) {
        int previousMove = board.getLastMove();

        // No previous move has been made yet (this player is going first) ->
        // there is nothing to be "adjacent" to, so just pick randomly.
        if (previousMove == 0) {
            return chooseRandomAvailable(board);
        }

        Integer right = rightOf(previousMove);
        if (right != null && board.isCellAvailable(right)) {
            return right;
        }

        Integer left = leftOf(previousMove);
        if (left != null && board.isCellAvailable(left)) {
            return left;
        }

        Integer above = aboveOf(previousMove);
        if (above != null && board.isCellAvailable(above)) {
            return above;
        }

        Integer below = belowOf(previousMove);
        if (below != null && board.isCellAvailable(below)) {
            return below;
        }

        return chooseRandomAvailable(board);
    }

    /**
     * Returns the cell directly to the right of the given cell, or null if
     * the given cell is in the rightmost column (no cell exists to its right).
     */
    private Integer rightOf(int cell) {
        // Cells 3, 6, and 9 are in the rightmost column.
        if (cell % 3 == 0) {
            return null;
        }
        return cell + 1;
    }

    /**
     * Returns the cell directly to the left of the given cell, or null if
     * the given cell is in the leftmost column (no cell exists to its left).
     */
    private Integer leftOf(int cell) {
        // Cells 1, 4, and 7 are in the leftmost column.
        if (cell % 3 == 1) {
            return null;
        }
        return cell - 1;
    }

    /**
     * Returns the cell directly above the given cell, or null if the given
     * cell is in the top row (no cell exists above it).
     */
    private Integer aboveOf(int cell) {
        // Cells 1, 2, and 3 are in the top row.
        if (cell <= 3) {
            return null;
        }
        return cell - 3;
    }

    /**
     * Returns the cell directly below the given cell, or null if the given
     * cell is in the bottom row (no cell exists below it).
     */
    private Integer belowOf(int cell) {
        // Cells 7, 8, and 9 are in the bottom row.
        if (cell >= 7) {
            return null;
        }
        return cell + 3;
    }

    private int chooseRandomAvailable(Board board) {
        List<Integer> available = board.getAvailableCells();
        return available.get(random.nextInt(available.size()));
    }
}
