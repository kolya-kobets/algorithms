package algorithms.part1.lectures;

public class Merge {

    static int mergeCount = 0;

    static public void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length);
    }

    static public void sort2(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        final int N = a.length;
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += sz + sz) {
                merge(a, aux, lo, Math.min(lo + sz + sz, N), lo + sz);
            }
        }
    }

    static private void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi - lo < 2)
            return;
        int mid = (lo + hi) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid, hi);
        //if (!less(a[mid], a[mid - 1]))
        //    return;
        merge(a,aux, lo, hi, mid);
    }

    public static void main(String[] args) {
        //Integer[] array = new Integer[] { 57, 54, 61, 89, 43, 70, 19, 74, 63, 79, 77, 36  };

        Integer[] array = new Integer[] { 81, 45, 61, 96, 55, 98, 99, 54, 14, 73 };
 
        mergeCount = 7;
        sort2(array);

        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(' ');
        }
    }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int hi,
            int mid) {
        if (mergeCount == 0)
            return;
        
        for(int i=lo; i<hi; i++) {
            aux[i] = a[i]; 
        }
        // assert isSorted(a, lo, mid);
        // assert isSorted(a, mid, hi);

        int i = lo, j = mid;
        for (int k = lo; k < hi; k++) {
            if (i >= mid)
                a[k] = aux[j++];
            else if (j >= hi)
                a[k] = aux[i++];
            else if (less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }
        mergeCount--;

        // assert isSorted(a, lo, hi);
    }

    private static boolean isSorted(Comparable[] a, int beg, int end) {
        for (int i = beg + 1; i < end; i++) {
            if (less(a[i], a[i - 1])) {
                return false;
            }
        }
        return true;
    }

    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
}
