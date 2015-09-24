package algorithms.part1.lecture1;

public class QuickFindUF {

    private int[] id = null;

    public QuickFindUF(int count) {
        id = new int[count];
        for (int i = 0; i < count; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    public void printId() {
        for (int v : id) {
            System.out.print(v);
            System.out.print(' ');
        }
    }
}
