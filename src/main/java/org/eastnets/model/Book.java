package org.eastnets.model;

public class Book {

    private int id ;
    private int quantity ;
    private String title ;
    private String author ;
    private int year;

    public Book(int id, String title, String author, int quantity, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
        this.year = year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getYear() { return year; }

    public void setYear(int year) { this.year = year; }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", quantity=" + quantity +
                ", year=" + year +
                '}';
    }
}
