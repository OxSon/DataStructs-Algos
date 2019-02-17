package percolation;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PercolationTest {
    private final int n = 3;
    private Percolation p;

    @BeforeEach
    void setUp() {
        p = new Percolation(n);
    }

    @Test
    void Percolation() {
        assertThrows(IllegalArgumentException.class, () -> new Percolation(-1));
        assertDoesNotThrow(() -> new Percolation(n));
        for (int i = 0; i < n ; i++) {
            for(int j = 0; j < n; j++) {
                System.out.printf("(i=%d; j=%d) ", i, j);
                p.isOpen(i, j);
            }
        }
    }

    @Test
    void percolates() {
        assertFalse(p.percolates());

        p.open(0, 0);
        p.open(1, 0);
        p.open(2, 0);

        assertTrue(p.percolates());
    }

    @Test
    void open() {
        int i = StdRandom.uniform(n);
        int j = StdRandom.uniform(n);

        assertFalse(p.isOpen(i, j));
        p.open(i, j);
        assertTrue(p.isOpen(i, j));
    }

    @Test
    void isOpen() {
        assertThrows(IndexOutOfBoundsException.class, () -> p.isOpen(-1, 2));

        assertFalse(p.isOpen(0, 0));

        p.open(0, 0);
        assertTrue(p.isOpen(0, 0));
    }

    @Test
    void isFull() {
        p.open(0, 0);
        p.open(1, 0);
        p.open(1, 1);
        assertTrue(p.isFull(1, 1));

        //a blocked site
        assertFalse(p.isOpen(2, 2));
        assertFalse(p.isFull(2, 2));

        //an open but not full site
        p.open(2, 2);
        assertFalse(p.isFull(1, 2));
    }
}