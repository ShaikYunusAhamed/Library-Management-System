import java.io.*;
import java.util.*;

public class Library {
    private List<Book> books;
    private final String fileName = "books.txt";

    public Library() {
        books = new ArrayList<>();
        loadBooks();
    }

    // Add a new book
    public void addBook(Book book) {
        books.add(book);
        saveBooks();
    }

    // View all books
    public void viewBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }
        for (Book b : books) {
            System.out.println(b);
        }
    }

    // Issue a book
    public void issueBook(String id) {
        for (Book b : books) {
            if (b.getId().equals(id)) {
                if (!b.isIssued()) {
                    b.issueBook();
                    saveBooks();
                    System.out.println("Book issued successfully.");
                } else {
                    System.out.println("Book already issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Return a book
    public void returnBook(String id) {
        for (Book b : books) {
            if (b.getId().equals(id)) {
                if (b.isIssued()) {
                    b.returnBook();
                    saveBooks();
                    System.out.println("Book returned successfully.");
                } else {
                    System.out.println("Book was not issued.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    // Save books to file
    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(books);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load books from file
    private void loadBooks() {
        File file = new File(fileName);
        if (!file.exists()) return;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            books = (List<Book>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
