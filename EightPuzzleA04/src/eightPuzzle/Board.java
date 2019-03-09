package eightPuzzle;

import java.util.Arrays;

import edu.princeton.cs.algs4.Queue;

/**
 * Represents an 8-Puzzle board
 *
 * @author Alec Mills
 * @author Chau Pham
 */
public class Board {
    /*PERFORMANCE REQS:
     * all methods take time proportional to N^2 or better,
     * with the exception of isSolvable() which may take up to N^4 in the worst case
     */
    private final int[] boardFlat;
    private final int size;
    //FIXME is it better to compute this on demand,
    // or compute once in constructor and use the extra memory for storing the array?
    // note: storing this array costs us an extra 4N + 32 byes of memory
    // storing the array also greatly simplifies code in a number of places and improves readability
    // my current thinking is it's worth it to store, but that may change during implementation of solver
    // as we may need to store a large number of board objects
    private final int[] goal;
    //FIXME is it better to store this, thereby making isSolvable calls no longer n^2 in the worst case, but n lgn?
    // and simplifying code for 'neighbors' //FIXME exactly how much does it improve?
    // Or is the extra memory and constructor calculation time not desirable?
    // Also note: 'row' and 'col' might be bad names for these, can be confusing, X and Y might be better
    private int blankTileRow;
    private int blankTileCol;

    /**
     * Construct a board from an N-by-N array of blocks
     * (where blocks[i][j] = block in row i, column j).
     * Valid integers are between 0 and (N^2) - 1, where zero represents the empty space
     * <p>
     * FIXME add time complexity to javadoc
     *
     * @param blocks initial layout of blocks.
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

                //TODO decide if it's worth storing these in board, see notes above at field declarations
                if (blocks[i][j] == 0) {
                    blankTileRow = i;
                    blankTileCol = j;
                }
            }
        }
    }

    /**
     * Board size N (i.e. a 3 by 3 board with 9 total spaces would be size 3)
     * <p>
     * FIXME add time complexity to javadoc
     *
     * @return board size N.
     */
    public int size() {
        return size;
    }

    /**
     * Hamming priority heuristic; the number of blocks out of place.
     * <p>
     * FIXME add time complexity to javadoc
     *
     * @return the number of blocks out of place.
     */
    public int hamming() {
        int count = 0;
        //we use boardFlat.length - 1 because we are not interested in the position of the blank tile
        for (int i = 0; i < boardFlat.length - 1; i++) {
            if (goal[i] != boardFlat[i])
                count++;
        }

        return count;
    }

    /**
     * Manhattan priority heuristic;
     * sum of vertical and horizontal distances between
     * blocks and their respective goal positions.
     *
     * @return sum of Manhattan distances between blocks and goal.
     */
    public int manhattan() {
        //TODO test this
        int sum = 0;
        for(int i = 0; i < boardFlat.length; i++) {
            sum += manhattanSingle(boardFlat[i], i);
        }

        return sum;
    }

    //calculates manhattan distance of a single tile using 1D index
    //Function is:
    //F(x, k) = manhattanSingle(x, i(k), j(k), where i(k) and j(k) return their respective 2D index for 1D index k
    private int manhattanSingle(int x, int k) {
        //Function for going from k = 1D index to (i, j) = 2D indices:
        //F(k) = (k / N, k % N), where N = dimension of board
        return manhattanSingle(x, k / size, k % size);
    }

    //calculates manhattan distance of a single tile using 2D indices
    //Function is:
    //F(x, i, j) = |goalI - i| + |goalJ - j|, where (i, j) are current indices of x
    private int manhattanSingle(int x, int i, int j) {
        if(x == 0) //we are not interested in position of blank tile
            return 0;
        //int[] goal = [goalI, goalJ]
        int[] goal = goalIndices(x);

        //Recall our function: F(x, i, j) = |goalI - i| + |goalJ - j|
        return Math.abs(goal[0] - i) + Math.abs(goal[1] - j);
    }


    /**
     * Is this board the goal board?
     * <p>
     * FIXME add time complexity to javadoc
     *
     * @return true if this board is the goal board false otherwise.
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
     * Is this board solvable? Uses an enhanced merge sort method to determine this by counting inversions.
     * <p>
     * Provides solution in O(n lg(n)) time complexity. The authors felt the additional memory required by
     * Merge-sort was a worthy trade-off, considering that this extra memory is ephemeral to the function itself
     * and this function is unlikely to be called often.
     * <p>
     * <p>
     * FIXME add time complexity to javadoc
     *
     * @return true if board is solvable false otherwise.
     */
    public boolean isSolvable() {
        //FIXME only very briefly tested
        int inversions = inversions(boardFlat);

        // 1 2 3 4 5 0 6 8 9 10 7 11 13 14 15 12

        //if board size is odd, a solvable board has an even number of inversions
        if (size % 2 != 0)
            return inversions % 2 == 0;
        else
            //if board size is even, a solvable board has an odd sum of inversions and blank tile row
            return (inversions + blankTileRow) % 2 != 0;
    }

