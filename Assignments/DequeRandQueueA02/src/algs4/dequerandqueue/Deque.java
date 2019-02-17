package algs4.dequerandqueue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Author: Alec Mills
 * <p>
 * A double-ended queue implementation using a doubly-linked list.
 * All operations provide O(1) performance.
 * Non-iterator memory use is O(n).
 *
 * @param <Item> Type of items stored in the deque
 */
public class Deque<Item> implements Iterable<Item> {
    private Node<Item> first, last;
    private int size;

    /**
     * construct an empty Deque
     */
    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    /**
     * Is the Deque empty?
     *
     * @return true if empty false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * number of elements in Deque
     *
     * @return size of Deque
     */
    public int size() {
        return size;
    }

    /**
     * prepend an item to the deque
     *
     * @param item item to prepend
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new UnsupportedOperationException("Cannot add null item");
        //if deque is empty before adding, last and first are the same
        //and the sole item's next property will be null
        if (isEmpty()) {
            first = new Node<>(item, null, null);
            last = first;
        } else {
            first = new Node<>(item, first, null);
            first.next.prev = first;
        }

        size++;
    }

    /**
     * append an item to the deque
     *
     * @param item item to append
     */
    public void addLast(Item item) {
        if (item == null)
            throw new UnsupportedOperationException("Cannot add null item");
        //if deque is empty before adding, last and first are the same
        //and the sole item's prev property will be null
        if (isEmpty()) {
            last = new Node<>(item, null, null);
            first = last;
        } else {
            last = new Node<>(item, null, last);
            last.prev.next = last;
        }

        size++;
    }

    /**
     * remove item from head of deque
     *
     * @return item removed
     */
    public Item removeFirst() {
        return getItem(true);
    }

    /**
     * remove item from tail of deque
     *
     * @return item removed
     */
    public Item removeLast() {
        return getItem(false);
    }



    @Override
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            Node<Item> current;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public Item next() {
                final var item = current.item;
                current = current.next;
                return item;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private static class Node<Item> {
        final Item item;
        Node<Item> next;
        Node<Item> prev;

        Node(Item item, Node<Item> next, Node<Item> prev) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    private Item getItem(boolean atFirst) {
        if (isEmpty())
            throw new NoSuchElementException("Deque is empty");

        final Item item;
        if (atFirst) {
            item = first.item;
            first = first.next;
        } else {
            item = last.item;
            last = last.prev;
        }
        size--;

        //first and last should be null if empty, first will have already been
        // assigned null if applicable in 'first = first.next' call
        if (isEmpty()) {
            first = null;
            last = null;
        } else {
            if (atFirst) //prevent loitering
                first.prev = null;
            else
                last.next = null;
        }

        return item;
    }
    /**
     * program entry point
     *
     * @param args command-line arguments (n/a)
     */
    public static void main(final String[] args) {
        var d = new Deque<Integer>();
        try {
            d.removeFirst();
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw no-element exception on " +
                    "attempting to removeFirst from empty queue");
        } catch (Exception e) {
            System.err.println("Did not throw correct exception on attempting" +
                    " to removeFirst from empty queue");
            e.printStackTrace();
        }
        try {
            d.removeLast();
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw no-element exception on " +
                    "attempting to removeLast from empty queue");
        } catch (Exception e) {
            System.err.println("Did not throw correct exception on attempting" +
                    " to removeLast from empty queue");
            e.printStackTrace();
        }

        try {
            d.addFirst(null);
        } catch (UnsupportedOperationException e) {
            System.out.println("Correctly threw unsupported operation when " +
                    "attempting to addFirst null item to queue");
        } catch (Exception e) {
            System.err.println("Did not throw correct exception on attempting"
                    + "to addFirst null item");
            e.printStackTrace();
        }
        try {
            d.addLast(null);
        } catch (UnsupportedOperationException e) {
            System.out.println("Correctly threw unsupported operation when " +
                    "attempting to addLast null item to queue");
        } catch (Exception e) {
            System.err.println("Did not throw correct exception on attempting"
                    + "to addLast null item");
            e.printStackTrace();
        }


        for (int i = 0; i < 10; i++) {
            d.addFirst(1);
        }

        var arr = new int[10];
        int index = 0;

        for (var item : d) {
            arr[index] = item;
        }

        //test iterator
        for (int i = 0; i < arr.length; i++) {
            int next = d.removeFirst();
            assert arr[i] == next;
            i++;
        }

        //test add first add last remove first remove last
        d = new Deque<>();

        int x = StdRandom.uniform(100);
        int y = StdRandom.uniform(100);
        d.addFirst(x);
        d.addLast(y);
        int resultOne = d.removeFirst();
        int resultTwo = d.removeLast();
        assert resultOne == x && resultTwo == y;
    }
}
