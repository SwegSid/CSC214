
import java.util.ArrayList;
import java.util.List;

public class Board {
    private static final int[][] WINS = {
            {0,1,2},{3,4,5},{6,7,8},
            {0,3,6},{1,4,7},{2,5,8},
            {0,4,8},{2,4,6}
    };

    private final char[] cells;

    public Board() {
        cells = new char[9];
        for (int i = 0; i < 9; i++) cells[i] = (char) ('1' + i);
    }

    public void reset() {
        for (int i = 0; i < 9; i++) cells[i] = (char) ('1' + i);
    }

    public boolean isCellAvailable(int cell) {
        return cells[cell - 1] != 'X' && cells[cell - 1] != 'O';
    }

    public void mark(int cell, char symbol) {
        cells[cell - 1] = symbol;
    }

    public void unmark(int cell) {
        cells[cell - 1] = (char) ('1' + (cell - 1));
    }

    public boolean isEmpty() {
        return countFilled() == 0;
    }

    public int countFilled() {
        int count = 0;
        for (char c : cells) if (c == 'X' || c == 'O') count++;
        return count;
    }

    public List<Integer> getAvailableCells() {
        List<Integer> available = new ArrayList<>();
        for (int cell = 1; cell <= 9; cell++) {
            if (isCellAvailable(cell)) available.add(cell);
        }
        return available;
    }

    public boolean wouldWin(int cell, char symbol) {
        if (!isCellAvailable(cell)) return false;
        mark(cell, symbol);
        boolean result = hasWinner(symbol);
        unmark(cell);
        return result;
    }

    public boolean hasWinner() {
        for (int[] line : WINS) {
            if (cells[line[0]] == cells[line[1]] && cells[line[1]] == cells[line[2]]) return true;
        }
        return false;
    }

    public boolean hasWinner(char symbol) {
        for (int[] line : WINS) {
            if (cells[line[0]] == symbol && cells[line[1]] == symbol && cells[line[2]] == symbol) return true;
        }
        return false;
    }

    public boolean isFull() {
        for (char c : cells) if (c != 'X' && c != 'O') return false;
        return true;
    }

    public void print() {
        System.out.println();
        System.out.printf("    %c  |  %c  |  %c%n", cells[0], cells[1], cells[2]);
        System.out.println("  -----+-----+-----");
        System.out.printf("    %c  |  %c  |  %c%n", cells[3], cells[4], cells[5]);
        System.out.println("  -----+-----+-----");
        System.out.printf("    %c  |  %c  |  %c%n", cells[6], cells[7], cells[8]);
        System.out.println();
    }
}