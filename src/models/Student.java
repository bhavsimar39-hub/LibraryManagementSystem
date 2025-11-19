package models;

public class Student extends Person {
    private String rollNumber;
    private int booksIssued;
    private static final int MAX_BOOKS_ALLOWED = 3;
    
    // Constructor
    public Student(String personId, String name, String email, String phone, String rollNumber) {
        super(personId, name, email, phone);
        this.rollNumber = rollNumber;
        this.booksIssued = 0;
    }
    
    // Check if student can issue more books
    public boolean canIssueMore() {
        return booksIssued < MAX_BOOKS_ALLOWED;
    }
    
    // Increment books issued count
    public void issueBook() {
        if (canIssueMore()) {
            booksIssued++;
        }
    }
    
    // Decrement books issued count
    public void returnBook() {
        if (booksIssued > 0) {
            booksIssued--;
        }
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("Student ID: " + getPersonId());
        System.out.println("Name: " + getName());
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Books Issued: " + booksIssued + "/" + MAX_BOOKS_ALLOWED);
    }
    
    // Getters and Setters
    public String getRollNumber() {
        return rollNumber;
    }
    
    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }
    
    public int getBooksIssued() {
        return booksIssued;
    }
    
    public void setBooksIssued(int booksIssued) {
        this.booksIssued = booksIssued;
    }
    
    // For CSV conversion
    public String toCSV() {
        return getPersonId() + "," + getName() + "," + getEmail() + "," + 
               getPhone() + "," + rollNumber + "," + booksIssued;
    }
    
    // Create Student from CSV line
    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Student student = new Student(parts[0], parts[1], parts[2], parts[3], parts[4]);
        student.setBooksIssued(Integer.parseInt(parts[5]));
        return student;
    }
}
