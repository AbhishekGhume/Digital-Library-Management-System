import java.util.*;

public class LibraryManagementSystem {
    private Map<String, Book> bookCatalog;

    public LibraryManagementSystem() {
        this.bookCatalog = new HashMap<>();
    }

    // Add Book
    public void addBook(Book book) {
        if (bookCatalog.containsKey(book.getBookId())) {
            throw new IllegalArgumentException("Book with ID " + book.getBookId() + " already exists");
        }
        bookCatalog.put(book.getBookId(), book);
    }

    // View All Books
    public List<Book> getAllBooks() {
        return new ArrayList<>(bookCatalog.values());
    }

    // Search Book by ID
    public Book searchBookById(String bookId) {
        Book book = bookCatalog.get(bookId);
        if (book == null) {
            throw new NoSuchElementException("Book with ID " + bookId + " not found");
        }
        return book;
    }

    // Search Book by Title
    public List<Book> searchBookByTitle(String title) {
        return bookCatalog.values().stream()
            .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
            .toList();
    }

    // Update Book
    public void updateBook(String bookId, Book updatedBook) {
        if (!bookCatalog.containsKey(bookId)) {
            throw new NoSuchElementException("Book with ID " + bookId + " not found");
        }
        bookCatalog.put(bookId, updatedBook);
    }

    // Delete Book
    public void deleteBook(String bookId) {
        if (!bookCatalog.containsKey(bookId)) {
            throw new NoSuchElementException("Book with ID " + bookId + " not found");
        }
        bookCatalog.remove(bookId);
    }
}