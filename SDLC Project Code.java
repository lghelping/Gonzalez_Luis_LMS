import java.util.*;

/**
 * Author: [Luis Gonzalez]
 * Course: [CEN-3024C-17125]
 * Date: [09/01/2023]

 * Class: User
 * This class represents a user in the library system (base class for Librarians, Patrons, and Administrators).
 */
class User {
    private String username;
    private String password;

    // Constructor, login, and logout methods remain the same
}

class Book {
    private String title;
    private String author;
    private String ISBN;
    private int quantity;

    // Constructor
    public Book(String title, String author, String ISBN, int quantity) {
        this.title = title;
        this.author = author;
        this.ISBN = ISBN;
        this.quantity = quantity;
    }

    // Getter methods for book attributes
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getQuantity() {
        return quantity;
    }

    // Override toString method to display book information
    @Override
    public String toString() {
        return "Title: " + title + ", Author: " + author + ", ISBN: " + ISBN + ", Quantity: " + quantity;
    }
}

class Transaction {
    private int transactionID;
    private int bookID;
    private int patronID;
    private Date transactionDate;

    // Constructor and other methods remain the same
}

class Library {
    private List<Book> books;
    private List<User> users;
    private List<Transaction> transactions;

    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(String title, String author, String ISBN, int quantity) {
        Book newBook = new Book(title, author, ISBN, quantity);
        books.add(newBook);
        System.out.println("Book added: " + newBook);
    }

    public void removeBook(String ISBN) {
        // Find the book with the specified ISBN and remove it
        for (Book book : books) {
            if (book.getISBN().equals(ISBN)) {
                books.remove(book);
                System.out.println("Book removed: " + book);
                return;
            }
        }
        System.out.println("Book with ISBN " + ISBN + " not found.");
    }

    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books in the library.");
            return;
        }
        System.out.println("List of Books in the Library:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // The rest of your code remains the same

    public static void main(String[] args) {
        Library library = new Library();
        // Adding books
        library.addBook("Book 1", "Author 1", "123456789", 5);
        library.addBook("Book 2", "Author 2", "987654321", 3);

        // Viewing books
        library.viewBooks();

        // Removing a book
        library.removeBook("123456789");

        // Viewing books after removal
        library.viewBooks();
    }
}

class Report {
    // Report attributes and methods would be added here
    // Not implemented in this simplified example
}
