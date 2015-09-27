package algorithms.part1.lectures;

import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    
    public static void sort(Comparable[] a) {
        //StdRandom.shuffle(a);
        sort1(a, 0, a.length);
    }
    
    //3-way part.
    public static void sort2(Comparable[] a, int beg, int end) {
        if (end-beg < 2) return;
        int lt = beg, gt = end-1;
        Comparable v = a[beg];
        int i = beg;
        while(i <= gt) {
            int cmp = a[i].compareTo(v);
            if(cmp < 0) swap(a, lt++, i++);
            else if(cmp > 0) swap(a, i, gt--);
            else i++;
        }
        
        //sort2(a, beg, lt);
        //sort2(a, gt+1,end);
    }
    
    private static void sort1(Comparable[] a, int beg, int end) {
        if(end - beg < 2) return;
        int mid = partition(a, beg, end);
        sort1(a, beg, mid);
        sort1(a, mid+1, end);
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[] { 40, 13, 17, 40, 80, 43, 40, 78, 45, 40  };
        char[] array2 = new char[] { 'B', 'B', 'A', 'A', 'B', 'B', 'B', 'B', 'A', 'B', 'B', 'A' };
        Character[] array3 = new Character[array2.length];
        for(int i=0; i< array.length; i++) {
            array3[i] = Character.valueOf(array2[i]);
        }
        
        
        int b = Character.valueOf('B').compareTo(Character.valueOf('A'));
        //partition(array, 0, array.length);
        sort2(array, 0, array.length);
        //partition2(array3, 0, array.length);
        
        
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            System.out.print(' ');
        }
    }

    private static int partition2(Comparable[] a, int beg, int end) {
        
        int i=beg+1, j = end-1;
        while(true) {
            while(i < end && less(a[i], a[beg])) {
                i++;
            }
            while(j > beg && less(a[beg], a[j])) {
                j--;
            }
            if(i >= j) break;
            swap(a,i,j);
            i++; j--;
        }
        swap(a,j,beg);
        return j;
    }
    
    private static int partition(Comparable[] a, int beg, int end) {
        
        int i=beg+1, j = end-1;
        while(true) {
            while(i < end && less(a[i], a[beg])) i++;
            while(j > beg && !less(a[j], a[beg])) j--;
            if(i >= j) break;
            swap(a,i,j);
        }
        swap(a,j,beg);
        return j;
    }
    private static boolean less(Comparable a, Comparable b) {
        return a.compareTo(b) < 0;
    }
    private static boolean equal(Comparable a, Comparable b) {
        return a.compareTo(b) == 0;
    }
    private static void swap(Comparable[] a, int i, int j) {
        Comparable tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
