package org.eastnets.dao;
import org.eastnets.db.DBConnection;
import org.eastnets.model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    public void addBook(Book book) {

        String sql = "INSERT INTO books (title, author, quantity, year) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setInt(3, book.getQuantity());
            stmt.setInt(4, 2025);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding book:");
            e.printStackTrace();
        }

    }
    public List<Book> viewBooks() {
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                getBooksFromDB(rs, books);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching books:");
            e.printStackTrace();
        }

        return books;
    }

    private static void getBooksFromDB(ResultSet rs, ArrayList<Book> books) throws SQLException {
        Book book = new Book(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("author"),
                rs.getInt("quantity"),
                rs.getInt("year")
        );
        books.add(book);
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        return searchBooksByField("title", title);
    }

    @Override
    public List<Book> searchBookByAuthor(String author) {
        return searchBooksByField("author", author);
    }

    private List<Book> searchBooksByField(String field, String value) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books WHERE " + field + " ILIKE ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + value + "%");

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(new Book(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getString("author"),
                            rs.getInt("quantity"),
                            rs.getInt("year")
                    ));
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching book by " + field + ":");
            e.printStackTrace();
        }

        return books;
    }

    public void deleteBook(int id){
        String sql = "DELETE FROM books WHERE id = ? ";
        try(Connection conn = DBConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Book deleted successfully!");
            } else {
                System.out.println("No book found with the given ID.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting book:");
            e.printStackTrace();
        }
    }
}
