package eightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

/**
 * Solves a given 8-Puzzle board, if solvable.
 *
 * @author Alec Mills
 * @author Chau Pham
 */
public class Solver {
    private MinPQ<Board> pq;
    //MinPQ<SearchNode> pq;

    //no reason not to store these that I can see
    private int moves;
    private Iterable<Board> solution;

    /**
     * Finds a solution to the initial board given (using the A* algorithm).
     *
     * @param initial board state at game begin
     */
    public Solver(Board initial) {
        //corner cases
        if (initial == null)
            throw new NullPointerException("Cannot solve null board");
        if (!initial.isSolvable())
            throw new IllegalArgumentException("Board is not solvable");
        //TODO
    }

    /**
     * The minimum number of moves to solve initial board.
     * @return the minimum number of moves.
     */
    public int moves() {
        //TODO
        return 0;
    }

    /**
     * Calculates a solution to the initial board state.
     * @return a sequence of boards in a shortest solution.
     */
    public Iterable<Board> solution() {
        //TODO
        return null;
    }

    /**
     * Program entry-point-- solves a slider puzzle as a demonstration.
     *
     * @author Algs4 R. Sedgewick & K. Wayne
     * @param args unused.
     */
    public static void main(String[] args) {
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }
    }
}
