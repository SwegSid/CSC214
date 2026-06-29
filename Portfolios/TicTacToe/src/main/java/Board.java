

public class Board {
    private final char[] cells;

    public Board() {
        cells = new char[9];
        for (int i = 0; i < 9; i++) {
            cells[i] = (char) ('1' + i);
        }
    }

    public void reset() {
        for (int i = 0; i < 9; i++) {
            cells[i] = (char) ('1' + i);
        }
    }

    public boolean isCellAvailable(int cell) {
        // cell is 1-indexed
        return cells[cell - 1] != 'X' && cells[cell - 1] != 'O';
    }

    public void mark(int cell, char symbol) {
        cells[cell - 1] = symbol;
    }

    public boolean hasWinner() {
        int[][] wins = {
                {0,1,2},{3,4,5},{6,7,8}, // rows
                {0,3,6},{1,4,7},{2,5,8}, // cols
                {0,4,8},{2,4,6}          // diagonals
        };
        for (int[] line : wins) {
            if (cells[line[0]] == cells[line[1]] && cells[line[1]] == cells[line[2]]) {
                return true;
            }
        }
        return false;
    }

    public boolean isFull() {
        for (char c : cells) {
            if (c != 'X' && c != 'O') return false;
        }
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
