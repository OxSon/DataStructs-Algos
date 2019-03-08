package eightPuzzle;

/**
 * Represents an 8-Puzzle board
 *
 * @author Alec Mills
 * @author Chau Pham
 */
public class Board {
    private int[] boardFlat;
    private int size;

    /**
     * Construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j).
     * Valid integers are between 0 and (N^2) - 1, where zero represents the empty space
     *
     * @param blocks initial layout of blocks
     */
    public Board(int[][] blocks) {
        /*PERFORMARNCE REQS:
         * all methods take time proportional to N^2 or better,
         * with the exception of isSolveable() which may take up to N^4 in the worst case
         */
        //TODO
        size = blocks.length;

        //transfer data to our internal representation
        boardFlat = new int[size * size];
        int k = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardFlat[k++] = blocks[i][j];
            }
        }
    }

    /**
     * Board size N (i.e. a 3 by 3 board with 9 total spaces would be size 3)
     *
     * @return board size N
     */
    public int size() {
        //TODO
        return 0;
    }

    /**
     * Hamming priority heuristic; the number of blocks out of place.
     *
     * @return the number of blocks out of place
     */
    public int hamming() {
        //TODO
        return 0;
    }

    /**
     * Manhattan priority heuristic;
     * sum of vertical and horizontal distances between
     * blocks and their respective goal positions
     *
     * @return sum of Manhattan distances between blocks and goal
     */
    public int manhattan() {
        //TODO
        return 0;
    }

    /**
     * Is this board the goal board?
     *
     * @return true if this board is the goal board false otherwise
     */
    public boolean isGoal() {
        //TODO
        return false;
    }

    /**
     * Is this board solvable? Uses inversions method to determine this.
     *
     * @return true if board is solvable false otherwise
     */
    public boolean isSolveable() {
        //TODO
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        //TODO
        return super.equals(obj);
    }

    public Iterable<Board> neighbors() {
        //TODO
        return null;
    }

    public String toString() {
        return String.format("%d\n%s", size, boardString());
    }

    private String boardString() {
        var sb = new StringBuilder();

        for (int i = 0; i < boardFlat.length; i++) {
            sb.append(" ").append(boardFlat[i]);
            if ((i + 1) % size == 0)
                sb.append("\n");
        }

        return sb.toString();
    }
}
