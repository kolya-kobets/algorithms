package algorithms.part1.task1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF percolate = null, full = null;
    private boolean[][] openSite;
    private int N = 0;
    private int top, btm, btmRow;

    public Percolation(int count) {
        N = count;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        top = N * N;
        btm = top + 1;
        btmRow = N * (N - 1);
        percolate = new WeightedQuickUnionUF(N * N + 2);
        full = new WeightedQuickUnionUF(N * N + 1);
        openSite = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            percolate.union(btmRow + i, btm);
        }
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) {
            return;
        }
        int x = i - 1, y = j - 1;
        openSite[x][y] = true;

        if (y != 0 && openSite[x][y - 1]) {
            percolate.union(getRawIndex(x, y), getRawIndex(x, y - 1));
            full.union(getRawIndex(x, y), getRawIndex(x, y - 1));
        }
        if (y != N - 1 && openSite[x][y + 1]) {
            percolate.union(getRawIndex(x, y), getRawIndex(x, y + 1));
            full.union(getRawIndex(x, y), getRawIndex(x, y + 1));
        }
        if (x == 0) {
            percolate.union(getRawIndex(x, y), top);
            full.union(getRawIndex(x, y), top);
        } else if (openSite[x - 1][y]) {
            percolate.union(getRawIndex(x, y), getRawIndex(x - 1, y));
            full.union(getRawIndex(x, y), getRawIndex(x - 1, y));
        }
        if (x != N - 1 && openSite[x + 1][y]) {
            percolate.union(getRawIndex(x, y), getRawIndex(x + 1, y));
            full.union(getRawIndex(x, y), getRawIndex(x + 1, y));
        }

    }

    public boolean isOpen(int i, int j) {
        checkIndices(i, j);
        return openSite[i-1][j-1];
    }

    public boolean isFull(int i, int j) {
        return isOpen(i, j) && full.connected(top, getRawIndex(i - 1, j - 1));
    }

    public boolean percolates() {
        return percolate.connected(top, btm);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
        boolean f1 = p.isFull(3, 2);
        System.out.format("isFull(3,1): %s\n", Boolean.toString(f1));
        if (p.percolates()) {
            System.out.print("percolates");
        } else {
            System.out.print("not percolates");
        }
    }

    private void checkIndices(int i, int j) {
        if (i > N || i < 1 || j < 1 || j > N) {
            throw new IndexOutOfBoundsException();
        }
    }

    private int getRawIndex(int i, int j) {
        return i * N + j;
    }
}