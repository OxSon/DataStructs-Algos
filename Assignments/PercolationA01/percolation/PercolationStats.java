package percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Author: Alec Mills
 * <p>
 * Used to automate percolation simulation experiments for purposes of a
 * calculating an approximate percolation threshold using Monte Carlo method
 */
public class PercolationStats {
    private double[] sampleData;

    private int t;
    private int n;

    /**
     * perform T independent experiments on an NxN grid
     *
     * @param n grid length & width
     * @param t number of independent experiments to perform
     * @throws IllegalArgumentException if n <= 0 || t <= 0
     */
    public PercolationStats(int n, int t) throws IllegalArgumentException {
        if (n <= 0 || t <= 0)
            throw new IllegalArgumentException("N and T must both be > 0");

        this.n = n;
        this.t = t;
        sampleData = new double[t];
        int numSites = n * n;

        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);
            int openSites = 0;

            do {
                if (n == 1) {
                    p.open(0, 0);
                    openSites++;
                } else {
                    //we must generate a number from [1, (n * n) - 1]
                    int[] siteIndices = oneDToTwoDIndices(StdRandom.uniform((n * n) - 1) + 1);

                    if (!p.isOpen(siteIndices[0], siteIndices[1])) {
                        p.open(siteIndices[0], siteIndices[1]);
                        openSites++;
                    }
                }
            } while (!p.percolates());

            sampleData[i] = (double) openSites / numSites;
        }
    }

    /**
     * sample mean of percolation threshold
     *
     * @return double value of mean
     */
    public double mean() {
        return StdStats.mean(sampleData);
    }

    /**
     * sample standard deviation of percolation threshold
     *
     * @return double value of standard dev
     */
    public double stddev() {
        return StdStats.stddev(sampleData);
    }

    /**
     * low endpoint of 95% confidence interval
     *
     * @return double value of low endpoint
     */
    public double confidenceLow() {
        return mean() - (1.96 * stddev()) / Math.sqrt(t);
    }

    /**
     * high endpoint of 95% confidence interval
     *
     * @return double value of high endpoint
     */
    public double confidenceHigh() {
        return mean() + (1.96 * stddev()) / Math.sqrt(t);
    }

    private int[] oneDToTwoDIndices(int x) {
        return new int[]{x / n, x % n};
    }

    public static void main(String[] args) {
        var p = new PercolationStats(2, 3000);

        System.out.printf("Mean: %.2f, StdDev: %.2f, Confidence: [%.2f, %.2f]",
                p.mean(), p.stddev(), p.confidenceLow(), p.confidenceHigh());
    }
}
