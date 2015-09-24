package algorithms.part1.task1;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private WeightedQuickUnionUF qunion = null;
    private boolean[][] openSite;
    private int N = 0;
    private int top, bottom;

    public Percolation(int count) {
        N = count;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        top = (N + 2) * (N + 2);
        bottom = top + 1;
        qunion = new WeightedQuickUnionUF((N + 2) * (N + 2) + 2);
        openSite = new boolean[N + 2][N + 2];

        int top_start = (N + 2);
        int bottom_start = (N + 2) * (N + 1);
        for (int i = 1; i <= N; i++) {
            qunion.union(top_start + i, top);
            qunion.union(bottom_start + i, bottom);

            openSite[N + 1][i] = true;
        }
    }

    public void open(int i, int j) {
        if (isOpen(i, j)) {
            return;
        }

        openSite[i][j] = true;
        if (openSite[i][j - 1]) {
            qunion.union(getRawIndex(i, j), getRawIndex(i, j - 1));
        }
        if (openSite[i][j + 1]) {
            qunion.union(getRawIndex(i, j), getRawIndex(i, j + 1));
        }
        if (openSite[i - 1][j]) {
            qunion.union(getRawIndex(i, j), getRawIndex(i - 1, j));
        }
        if (openSite[i + 1][j]) {
            qunion.union(getRawIndex(i, j), getRawIndex(i + 1, j));
        }

    }

    public boolean isOpen(int i, int j) {
        checkIndices(i, j);
        return openSite[i][j];
    }

    public boolean isFull(int i, int j) {
        return isOpen(i, j) && qunion.connected(top, getRawIndex(i, j));
    }

    public boolean percolates() {
        return qunion.connected(top, bottom);
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(1, 3);
        p.open(2, 3);
        p.open(3, 3);
        p.open(3, 1);
        boolean f1 = p.isFull(3, 1);

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
        return i * (N + 2) + j;
    }
}