import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final boolean[][] sites;
    private final WeightedQuickUnionUF ufWithTopAndBottom;
    private final WeightedQuickUnionUF ufWithTop;
    private int openSites;
    private final int n;
    private final int virtualTopSite;
    private final int virtualBottomSite;

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("n should be a positive integer!");
        }
        this.n = n;
        this.virtualTopSite = n * n;
        this.virtualBottomSite = n * n + 1;
        this.sites = new boolean[n][n];
        this.ufWithTopAndBottom = new WeightedQuickUnionUF(n * n + 2);
        this.ufWithTop = new WeightedQuickUnionUF(n * n + 1);
        this.openSites = 0;
    }

    private int xyTo1D(int row, int col) {
        return (row - 1) * this.n + col - 1;
    }

    private void validateIndices(int row, int col) {
        if (row <= 0 || row > this.n || col <= 0 || col > this.n) {
            throw new java.lang.IllegalArgumentException("Index out of bounds.");
        }
    }

    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        validateIndices(row, col);
        if (isOpen(row, col)) {
            return;
        }
        this.sites[row - 1][col - 1] = true;
        int thisSite = xyTo1D(row, col);
        if (row - 1 > 0 && isOpen(row - 1, col)) {
            this.ufWithTopAndBottom.union(thisSite, xyTo1D(row - 1, col));
            this.ufWithTop.union(thisSite, xyTo1D(row - 1, col));
        }
        if (row + 1 <= this.n && isOpen(row + 1, col)) {
            this.ufWithTopAndBottom.union(thisSite, xyTo1D(row + 1, col));
            this.ufWithTop.union(thisSite, xyTo1D(row + 1, col));
        }
        if (col - 1 > 0 && isOpen(row, col - 1)) {
            this.ufWithTopAndBottom.union(thisSite, xyTo1D(row, col - 1));
            this.ufWithTop.union(thisSite, xyTo1D(row, col - 1));
        }
        if (col + 1 <= this.n && isOpen(row, col + 1)) {
            this.ufWithTopAndBottom.union(thisSite, xyTo1D(row, col + 1));
            this.ufWithTop.union(thisSite, xyTo1D(row, col + 1));
        }
        if (row == 1) {
            this.ufWithTopAndBottom.union(thisSite, virtualTopSite);
            this.ufWithTop.union(thisSite, virtualTopSite);
        }
        if (row == this.n) {
            this.ufWithTopAndBottom.union(thisSite, virtualBottomSite);
        }
        this.openSites++;
    }

    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateIndices(row, col);
        return this.sites[row - 1][col - 1];
    }

    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        validateIndices(row, col);
        return this.ufWithTop.connected(xyTo1D(row, col), this.virtualTopSite);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return this.openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return this.ufWithTopAndBottom.connected(this.virtualTopSite, this.virtualBottomSite);
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
