package percolationproject;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[][] grid; // grid[i][j] == 0 for blocked, 1 for open
    private WeightedQuickUnionUF uf; // for percolates()
    private WeightedQuickUnionUF ufBack; // for isFull()
    private int sz;
    private int n;
    private int numOpenSites = 0; // zero open sites at start

    // create a N-by-N grid, with all sites blocked, a zero value
    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException();
        }

        grid = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                grid[i][j] = false;
            }
        }
        this.n = n;
        int nSquare = n * n;
        sz = nSquare + 2;
        uf = new WeightedQuickUnionUF(sz);
        ufBack = new WeightedQuickUnionUF(nSquare + 1);
        for (int i = 1; i <= n; ++i) {
            uf.union(0, i);
            uf.union(sz - 1, nSquare + 1 - i);
            ufBack.union(0, i);
        }
    }

    // open site (row i, column j) if it is not open already
    public void open(int i, int j) {
        validArgs(i, j);
        if (!isOpen(i, j)) {
            grid[i - 1][j - 1] = true;
            numOpenSites++;

            int index = ((i - 1) * n + j);

            if (i > 1 && isOpen(i - 1, j)) {
                uf.union(((i - 2) * n + j), index);
                ufBack.union(((i - 2) * n + j), index);
            }
            if (i < n && isOpen(i + 1, j)) {
                uf.union((i * n + j), index);
                ufBack.union((i * n + j), index);
            }
            if (j > 1 && isOpen(i, j - 1)) {
                uf.union(((i - 1) * n + j - 1), index);
                ufBack.union(((i - 1) * n + j - 1), index);
            }
            if (j < n && isOpen(i, j + 1)) {
                uf.union(((i - 1) * n + j + 1), index);
                ufBack.union(((i - 1) * n + j + 1), index);
            }
        }

    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        validArgs(i, j);
        return grid[i - 1][j - 1];
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        validArgs(i, j);
        return isOpen(i, j) && ufBack.connected(((i - 1) * n + j), 0);
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1) {
            return isOpen(1, 1);
        }
        return uf.connected(0, sz - 1);
    }

    // testing input i and j
    private void validArgs(int i, int j) {
        if (i < 1 || i > n || j < 1 || j > n) {
            throw new java.lang.IndexOutOfBoundsException();
        }
    }

    public int numberOfOpenSites() {      // number of open sites
        return numOpenSites;
    }
}
