import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[][] sites;
    private final WeightedQuickUnionUF ufWithTopAndBottom;
    private final WeightedQuickUnionUF ufWithTop;
    private int openSites;
    private final int n;
    private final int hiddenTopSite;
    private final int hiddenBottomSite;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n should be a positive integer!");
        }
        this.n = n;
        this.hiddenTopSite = n * n;
        this.hiddenBottomSite = n * n + 1;
        this.sites = new boolean[n][n];
        this.ufWithTopAndBottom = new WeightedQuickUnionUF(n * n + 2);
        this.ufWithTop = new WeightedQuickUnionUF(n * n + 1);
        this.openSites = 0;
    }

    private int to1D(int row, int col) {
        return (row - 1) * this.n + col - 1;
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > this.n || col <= 0 || col > this.n) {
            throw new java.lang.IllegalArgumentException("Index out of bounds.");
        }
        if (isOpen(row, col)) {
            return;
        }
        this.sites[row-1][col-1] = true;
        int thisSite = to1D(row, col);
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            this.ufWithTopAndBottom.union(thisSite, to1D(row - 1, col));
            this.ufWithTop.union(thisSite, to1D(row - 1, col));
        }
        if (row + 1 <= this.n && isOpen(row + 1, col)) {
            this.ufWithTopAndBottom.union(thisSite, to1D(row + 1, col));
            this.ufWithTop.union(thisSite, to1D(row + 1, col));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            this.ufWithTopAndBottom.union(thisSite, to1D(row, col - 1));
            this.ufWithTop.union(thisSite, to1D(row, col - 1));
        }
        if (col + 1 <= this.n && isOpen(row, col + 1)) {
            this.ufWithTopAndBottom.union(thisSite, to1D(row, col + 1));
            this.ufWithTop.union(thisSite, to1D(row, col + 1));
        }
        if (row == 1) {
            this.ufWithTopAndBottom.union(thisSite, hiddenTopSite);
            this.ufWithTop.union(thisSite, hiddenTopSite);
        }
        if (row == this.n) {
            this.ufWithTopAndBottom.union(thisSite, hiddenBottomSite);
        }
        this.openSites++;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > this.n || col <= 0 || col > this.n) {
            throw new java.lang.IllegalArgumentException("Index out of bounds.");
        }
        return this.sites[row-1][col-1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > this.n || col <= 0 || col > this.n) {
            throw new java.lang.IllegalArgumentException("Index out of bounds.");
        }
        return this.ufWithTop.connected(to1D(row, col), this.hiddenTopSite);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.ufWithTopAndBottom.connected(this.hiddenTopSite, this.hiddenBottomSite);
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 1);
        percolation.open(2, 1);
        percolation.open(3, 1);
        percolation.open(4, 2);
        percolation.open(4, 3);
        percolation.open(3, 3);
        percolation.open(4, 1);
        System.out.println(percolation.isFull(4, 3));
        System.out.println(percolation.percolates());
    }
}
