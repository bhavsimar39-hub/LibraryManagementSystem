package models;

public class Librarian extends Person {
    private String employeeId;
    
    public Librarian(String personId, String name, String email, String phone, String employeeId) {
        super(personId, name, email, phone);
        this.employeeId = employeeId;
    }
    
    @Override
    public void displayInfo() {
        System.out.println("\n--- Librarian Information ---");
        System.out.println("Librarian ID: " + getPersonId());
        System.out.println("Name: " + getName());
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}
