package org.eastnets.dao;
import org.eastnets.model.Book;
import java.util.List;

public interface BookDAO {

     void addBook(Book book);
     List<Book> viewBooks();
     List<Book> searchBookByTitle(String title);
     List<Book> searchBookByAuthor(String author);
     void deleteBook(int id);

}
