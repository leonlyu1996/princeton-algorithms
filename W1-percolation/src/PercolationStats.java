import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final static double CONFIDENCE_95 = 1.96;
    private final int trials;
    private final double mean;
    private final double stddev;

    // perform trials independent experiments on an n-by-n grid
    public PercolationStats(int n, int trials) {

        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        double[] thresholds = new double[trials];
        this.trials = trials;

        for (int i = 0; i < trials; i++) {
            Percolation percolation = new Percolation(n);
            int count = 0;
            while (!percolation.percolates()) {
                int siteId = StdRandom.uniform(0, n * n);
                int row = siteId / n + 1;
                int col = siteId % n + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    count++;
                }
            }
            thresholds[i] = ((double) count) / (n * n);
        }
        this.mean = StdStats.mean(thresholds);
        this.stddev = StdStats.stddev(thresholds);
    }

    // sample mean of percolation threshold
    public double mean() {
        return this.mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return this.stddev;
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean - PercolationStats.CONFIDENCE_95 * this.stddev / Math.sqrt(this.trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean + PercolationStats.CONFIDENCE_95 * this.stddev / Math.sqrt(this.trials);
    }

    // test client (described below)
    public static void main(String[] args) {
        int n = 200;
        int trials = 1000;
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.println("mean:\t= " + ps.mean());
        System.out.println("stddev:\t= " + ps.stddev());
        System.out.println("95% confidence interval:\t= [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");
    }
}