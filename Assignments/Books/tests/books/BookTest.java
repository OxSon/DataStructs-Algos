package books;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {

    @Test
    void ToStringTest() {
        Book book = new Book("a", "b", 1900);
        assertEquals("a by b (1900)", book.toString());

        book = new Book("hello", "there", 8);
        assertEquals("hello by there (8)", book.toString());
   }

    @Test
    void compareTo() {
        Book lesser = new Book("Abc", "z", 1);
        Book greater = new Book("Bcd", "z", 1);

        assertTrue(lesser.compareTo(greater) < 0);
    }

    @Test
    void getList() {
        //TODO
//        fail();
    }
}