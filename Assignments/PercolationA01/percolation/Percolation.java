package percolation;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Author: Alec Mills
 * <p>
 * Assignment A01 - Percolation
 */
public class Percolation {
    private WeightedQuickUnionUF primaryUF;
    private WeightedQuickUnionUF secondaryUF;
    private boolean[][] sites;
    private int n;

    /**
     * create an NxN grid
     *
     * @param n dimensions of the grid
     */
    public Percolation(int n) throws IllegalArgumentException {
        //don't allow nonsensical grid sizes
        if (n <= 0)
            throw new IllegalArgumentException();

        //sites has enough space for all N*N objects, plus a virtual top and virtual bottom site, i.e.indices [0] and [N + 2]
        primaryUF = new WeightedQuickUnionUF((n * n) + 2);
        //secondary UF object used for isFull checks has only a virtual top, no virtual bottom
        secondaryUF = new WeightedQuickUnionUF(n * n + 1);
        sites = new boolean[n][n];
        this.n = n;

        //connect virtual top site to top row and virtual bottom site to bottom row for primary unionfind object
        for (int i = 1; i <= n; i++) {
            primaryUF.union(0, i);
            secondaryUF.union(0, i);
        }

        //secondary only has virtual top
        for (int i = (n * n); i > (n * n) - n; i--)
            primaryUF.union((n * n) + 1, i);
    }

    /**
     * Does the system percolate?
     *
     * @return true if the system percolates false otherwise
     */
    public boolean percolates() {
        //if the virtual top and virtual bottom sites are connected, the system percolates.
        return primaryUF.connected(0, n * n);
    }

    /**
     * Open site if it is not already, i.e., cause the site to be unblocked
     *
     * @param i x coordinate to check
     * @param j y coordinate to check
     */
    public void open(int i, int j) throws IndexOutOfBoundsException {
        if (!validateIndices(i, j))
            throw new IndexOutOfBoundsException("Indices must be in range [0,n)");


        //for each adjacent site, connect to site[i,j] if the adjacent site is open
        for (int[] neighbor : neighbors(i, j)) {
            int i2 = neighbor[0];
            int j2 = neighbor[1];

            if (validateIndices(i2, j2) && isOpen(i2, j2)) {
                int site = twoDToOneDIndices(i, j);
                int neighborSite = twoDToOneDIndices(i2, j2);
                primaryUF.union(site, neighborSite);
                secondaryUF.union(site, neighborSite);
            }
        }

        sites[i][j] = true;
    }

    /**
     * @param i x coordinate to check
     * @param j y coordinate to check
     * @return true if site is open false otherwise
     */
    public boolean isOpen(int i, int j) throws IndexOutOfBoundsException {
        if (!validateIndices(i, j))
            throw new IndexOutOfBoundsException("Indices must be in range [0,n)");

        return sites[i][j];
    }

    /**
     * A site is full if:
     * the site is unblocked i.e. isOpen() and can be connected to the top row via a chain of adjacent open sites
     *
     * @param i x coordinate to check
     * @param j y coordinate to check
     * @return true if site is full false otherwise
     */
    public boolean isFull(int i, int j) throws IndexOutOfBoundsException {
        if (!validateIndices(i, j))
            throw new IndexOutOfBoundsException();

        int site = twoDToOneDIndices(i, j);
        //we use the secondaryUF object (which has no virtual bottom, only a virtual top) to avoid 'backwash' errors
        return sites[i][j] && secondaryUF.connected(site, 0);
    }

    private boolean validateIndices(int i, int j) {
        return (i >= 0 && i < n && j >= 0 && j < n);
    }

    private int twoDToOneDIndices(int i, int j) {
        //the standard conversion formula is n * i + j, in our case our sites are all shifted up by one
        //due to the virtual top site
        return (n * i) + j + 1;
    }


    private int[][] neighbors(int i, int j) {
        return new int[][]{
                {i - 1, j}, {i + 1, j},
                {i, j - 1}, {i, j + 1}
        };
    }
}