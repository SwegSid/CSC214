
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OpportunisticComputerPlayer implements Player {
    private static final int[] CORNERS = {1, 3, 7, 9};
    private static final int CENTER = 5;

    private final Random random;

    public OpportunisticComputerPlayer() {
        this.random = new Random();
    }

    public OpportunisticComputerPlayer(Random random) {
        this.random = random;
    }

    @Override
    public int getMove(Board board, char symbol, char opponentSymbol) {
        if (board.isEmpty()) {
            return chooseRandomFrom(availableCorners(board));
        }

        if (board.countFilled() == 1 && board.isCellAvailable(CENTER)) {
            return CENTER;
        }

        for (int cell : board.getAvailableCells()) {
            if (board.wouldWin(cell, symbol)) return cell;
        }

        for (int cell : board.getAvailableCells()) {
            if (board.wouldWin(cell, opponentSymbol)) return cell;
        }

        return chooseRandomFrom(board.getAvailableCells());
    }

    private List<Integer> availableCorners(Board board) {
        List<Integer> corners = new ArrayList<>();
        for (int corner : CORNERS) {
            if (board.isCellAvailable(corner)) corners.add(corner);
        }
        return corners;
    }

    private int chooseRandomFrom(List<Integer> options) {
        return options.get(random.nextInt(options.size()));
    }
}