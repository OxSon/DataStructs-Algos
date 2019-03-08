package eightPuzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void sizeTest() {
        fail();
    }

    @Test
    void hammingTest() {
        fail();
    }

    @Test
    void manhattanTest() {
        fail();
    }

    @Test
    void isGoalTest() {
        fail();
        fail();
    }

    @Test
    void isSolveableTest() {
        fail();
    }

    @Test
    void equalsTest() {
        fail();
    }

    @Test
    void neighborsTest() {
        fail();
    }

    @Test
    void toStringTest() {
        Board test = new Board(new int[][]{
                {0, 1, 2},
                {3, 4, 5},
                {6, 7, 8}

        });

        String expected =
                "3\n 0 1 2\n 3 4 5\n 6 7 8\n";
        assertEquals(expected, test.toString());
    }
}