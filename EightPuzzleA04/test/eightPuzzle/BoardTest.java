package eightPuzzle;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @BeforeEach
    void setUp() {
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
        //TODO are there more comparison cases that should be tested?
        // seems fairly simple but am i missing something?
        Board goal = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        });
        Board notGoal = new Board(new int[][]{
                {1, 0, 3},
                {6, 8, 4},
                {2, 5, 7}
        });

        assertTrue(goal.isGoal());
        assertFalse(notGoal.isGoal());
    }

    @Test
    void isSolveableTest() {
        fail();
    }

    @Test
    void equalsTest() {
        //TODO are there more comparison cases that should be tested?
        // seems fairly simple but am i missing something?
        Board goal = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        });
        Board notGoal = new Board(new int[][]{
                {1, 0, 3},
                {4, 8, 6},
                {7, 5, 2}
        });
        Board alsoGoal = new Board(new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        });

        assertEquals(goal, alsoGoal);
        assertNotEquals(goal, notGoal);
        assertNotEquals(alsoGoal, notGoal);
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