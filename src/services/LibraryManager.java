package services;

import models.Book;
import models.Student;
import models.Transaction;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<Transaction> transactions;
    private Scanner scanner;
    
    // Constructor
    public LibraryManager() {
        // Load data from files
        this.books = FileHandler.loadBooks();
        this.students = FileHandler.loadStudents();
        this.transactions = FileHandler.loadTransactions();
        this.scanner = new Scanner(System.in);
    }
    
    // Add a new book
    public void addBook() {
        System.out.println("\n=== Add New Book ===");
        
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        
        // Check if book already exists
        if (findBookById(bookId) != null) {
            System.out.println("Error: Book with this ID already exists!");
            return;
        }
        
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        
        System.out.print("Enter Author: ");
        String author = scanner.nextLine();
        
        System.out.print("Enter Category: ");
        String category = scanner.nextLine();
        
        System.out.print("Enter Total Copies: ");
        int totalCopies = Integer.parseInt(scanner.nextLine());
        
        // Create new book
        Book book = new Book(bookId, title, author, category, totalCopies);
        books.add(book);
        
        // Save to file
        FileHandler.saveBooks(books);
        
        System.out.println("Book added successfully!");
        book.displayInfo();
    }
    
    // Add a new student
    public void addStudent() {
        System.out.println("\n=== Add New Student ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        // Check if student already exists
        if (findStudentById(studentId) != null) {
            System.out.println("Error: Student with this ID already exists!");
            return;
        }
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter Roll Number: ");
        String rollNumber = scanner.nextLine();
        
        // Create new student
        Student student = new Student(studentId, name, email, phone, rollNumber);
        students.add(student);
        
        // Save to file
        FileHandler.saveStudents(students);
        
        System.out.println("Student added successfully!");
        student.displayInfo();
    }
    
    // Issue a book to a student
    public void issueBook() {
        System.out.println("\n=== Issue Book ===");
        
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found!");
            return;
        }
        
        // Check if student can issue more books
        if (!student.canIssueMore()) {
            System.out.println("Error: Student has already issued maximum books (3)!");
            return;
        }
        
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        
        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Error: Book not found!");
            return;
        }
        
        // Check if book is available
        if (!book.isAvailable()) {
            System.out.println("Error: Book is not available!");
            return;
        }
        
        // Generate transaction ID
        String transactionId = "TXN" + (transactions.size() + 1);
        
        // Create transaction
        Transaction transaction = new Transaction(transactionId, bookId, studentId);
        transactions.add(transaction);
        
        // Update book and student
        book.issueBook();
        student.issueBook();
        
        // Save all data
        FileHandler.saveBooks(books);
        FileHandler.saveStudents(students);
        FileHandler.saveTransactions(transactions);
        
        System.out.println("\nBook issued successfully!");
        transaction.displayInfo();
    }
    
    // Return a book
    public void returnBook() {
        System.out.println("\n=== Return Book ===");
        
        System.out.print("Enter Transaction ID: ");
        String transactionId = scanner.nextLine();
        
        Transaction transaction = findTransactionById(transactionId);
        if (transaction == null) {
            System.out.println("Error: Transaction not found!");
            return;
        }
        
        if (transaction.isReturned()) {
            System.out.println("Error: Book already returned!");
            return;
        }
        
        // Set return date and calculate fine
        transaction.setActualReturnDate(LocalDate.now());
        double fine = transaction.calculateFine();
        transaction.setReturned(true);
        
        // Update book and student
        Book book = findBookById(transaction.getBookId());
        Student student = findStudentById(transaction.getStudentId());
        
        if (book != null) {
            book.returnBook();
        }
        
        if (student != null) {
            student.returnBook();
        }
        
        // Save all data
        FileHandler.saveBooks(books);
        FileHandler.saveStudents(students);
        FileHandler.saveTransactions(transactions);
        
        System.out.println("\nBook returned successfully!");
        transaction.displayInfo();
        
        if (fine > 0) {
            System.out.println("\n⚠️  Please collect fine: ₹" + fine);
        }
    }
    
    // Search book by title, author, or ID
    public void searchBook() {
        System.out.println("\n=== Search Book ===");
        System.out.print("Enter search term (title/author/ID): ");
        String searchTerm = scanner.nextLine().toLowerCase();
        
        ArrayList<Book> results = new ArrayList<>();
        
        for (Book book : books) {
            if (book.getBookId().toLowerCase().contains(searchTerm) ||
                book.getTitle().toLowerCase().contains(searchTerm) ||
                book.getAuthor().toLowerCase().contains(searchTerm)) {
                results.add(book);
            }
        }
        
        if (results.isEmpty()) {
            System.out.println("No books found matching: " + searchTerm);
        } else {
            System.out.println("\nFound " + results.size() + " book(s):");
            for (Book book : results) {
                book.displayInfo();
            }
        }
    }
    
    // Display all books
    public void displayAllBooks() {
        System.out.println("\n=== All Books ===");
        if (books.isEmpty()) {
            System.out.println("No books in library!");
            return;
        }
        
        System.out.println("\nTotal Books: " + books.size());
        for (Book book : books) {
            book.displayInfo();
        }
    }
    
    // Display all students
    public void displayAllStudents() {
        System.out.println("\n=== All Students ===");
        if (students.isEmpty()) {
            System.out.println("No students registered!");
            return;
        }
        
        System.out.println("\nTotal Students: " + students.size());
        for (Student student : students) {
            student.displayInfo();
        }
    }
    
    // Display all issued books
    public void displayIssuedBooks() {
        System.out.println("\n=== Currently Issued Books ===");
        
        ArrayList<Transaction> issuedTransactions = new ArrayList<>();
        for (Transaction transaction : transactions) {
            if (!transaction.isReturned()) {
                issuedTransactions.add(transaction);
            }
        }
        
        if (issuedTransactions.isEmpty()) {
            System.out.println("No books currently issued!");
            return;
        }
        
        System.out.println("\nTotal Issued: " + issuedTransactions.size());
        for (Transaction transaction : issuedTransactions) {
            transaction.displayInfo();
            
            // Check if overdue
            if (FineCalculator.isOverdue(transaction.getDueDate())) {
                double currentFine = FineCalculator.calculateCurrentFine(transaction.getDueDate());
                System.out.println("⚠️  OVERDUE! Current Fine: ₹" + currentFine);
            }
        }
    }
    
    // Generate fine report
    public void generateFineReport() {
        System.out.println("\n=== Fine Report ===");
        
        double totalFineCollected = 0.0;
        double totalPendingFine = 0.0;
        int overdueCount = 0;
        
        for (Transaction transaction : transactions) {
            if (transaction.isReturned() && transaction.getFine() > 0) {
                totalFineCollected += transaction.getFine();
            } else if (!transaction.isReturned() && FineCalculator.isOverdue(transaction.getDueDate())) {
                double currentFine = FineCalculator.calculateCurrentFine(transaction.getDueDate());
                totalPendingFine += currentFine;
                overdueCount++;
            }
        }
        
        System.out.println("\n--- Summary ---");
        System.out.println("Total Fine Collected: ₹" + totalFineCollected);
        System.out.println("Total Pending Fine: ₹" + totalPendingFine);
        System.out.println("Overdue Books: " + overdueCount);
        
        // Show overdue details
        if (overdueCount > 0) {
            System.out.println("\n--- Overdue Books ---");
            for (Transaction transaction : transactions) {
                if (!transaction.isReturned() && FineCalculator.isOverdue(transaction.getDueDate())) {
                    System.out.println("\nTransaction ID: " + transaction.getTransactionId());
                    System.out.println("Book ID: " + transaction.getBookId());
                    System.out.println("Student ID: " + transaction.getStudentId());
                    System.out.println("Due Date: " + transaction.getDueDate());
                    double currentFine = FineCalculator.calculateCurrentFine(transaction.getDueDate());
                    System.out.println("Current Fine: ₹" + currentFine);
                }
            }
        }
    }
    
    // Helper method to find book by ID
    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
    
    // Helper method to find student by ID
    private Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getPersonId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
    
    // Helper method to find transaction by ID
    private Transaction findTransactionById(String transactionId) {
        for (Transaction transaction : transactions) {
            if (transaction.getTransactionId().equals(transactionId)) {
                return transaction;
            }
        }
        return null;
    }
}
