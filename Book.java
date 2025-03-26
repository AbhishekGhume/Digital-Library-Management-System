public class Book {
    private String bookId;
    private String title;
    private String author;
    private String genre;
    private BookStatus availability;

    // Enum for Book Availability Status
    public enum BookStatus {
        AVAILABLE, CHECKED_OUT
    }

    // Constructor
    public Book(String bookId, String title, String author, String genre, BookStatus availability) {
        validateBookDetails(bookId, title, author);
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Validation Method
    private void validateBookDetails(String bookId, String title, String author) {
        if (bookId == null || bookId.trim().isEmpty()) {
            throw new IllegalArgumentException("Book ID cannot be empty");
        }
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Book Title cannot be empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be empty");
        }
    }

    // Getters and Setters
    public String getBookId() { return bookId; }
    public void setBookId(String bookId) { 
        validateBookDetails(bookId, this.title, this.author);
        this.bookId = bookId; 
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { 
        validateBookDetails(this.bookId, title, this.author);
        this.title = title; 
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { 
        validateBookDetails(this.bookId, this.title, author);
        this.author = author; 
    }

    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }

    public BookStatus getAvailability() { return availability; }
    public void setAvailability(BookStatus availability) { this.availability = availability; }

    @Override
    public String toString() {
        return String.format("Book{id=%s, title='%s', author='%s', genre='%s', status=%s}", 
            bookId, title, author, genre, availability);
    }
}