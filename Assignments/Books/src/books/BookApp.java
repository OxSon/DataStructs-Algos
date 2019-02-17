package books;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class BookApp {
    public static void main(String[] args) {
        List<Book> books = Book.getList("src/books.csv");
        System.out.printf("%d books read in%n%n", books.size());

        System.out.println("Sorted books list:");
        books.sort(Comparator.naturalOrder());
        for (var book : books) {
            System.out.println(book);
        }

        System.out.println();//structure output

        System.out.println("Reverse-sorted books list:");
        books.sort(Comparator.reverseOrder());
        for (var book : books) {
            System.out.println(book);
        }

        Collections.reverseOrder();
    }

}
