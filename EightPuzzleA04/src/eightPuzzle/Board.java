package eightPuzzle;

import java.util.Arrays;

/**
 * Represents an 8-Puzzle board
 *
 * @author Alec Mills
 * @author Chau Pham
 */
public class Board {
    /*PERFORMARNCE REQS:
     * all methods take time proportional to N^2 or better,
     * with the exception of isSolveable() which may take up to N^4 in the worst case
     */
    private int[] boardFlat;
    private int size;
    //FIXME is it better to compute this on demand,
    // or compute once in constructor and use the extra memory for storing the array?
    // note: storing this array costs us an 4N + 32 byes of memory
    // storing the array also greatly simplifies code in a number of places and improves readability
    // my current thinking is it's worth it to store, but that may change during implementation of solver
    // as we may need to store a large number of board objects
    private int[] goal;

    /**
     * Construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j).
     * Valid integers are between 0 and (N^2) - 1, where zero represents the empty space
     *
     * @param blocks initial layout of blocks
     */
    public Board(int[][] blocks) {
        //TODO decide whether we're calculating goal here. Without, constructor takes O(N) time
        // with it, still takes O(N) time (~2N using tilde notation)
        size = blocks.length;

        //calculate goal board for later reference
        goal = new int[size * size];
        for (int i = 0; i < goal.length - 1; i++) { //add numbers in range [1, size * size - 1]
            goal[i] = i + 1;
        }
        goal[goal.length - 1] = 0; //add the blank square in bottom right

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
        return size;
    }

    /**
     * Hamming priority heuristic; the number of blocks out of place.
     *
     * @return the number of blocks out of place
     */
    public int hamming() {
        int count = 0;
        //we use boardFlat.length - 1 because we are not interested in the position of the blank space
        for (int i = 0; i < boardFlat.length - 1; i++) {
            if (goal[i] != boardFlat[i])
                count++;
        }

        return count;
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
        //FIXME is it better to do the work of calculating this more frequently with this equals method,
        // or is it better to store the goal board as a field?
        // With this implementation order of growth is linear, which does meets the minimum requirements

//        //create our goal board
//        int[] goal = new int[size * size];
//        for(int i = 0; i < goal.length - 1; i++) { //add numbers in range [1, size * size - 1]
//            goal[i] = i + 1;
//        }
//        goal[goal.length - 1] = 0; //add the blank square in bottom right

        return Arrays.equals(boardFlat, goal);
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
        if (this == obj)
            return true;
        if (!(obj instanceof Board))//covers case where argument is null or not a board
            return false;

        //take advantage of private fields and built in int[] comparison equals in java.util.Arrays
        return Arrays.equals(boardFlat, ((Board) obj).boardFlat);
    }

    public Iterable<Board> neighbors() {
        //TODO
        return null;
    }

    @Override
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
