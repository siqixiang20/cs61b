package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF grid;
    private int sideN;
    private boolean[] sites;
    private int dummyTop;
    private int dummyBottom;
    private int openSitesNum;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("Wrong Side!");
        }
        grid = new WeightedQuickUnionUF(N * N + 2);
        sideN = N;
        sites = new boolean[N * N];
        dummyTop = N * N;
        dummyBottom = N * N + 1;
        openSitesNum = 0;
        initialize();
    }

    // initialize all sites
    private void initialize() {
        for (int i = 0; i < sideN; i += 1) {
            grid.union(dummyTop, i);
        }
    }

    // check if row, col valid;
    private void valid(int row, int col) {
        if (row < 0 || col < 0 || row > sideN - 1 || col > sideN - 1) {
            throw new IndexOutOfBoundsException("Wrong row or col!");
        }
    }
    // translate 2D to 1D number
    private int xyTo1D(int x, int y) {
        return x * sideN + y;
    }

    //neighbors
    private int[] neighbors(int row, int col) {
        valid(row, col);
        // top, left, bottom, right
        int[] neighbors = new int[]{-1, -1, -1, -1};

        if (row == 0) {
            neighbors[0] = xyTo1D(row, col);
            neighbors[2] = xyTo1D(row + 1, col);
        } else if (row < sideN - 1) {
            neighbors[0] = xyTo1D(row - 1, col);
            neighbors[2] = xyTo1D(row + 1, col);
        } else {
            neighbors[0] = xyTo1D(row - 1, col);
            neighbors[2] = xyTo1D(row, col);
        }

        if (col == 0) {
            neighbors[1] = xyTo1D(row, col);
            neighbors[3] = xyTo1D(row, col + 1);
        } else if (row < sideN - 1) {
            neighbors[1] = xyTo1D(row, col - 1);
            neighbors[3] = xyTo1D(row, col + 1);
        } else {
            neighbors[1] = xyTo1D(row, col - 1);
            neighbors[3] = xyTo1D(row, col);
        }

        return neighbors;
    }

    // help func: connect target to
    private void connectEmpty(int row, int col) {
        valid(row, col);
        int[] neigh = neighbors(row, col);
        int target = xyTo1D(row, col);
        for (int x : neigh) {
            if (sites[x]) {
                grid.union(x, target);
            }
        }
        for (int i = 0; i < sideN; i += 1) {
            int x = xyTo1D(sideN-1,i);
            if (grid.connected(x, dummyTop)) {
                grid.union(dummyBottom, x);
            }
        }
    }
    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        valid(row, col);
        int target = xyTo1D(row, col);
        if (!sites[target]) {
            sites[target] = true;
            connectEmpty(row, col);
            openSitesNum += 1;
        }
    }
    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        valid(row, col);
        return sites[xyTo1D(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        valid(row, col);
        int target = xyTo1D(row, col);
        return isOpen(row, col) && grid.connected(dummyTop, target);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return openSitesNum;
    }

    // does the system percolate?
    public boolean percolates() {
        return grid.connected(dummyTop, dummyBottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
        Percolation test = new Percolation(5);
        test.open(1, 1);
        test.open(2, 1);
        test.open(4, 1);
        test.open(3, 1);
        test.open(0, 1);
        test.open(4, 3);
        System.out.println(test.percolates());
    }

}
