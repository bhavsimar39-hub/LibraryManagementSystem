package services;

import models.Book;
import models.Student;
import models.Transaction;

import java.io.*;
import java.util.ArrayList;

public class FileHandler {
    // File paths
    private static final String BOOKS_FILE = "data/books.csv";
    private static final String STUDENTS_FILE = "data/students.csv";
    private static final String TRANSACTIONS_FILE = "data/transactions.csv";
    
    // Save books to file
    public static void saveBooks(ArrayList<Book> books) {
        try {
            // Create data directory if it doesn't exist
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(BOOKS_FILE));
            
            // Write header
            writer.write("BookID,Title,Author,Category,TotalCopies,AvailableCopies\n");
            
            // Write each book
            for (Book book : books) {
                writer.write(book.toCSV() + "\n");
            }
            
            writer.close();
            System.out.println("Books saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving books: " + e.getMessage());
        }
    }
    
    // Load books from file
    public static ArrayList<Book> loadBooks() {
        ArrayList<Book> books = new ArrayList<>();
        
        try {
            File file = new File(BOOKS_FILE);
            if (!file.exists()) {
                System.out.println("No existing book data found. Starting fresh.");
                return books;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(BOOKS_FILE));
            
            // Skip header
            String line = reader.readLine();
            
            // Read each book
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Book book = Book.fromCSV(line);
                    books.add(book);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + books.size() + " books from file.");
            
        } catch (IOException e) {
            System.out.println("Error loading books: " + e.getMessage());
        }
        
        return books;
    }
    
    // Save students to file
    public static void saveStudents(ArrayList<Student> students) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENTS_FILE));
            
            // Write header
            writer.write("StudentID,Name,Email,Phone,RollNumber,BooksIssued\n");
            
            // Write each student
            for (Student student : students) {
                writer.write(student.toCSV() + "\n");
            }
            
            writer.close();
            System.out.println("Students saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }
    
    // Load students from file
    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        
        try {
            File file = new File(STUDENTS_FILE);
            if (!file.exists()) {
                System.out.println("No existing student data found. Starting fresh.");
                return students;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE));
            
            // Skip header
            String line = reader.readLine();
            
            // Read each student
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Student student = Student.fromCSV(line);
                    students.add(student);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + students.size() + " students from file.");
            
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
        
        return students;
    }
    
    // Save transactions to file
    public static void saveTransactions(ArrayList<Transaction> transactions) {
        try {
            File directory = new File("data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            
            BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE));
            
            // Write header
            writer.write("TransactionID,BookID,StudentID,IssueDate,DueDate,ActualReturnDate,Fine,IsReturned\n");
            
            // Write each transaction
            for (Transaction transaction : transactions) {
                writer.write(transaction.toCSV() + "\n");
            }
            
            writer.close();
            System.out.println("Transactions saved successfully!");
            
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }
    
    // Load transactions from file
    public static ArrayList<Transaction> loadTransactions() {
        ArrayList<Transaction> transactions = new ArrayList<>();
        
        try {
            File file = new File(TRANSACTIONS_FILE);
            if (!file.exists()) {
                System.out.println("No existing transaction data found. Starting fresh.");
                return transactions;
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE));
            
            // Skip header
            String line = reader.readLine();
            
            // Read each transaction
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    Transaction transaction = Transaction.fromCSV(line);
                    transactions.add(transaction);
                }
            }
            
            reader.close();
            System.out.println("Loaded " + transactions.size() + " transactions from file.");
            
        } catch (IOException e) {
            System.out.println("Error loading transactions: " + e.getMessage());
        }
        
        return transactions;
    }
}
