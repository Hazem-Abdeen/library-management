# 📚 Library Management System

This is a **Java-based console application** that allows users to manage a simple library system. The app supports adding, viewing, searching, and deleting books using a PostgreSQL database and JDBC.

---

## 🚀 Features

- ➕ Add a new book (with title, author, quantity, year)
- 🔍 Search for books by **title** or **author**
- 📄 View all books in the system
- ❌ Delete a book by ID
- 🛡 Input validation to prevent invalid or duplicate entries

---

## 🧱 Technologies Used

- **Java** (OOP, JDBC)
- **PostgreSQL** (Database)
- **Git** and **GitHub** (Version Control)
- **IntelliJ IDEA** (IDE)

---

## 🗃️ Database Schema

```sql
CREATE TABLE books (
  id SERIAL PRIMARY KEY,
  title VARCHAR(100) NOT NULL,
  author VARCHAR(100) NOT NULL,
  quantity INT NOT NULL CHECK (quantity > 0),
  year INT NOT NULL CHECK (year > 0)
);

Project Structure:
src/
└── main/
    └── java/
        └── org.eastnets/
            ├── dao/         # DAO interface and implementation
            ├── db/          # DB connection helper
            ├── model/       # Book model
            ├── service/     # Business logic & validation
            └── Main.java    # CLI interface
