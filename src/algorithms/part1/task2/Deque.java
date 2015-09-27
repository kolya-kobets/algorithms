package algorithms.part1.task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first = null, last = null;
    private int size = 0;

    public Deque() {
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        Node<Item> n = new Node<Item>();
        n.value = item;
        n.next = first;
        n.prev = null;
        first = n;
        if (size == 0) {
            last = first;
        } else {
            first.next.prev = first;
        }
        size++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        Node<Item> n = new Node<Item>();
        n.value = item;
        n.next = null;
        n.prev = last;
        last = n;
        
        if (size == 0) {
            first = last;
        } else {
            last.prev.next = last;
        }

        size++;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        Item res = first.value;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            first = first.next;
            first.prev = null;
        }
        size--;
        return res;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Item res = last.value;
        if (size == 1) {
            first = null;
            last = null;
        } else {
            last = last.prev;
            last.next = null;
        }
        size--;
        return res;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    public static void main(String[] args) {
    }

    private static class Node<Item> {
        private Item value;
        private Node<Item> next, prev;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> cur = first;

        public boolean hasNext() {
            return cur != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Node<Item> res = cur;
            cur = cur.next;
            return res.value;
        }

    }
}
