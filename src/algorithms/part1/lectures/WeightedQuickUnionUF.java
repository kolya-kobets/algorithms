package algorithms.part1.lectures;

public class WeightedQuickUnionUF {
    private int[] id = null;
    private int[] size = null;

    public WeightedQuickUnionUF(int count) {
        id = new int[count];
        size = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
            size[i] = 1;
        }
    }

    private int root(int v) {
        int res = id[v];
        while (res != id[res])
            res = id[res];
        return res;
    }

    public void union(int p, int q) {
        int proot = root(p);
        int qroot = root(q);
        if (proot == qroot) {
            return;
        }
        if (size[proot] < size[qroot]) {
            id[proot] = qroot;
            size[qroot] += size[proot];
        } else {
            id[qroot] = proot;
            size[proot] += size[qroot];
        }
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    public void printId() {
        for (int v : id) {
            System.out.print(v);
            System.out.print(' ');
        }
    }
}
