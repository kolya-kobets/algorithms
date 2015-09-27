package algorithms.part1.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] arr;
    private int size;

    public RandomizedQueue() {
        arr = (Item[]) new Object[1];
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == arr.length) {
            reallocate(size * 2);
        }
        arr[size++] = item;
        int ind = StdRandom.uniform(size);

        Item tmp = arr[ind];
        arr[ind] = arr[size - 1];
        arr[size - 1] = tmp;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Item v = arr[--size];
        arr[size] = null;
        if (size > 0 && size == arr.length / 4) {
            reallocate(arr.length / 2);
        }
        return v;
    }

    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[StdRandom.uniform(size)];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    public static void main(String[] args) {

    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int[] inds;
        private int cur = size - 1;

        public RandomizedQueueIterator() {
            if (size == 0) {
                return;
            }
            inds = new int[size];
            inds[0] = 0;
            for (int i = 1; i < size; i++) {
                int r = StdRandom.uniform(i + 1);
                inds[i] = inds[r];
                inds[r] = i;
            }
        }

        public boolean hasNext() {
            return cur >= 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arr[inds[cur--]];
        }

    }

    private void reallocate(int capacity) {
        if (capacity < size) {
            return;
        }
        Item[] tmp = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            tmp[i] = arr[i];
        }
        arr = tmp;
    }
}
