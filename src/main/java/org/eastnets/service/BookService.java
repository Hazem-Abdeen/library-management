package org.eastnets.service;
import org.eastnets.dao.BookDAO;
import org.eastnets.dao.BookDAOImpl;
import org.eastnets.model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService {

   private static final  BookService instance = new BookService();

    private final BookDAO bookDAO ;

    private BookService() {
        this.bookDAO = new BookDAOImpl();
    }

    public void addBook(Book book) {

        if (validateAddBooks(book)) return;
        bookDAO.addBook(book);
        System.out.println("Book added successfully!");
    }

    private boolean validateAddBooks(Book book) {
        List<Book> existing = bookDAO.searchBookByTitle(book.getTitle()).stream()
                .filter(b -> b.getAuthor().equalsIgnoreCase(book.getAuthor()))
                .toList();

        if (!existing.isEmpty()) {
            System.out.println("Book already exists in the database.");
            return true;
        }

        if (book.getTitle() == null || book.getTitle().isEmpty()) {
            System.out.println("Book title cannot be empty.");
            return true;
        }
        if (book.getAuthor() == null || book.getAuthor().isEmpty()) {
            System.out.println("Book author cannot be empty.");
            return true;
        }
        if (book.getQuantity() <= 0) {
            System.out.println("Book quantity must be greater than zero.");
            return true;
        }
        if (book.getYear() <= 0) {
            System.out.println("Book year must be a valid year.");
            return true;
        }
        return false;
    }

    public List<Book> getAllBooks() {
        return bookDAO.viewBooks();
    }

    public List<Book> searchBooksByTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Title cannot be empty.");
            return new ArrayList<>();
        }

        return bookDAO.searchBookByTitle(title.trim());
    }

    public List<Book> searchBooksByAuthor(String author) {
        if (author == null || author.trim().isEmpty()) {
            System.out.println("Error: Author cannot be empty.");
            return new ArrayList<>();
        }

        return bookDAO.searchBookByAuthor(author.trim());
    }

    public void deleteBook(int id) {
        if (id <= 0) {
            System.out.println("Invalid ID.");
            return;
        }
        bookDAO.deleteBook(id);
    }

    public static BookService getInstance() {
        return  instance;
    }
}
