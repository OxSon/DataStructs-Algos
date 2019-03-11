package eightPuzzle;

import edu.princeton.cs.algs4.*;

/**
 * Solves a given 8-Puzzle board, if solvable.
 *
 * @author Alec Mills
 * @author Chau Pham
 */
public class Solver {
    private MinPQ<Board> pq = new MinPQ<>();
    //MinPQ<SearchNode> pq;

    //no reason not to store these that I can see
    private int moves;
    private Stack<Board> solution;

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
        solution = solve(initial);
        moves = solution.size();
    }

    //use a stack so it gets returned starting from beginning board state
    private Stack<Board> solve(Board initial) {
        //FIXME this is broken, we need search node class. board doesn't store previous.
        //TODO test, this is just based on my last time doing this same project (actually different but similar)
        Board head = initial;
        do {
            for (var neighbor : initial.neighbors()) {
                if (!neighbor.equals(head))
                    pq.insert(neighbor);
            }
            head = pq.delMin();
        } while (head.isGoal());

        //can't return chain of previous right now.
        //do we want to make searchnode class that stores reference to previous,
        //or do we want to imitate my previous solution, i.e. make a private enum that tracks moves and
        //reconstruct board from that? will memory savings be worth the confusion?
        return null;
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
