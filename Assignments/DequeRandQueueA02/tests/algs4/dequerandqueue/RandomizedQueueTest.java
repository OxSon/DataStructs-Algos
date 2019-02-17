package algs4.dequerandqueue;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {
    private RandomizedQueue<Integer> q;

    @BeforeEach
    void before() {
        q = new RandomizedQueue<>();
    }

    @Test
    void forEach() {
        int initial = Integer.MAX_VALUE;

        //should do nothing
        for(var item : q) {
            initial /= item;
        }
        assertEquals(initial, Integer.MAX_VALUE);

        //enqueue a random int in range (0, 100]
        q.enqueue(StdRandom.uniform(100) + 1);
        q.enqueue(StdRandom.uniform(100) + 1);
        q.enqueue(StdRandom.uniform(100) + 1);
        q.enqueue(StdRandom.uniform(100) + 1);
        q.enqueue(StdRandom.uniform(100) + 1);
        q.enqueue(StdRandom.uniform(100) + 1);

        var firstResult = new ArrayList<Integer>();
        for(var item : q) {
            firstResult.add(item);
        }

        var secondResult = new ArrayList<Integer>();
        for(var item : q) {
            secondResult.add(item);
        }

        boolean success = false;
        for(int i = 0; i < firstResult.size(); i++) {
            if (firstResult.get(i) != secondResult.get(i))
                success = true;
        }
        assert success;
    }

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
    }

    @Test
    void enqueue() {
    }

    @Test
    void dequeue() {
    }

    @Test
    void sample() {
    }

    @Test
    void iterator() {
    }
}