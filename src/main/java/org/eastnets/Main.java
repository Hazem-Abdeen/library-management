package org.eastnets;
import org.eastnets.model.Book;
import org.eastnets.service.BookService;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookService bookService = BookService.getInstance();

    public static void main(String[] args) {
        while (true) {
            printMenu();
            int choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1 -> addBook();
                case 2 -> viewAllBooks();
                case 3 -> searchByTitle();
                case 4 -> searchByAuthor();
                case 5 -> deleteBook();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n===== Library Menu =====");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book by Title");
        System.out.println("4. Search Book by Author");
        System.out.println("5. Delete Book");
        System.out.println("6. Exit");
    }

    private static void addBook() {
        System.out.println("\n--- Add Book ---");
        String title = readString("Title: ");
        String author = readString("Author: ");
        int quantity = readInt("Quantity: ");
        int year = readInt("Year: ");

        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setQuantity(quantity);
        book.setYear(year);

        bookService.addBook(book);
    }

    private static void viewAllBooks() {
        System.out.println("\n--- All Books ---");
        List<Book> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchByTitle() {
        System.out.println("\n--- Search by Title ---");
        String title = readString("Enter title: ");
        List<Book> books = bookService.searchBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void searchByAuthor() {
        System.out.println("\n--- Search by Author ---");
        String author = readString("Enter author: ");
        List<Book> books = bookService.searchBooksByAuthor(author);
        if (books.isEmpty()) {
            System.out.println("No matching books found.");
        } else {
            books.forEach(System.out::println);
        }
    }

    private static void deleteBook() {
        System.out.println("\n--- Delete Book ---");
        int id = readInt("Enter book ID to delete: ");
        bookService.deleteBook(id);
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter again.");
            }
        }
    }
}