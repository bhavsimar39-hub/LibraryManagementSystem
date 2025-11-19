package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Transaction {
    private String transactionId;
    private String bookId;
    private String studentId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate actualReturnDate;
    private double fine;
    private boolean isReturned;
    
    // Constants
    private static final int LOAN_PERIOD_DAYS = 14;
    
    // Constructor for new issue
    public Transaction(String transactionId, String bookId, String studentId) {
        this.transactionId = transactionId;
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = LocalDate.now();
        this.dueDate = issueDate.plusDays(LOAN_PERIOD_DAYS);
        this.actualReturnDate = null;
        this.fine = 0.0;
        this.isReturned = false;
    }
    
    // Calculate fine for overdue books
    public double calculateFine() {
        if (actualReturnDate == null) {
            actualReturnDate = LocalDate.now();
        }
        
        if (actualReturnDate.isAfter(dueDate)) {
            long overdueDays = ChronoUnit.DAYS.between(dueDate, actualReturnDate);
            fine = overdueDays * services.FineCalculator.FINE_PER_DAY;
        } else {
            fine = 0.0;
        }
        
        return fine;
    }
    
    // Display transaction info
    public void displayInfo() {
        System.out.println("\n--- Transaction Information ---");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("Book ID: " + bookId);
        System.out.println("Student ID: " + studentId);
        System.out.println("Issue Date: " + issueDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Return Date: " + (actualReturnDate != null ? actualReturnDate : "Not Returned"));
        System.out.println("Fine: ₹" + fine);
        System.out.println("Status: " + (isReturned ? "Returned" : "Issued"));
    }
    
    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }
    
    public String getBookId() {
        return bookId;
    }
    
    public String getStudentId() {
        return studentId;
    }
    
    public LocalDate getIssueDate() {
        return issueDate;
    }
    
    public LocalDate getDueDate() {
        return dueDate;
    }
    
    public LocalDate getActualReturnDate() {
        return actualReturnDate;
    }
    
    public void setActualReturnDate(LocalDate actualReturnDate) {
        this.actualReturnDate = actualReturnDate;
    }
    
    public double getFine() {
        return fine;
    }
    
    public void setFine(double fine) {
        this.fine = fine;
    }
    
    public boolean isReturned() {
        return isReturned;
    }
    
    public void setReturned(boolean returned) {
        isReturned = returned;
    }
    
    // For CSV conversion
    public String toCSV() {
        return transactionId + "," + bookId + "," + studentId + "," + 
               issueDate + "," + dueDate + "," + 
               (actualReturnDate != null ? actualReturnDate : "") + "," + 
               fine + "," + isReturned;
    }
    
    // Create Transaction from CSV line
    public static Transaction fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Transaction transaction = new Transaction(parts[0], parts[1], parts[2]);
        transaction.issueDate = LocalDate.parse(parts[3]);
        transaction.dueDate = LocalDate.parse(parts[4]);
        if (!parts[5].isEmpty()) {
            transaction.actualReturnDate = LocalDate.parse(parts[5]);
        }
        transaction.fine = Double.parseDouble(parts[6]);
        transaction.isReturned = Boolean.parseBoolean(parts[7]);
        return transaction;
    }
}
