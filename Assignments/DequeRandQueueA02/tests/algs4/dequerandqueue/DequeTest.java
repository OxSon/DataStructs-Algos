package algs4.dequerandqueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DequeTest {
    private Deque<Integer> d;
    private static Random rand = new Random();

    @BeforeEach
    void before() {
        d = new Deque<>();
    }

    @Test
    void forEach() {
    }

    @Test
    void isEmpty() {
        assertTrue(d.isEmpty());
        d.addFirst(rand.nextInt(100));
        assertFalse(d.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, d.size());
        d.addFirst(rand.nextInt(100));
        assertEquals(1, d.size());
        d.removeFirst();
        assertEquals(0, d.size());
    }

    @Test
    void addFirst() {
        int x = rand.nextInt(100);
        assertTrue(d.isEmpty());
        assertThrows(UnsupportedOperationException.class,
                () -> d.addLast(null));
        d.addFirst(x);
        assertFalse(d.isEmpty());
        assertEquals(d.removeFirst(), x);
    }

    @Test
    void addLast() {
        int x = rand.nextInt(100);
        assertTrue(d.isEmpty());
        assertThrows(UnsupportedOperationException.class,
                () -> d.addFirst(null));
        d.addLast(x);
        assertFalse(d.isEmpty());
        assertEquals(d.removeLast(), x);
    }

    @Test
    void removeFirst() {
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        assertThrows(NoSuchElementException.class,
                () -> d.removeFirst());
        d.addFirst(x);
        d.addLast(y);
        assertEquals(d.removeFirst(), x);
    }

    @Test
    void removeLast() {
        int x = rand.nextInt(100);
        int y = rand.nextInt(100);
        assertThrows(NoSuchElementException.class,
                () -> d.removeLast());
        d.addFirst(x);
        d.addLast(y);
        assertEquals(d.removeLast(), y);
    }

    @Test
    void iterator() {
        for(int i = 0; i < 10; i++) {
            d.addFirst(1);
        }

        var list = new ArrayList<Integer>();
        for(var item : d) {
            list.add(item);
        }

        for(int i = 0; i < list.size(); i++) {
            assertEquals(list.get(0), d.removeFirst());
        }
    }
}