import java.util.Scanner;

public class LibraryConsoleUI {
    private LibraryManagementSystem librarySystem;
    private Scanner scanner;

    public LibraryConsoleUI() {
        this.librarySystem = new LibraryManagementSystem();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            displayMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1: addBook(); break;
                case 2: viewAllBooks(); break;
                case 3: searchBook(); break;
                case 4: updateBook(); break;
                case 5: deleteBook(); break;
                case 6: System.out.println("Exiting the system. Goodbye!"); return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\n--- Digital Library Management System ---");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search Book");
        System.out.println("4. Update Book");
        System.out.println("5. Delete Book");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void addBook() {
        try {
            System.out.print("Enter Book ID: ");
            String bookId = scanner.nextLine();

            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine();

            System.out.print("Enter Author Name: ");
            String author = scanner.nextLine();

            System.out.print("Enter Genre: ");
            String genre = scanner.nextLine();

            System.out.print("Enter Availability (AVAILABLE/CHECKED_OUT): ");
            Book.BookStatus availability = Book.BookStatus.valueOf(scanner.nextLine().toUpperCase());

            Book book = new Book(bookId, title, author, genre, availability);
            librarySystem.addBook(book);
            System.out.println("Book added successfully!");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewAllBooks() {
        librarySystem.getAllBooks().forEach(System.out::println);
    }

    private void searchBook() {
        System.out.println("Search by: 1. Book ID  2. Title");
        int searchChoice = getUserChoice();

        try {
            if (searchChoice == 1) {
                System.out.print("Enter Book ID: ");
                String bookId = scanner.nextLine();
                System.out.println(librarySystem.searchBookById(bookId));
            } else if (searchChoice == 2) {
                System.out.print("Enter Book Title: ");
                String title = scanner.nextLine();
                librarySystem.searchBookByTitle(title).forEach(System.out::println);
            } else {
                System.out.println("Invalid search option.");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateBook() {
        System.out.print("Enter Book ID to update: ");
        String bookId = scanner.nextLine();

        try {
            Book existingBook = librarySystem.searchBookById(bookId);
            System.out.println("Current Book Details: " + existingBook);

            System.out.print("Enter new Title (press enter to keep current): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.isEmpty()) {
                existingBook.setTitle(newTitle);
            }

            System.out.print("Enter new Author (press enter to keep current): ");
            String newAuthor = scanner.nextLine();
            if (!newAuthor.isEmpty()) {
                existingBook.setAuthor(newAuthor);
            }

            System.out.print("Enter new Genre (press enter to keep current): ");
            String newGenre = scanner.nextLine();
            if (!newGenre.isEmpty()) {
                existingBook.setGenre(newGenre);
            }

            System.out.print("Enter new Availability (AVAILABLE/CHECKED_OUT, press enter to keep current): ");
            String newAvailability = scanner.nextLine();
            if (!newAvailability.isEmpty()) {
                existingBook.setAvailability(Book.BookStatus.valueOf(newAvailability.toUpperCase()));
            }

            librarySystem.updateBook(bookId, existingBook);
            System.out.println("Book updated successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteBook() {
        System.out.print("Enter Book ID to delete: ");
        String bookId = scanner.nextLine();

        try {
            librarySystem.deleteBook(bookId);
            System.out.println("Book deleted successfully!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new LibraryConsoleUI().start();
    }
}