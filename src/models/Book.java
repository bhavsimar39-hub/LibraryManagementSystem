package models;

public class Book {
    private String bookId;
    private String title;
    private String author;
    private String category;
    private int totalCopies;
    private int availableCopies;
    
    // Constructor
    public Book(String bookId, String title, String author, String category, int totalCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = totalCopies;
    }
    
    // Check if book is available
    public boolean isAvailable() {
        return availableCopies > 0;
    }
    
    // Issue a copy of the book
    public boolean issueBook() {
        if (isAvailable()) {
            availableCopies--;
            return true;
        }
        return false;
    }
    
    // Return a copy of the book
    public void returnBook() {
        if (availableCopies < totalCopies) {
            availableCopies++;
        }
    }
    
    // Display book information
    public void displayInfo() {
        System.out.println("\n--- Book Information ---");
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Category: " + category);
        System.out.println("Available: " + availableCopies + "/" + totalCopies);
    }
    
    // Getters and Setters
    public String getBookId() {
        return bookId;
    }
    
    public void setBookId(String bookId) {
        this.bookId = bookId;
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
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public int getTotalCopies() {
        return totalCopies;
    }
    
    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }
    
    public int getAvailableCopies() {
        return availableCopies;
    }
    
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }
    
    // For CSV conversion
    public String toCSV() {
        return bookId + "," + title + "," + author + "," + category + "," + 
               totalCopies + "," + availableCopies;
    }
    
    // Create Book from CSV line
    public static Book fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Book book = new Book(parts[0], parts[1], parts[2], parts[3], Integer.parseInt(parts[4]));
        book.setAvailableCopies(Integer.parseInt(parts[5]));
        return book;
    }
}