    /**
     * All boards that can be reached in one legal move from this board
     * <p>
     * FIXME add time complexity to javadoc
     *
     * @return all neighboring boards.
     */
    public Iterable<Board> neighbors() {
        //FIXME test better, have only tested a couple cases, and only with size = 3 or 4
        //TODO comment better
        var neighbors = new Queue<Board>();
        for (int i = blankTileRow - 1; i <= blankTileRow + 1; i++) {
            for (int j = blankTileCol - 1; j <= blankTileCol + 1; j++) {
                //dont go out of bounds
                boolean inBounds = (i >= 0 && i < size && j >= 0 && j < size);
                //FIXME adding another set of conditions to try to avoid moving diagonals
                // i.e. we must still be on either blank tile row or blank tile column but not both
                // I wonder if this is too many conditions and it would be better to allow the wasted blank tile moving
                // to itself case
                if (inBounds && (i == blankTileRow || j == blankTileCol) && !(i == blankTileRow && j == blankTileCol)) {
                    int[][] newBoardState = new int[size][size];
                    for (int k = 0; k < size; k++) {
                        newBoardState[k] = Arrays.copyOfRange(boardFlat, (k * size), (k * size) + size);
                    }

                    int tileToMove = (i * size) + j;
                    int temp = boardFlat[tileToMove];
                    newBoardState[i][j] = 0;
                    newBoardState[blankTileRow][blankTileCol] = temp;
                    neighbors.enqueue(new Board(newBoardState));
                }
            }
        }

        return neighbors;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                sb.append(String.format("%2d ", boardFlat[(i * size) + j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return Arrays.equals(boardFlat, board.boardFlat);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(boardFlat);
    }

    private static int inversions(int[] data) {
        return mergeSort(data, 0, data.length - 1);
    }

    private static int mergeSort(int[] data, int lo, int hi) {
        int mid = lo + (hi - lo) / 2;
        int inversions = 0;

        if (hi > lo) {//don't do anything if our array is of size < 2
            //count inversions in left half
            inversions = mergeSort(data, lo, mid);
            //count inversions in right half
            inversions += mergeSort(data, mid + 1, hi);

            //count remaining inversions, i.e. inversion pairs
            //that span across the two halves
            inversions += merge(data, lo, mid + 1, hi);
        }

        return inversions;
    }


    private static int merge(int[] data, int lo, int mid, int hi) {
        //precondition
//        assert isSorted(data, 0, mid);
//        assert isSorted(data, mid + 1, data.length - 1);

        //our tracking indices and our running total of inversions
        //Note: i = left index, j = right index
        int i = lo;
        int k = lo;
        int inversions = 0;
        int j = mid;

        int[] aux = Arrays.copyOf(data, data.length);

        //standard merge sort procedure:
        //if left element is less/equal than right, copy left element and iterate i & k
        //otherwise, copy right element and iterate j & k
        //do this until either i or j has passed the end of their respective subarray
        while (i < mid && j <= hi) {
            if (aux[i] <= aux[j])
                data[k++] = aux[i++];
            else {
                //we're not interested in inversion pairs that include zero
                if (aux[i] != 0 && aux[j] != 0) {
                    inversions += mid - i;
                }
                data[k++] = aux[j++];
            }
        }

        //copy remaining elements if any from each sub-array into main array
        while (i < mid)
            data[k++] = aux[i++];
        while (j <= hi)
            data[k++] = aux[j++];

        //post condition
//        assert(isSorted(data, lo, hi));

        return inversions;
    }

    /*
     *  Helper function mapping a given number to it's goal (row, col) indices.
     *
     * Our function is;
     *  F(x) = [(x-1)/N, (x -1)%N], where N = dimensions of this board & '/' indicates integer division
     *  Domain: x | x is in range: [1, (N^2) - 1]
     *  Range: (i, j) -> ([0, N-1], [0, N-1])
     */
    private int[] goalIndices(int x) {
        //disallow inputs outside of our domain
        if (x < 1 || x > (size * size) - 1)
            throw new IllegalArgumentException("Input to goal indices is invalid");
        //Recall our function is: F(x) = [(x-1)/N, (x -1)%N]
        return new int[] {(x - 1) / size, (x - 1) % size };
    }

    //FIXME not working
    //for checking pre and post conditions for merge-sort inversion counter,
    //only used if assertions are enabled, no need for efficiency here.
//    private static boolean isSorted(int[] data, int low, int hi) {
//        int[] expected = Arrays.copyOfRange(data, low, hi);
//        Arrays.sort(expected);
//        return Arrays.equals(expected, Arrays.copyOfRange(data, low, hi));
//    }
}
