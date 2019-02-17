package books;

/**
 * Author: Alec Mills
 * <p>
 * A00 Books
 * CS 2420 w/ M. Posch 2019
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

final class Book implements Comparable<Book> {
    private final String title;
    private final String author;
    private final int year;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public static List<Book> getList(String file) {
        List<Book> books = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String nextLine;
            String[] values;

            books = new ArrayList<>();

            while (reader.ready()) {
                nextLine = reader.readLine();
                //split appropriately
                values = nextLine.split(",");

                //check for invalid format issues
                //i.e. more or less than 3 values, year is not a number, authors name starts with a number
                //FIXME
                if (!(values.length != 3 || !isYear(values[2]))) {
                    books.add(new Book(values[0], values[1], Integer.parseInt(values[2])));
                } else
                    System.err.printf("Problem: '%s' is improperly formatted%n", nextLine);
            }
        } catch (FileNotFoundException e) {
            System.err.printf("File: '%s' not found%n", file);
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return books;
    }

    private static boolean isYear(String s) {
        //first make sure we have a number
        Scanner sc = new Scanner(s.trim());
        if (!sc.hasNextInt())
            return false;
        sc.close();

        //very basic sanity checking
        return s.length() <= 4;
    }

    @Override
    public String toString() {
        return String.format("%s by %s (%d)", title, author, year);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int compareTo(Book book) {
        return title.compareToIgnoreCase(book.title);
    }
}
