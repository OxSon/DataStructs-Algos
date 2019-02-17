package algs4.dequerandqueue;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Author: Alec Mills
 * <p>
 * A randomized queue implementation using resizing array storage
 * Non-iterator operations provide amortized O(1) performance.
 * Iterator operations provide O(1) performance, including constructor.
 * Iterator uses O(n) memory.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] items;
    private int size;

    /**
     * construct empty randomized queue
     */
    public RandomizedQueue() {
        items = (Item[]) new Object[2];
        size = 0;
    }

    /**
     * is the queue empty
     *
     * @return true if empty false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * number of elements in queue
     *
     * @return size of queue
     */
    public int size() {
        return size;
    }

    /**
     * add an item to the queue
     *
     * @param item item to be added
     */
    public void enqueue(Item item) {
        if (item == null)
            throw new UnsupportedOperationException("Cannot add null items " +
                    "to queue"); //disallow
        // null items in our queue

        if (size == items.length) //if we're full
            resize(items.length * 2); //double capacity

        int index = size;
        items[index] = item; //always add at end of current continuous array
        size++;
    }

    /**
     * remove a random item from the queue
     *
     * @return the item removed
     */
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        //pick a random index
        int itemIndex = StdRandom.uniform(size);
        var item = items[itemIndex];
        //swap item at that index with last item in array and delete it
        items[itemIndex] = items[size - 1];
        items[size - 1] = null;

        size--;

        if (size <= items.length / 4) //if we're only a quarter full
            resize(items.length / 2); //halve capacity
        return item;
    }

    /**
     * return but do not remove a random item from the queue (analogous to peek)
     *
     * @return the item chosen
     */
    public Item sample() {
        int index = StdRandom.uniform(size);
        return items[index];
    }

    @Override
    public Iterator<Item> iterator() {
        return new Iterator<>() {
            int notVisited = size;

            @Override
            public boolean hasNext() {
                return notVisited > 0;
            }

            @Override
            public Item next() {
                //Fisher-yates shuffle
                int index = StdRandom.uniform(notVisited);

                var result = swap(index, notVisited - 1);
                notVisited--;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

            //swaps items[i] with items[j] and returns original items[i]
            private Item swap(int i, int j) {
                var temp = items[i];
                items[i] = items[j];
                items[j] = temp;
                return temp;
            }
        };
    }

    private void resize(int capacity) {
        assert capacity >= size; //don't allow argument to be smaller than

        // current size
        var temp = (Item[]) new Object[capacity];
        int index = 0;
        //add all non-null items in our old array to our new array
        for (var item : items) {
            if (items[index] != null) {
                temp[index] = item;
                index++;
            }
        }

        items = temp;
    }

    public static void main(String[] args) {
        //set up
        var q = new RandomizedQueue<Integer>();
        try {
            q.enqueue(null);
        } catch (UnsupportedOperationException e) {
            System.out.println("Correctly threw unsupported operation exception when " +
                    "attempting to enqueue null item");
        } catch (Exception e) {
            System.err.println("Did not throw correct exception when enqueuing null item");
        }
        try {
            q.dequeue();
        } catch (NoSuchElementException e) {
            System.out.println("Correctly threw nosuchelement on de-queueing " +
                    "from empty queue");
        } catch (Exception e) {
            System.err.println("Did not throw correct exception on " +
                    "de-queueing from empty queue");
        }

        for (int i = 0; i < 20; i++) {
            q.enqueue(i);
        }

        //test iterator
        for (var el : q) {
            System.out.println(el);
        }
        assert !q.isEmpty();

        //test sample
        for (var el : q) {
            System.out.println(q.sample());
        }
        assert !q.isEmpty();

        //test dequeue
        for (int i = 0; i < q.size(); i++) {
            System.out.println(q.dequeue());
        }
        assert q.isEmpty();
    }
}
