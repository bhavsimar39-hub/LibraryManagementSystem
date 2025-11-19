import services.LibraryManager;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibraryManager manager = new LibraryManager();
        
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   LIBRARY MANAGEMENT SYSTEM - v1.0    ║");
        System.out.println("╚════════════════════════════════════════╝");
        
        while (true) {
            displayMenu();
            System.out.print("\nEnter your choice: ");
            
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                
                switch (choice) {
                    case 1:
                        manager.addBook();
                        break;
                    case 2:
                        manager.addStudent();
                        break;
                    case 3:
                        manager.issueBook();
                        break;
                    case 4:
                        manager.returnBook();
                        break;
                    case 5:
                        manager.searchBook();
                        break;
                    case 6:
                        manager.displayAllBooks();
                        break;
                    case 7:
                        manager.displayAllStudents();
                        break;
                    case 8:
                        manager.displayIssuedBooks();
                        break;
                    case 9:
                        manager.generateFineReport();
                        break;
                    case 0:
                        System.out.println("\n✓ Thank you for using Library Management System!");
                        scanner.close();
                        System.exit(0);
                        break;
                    default:
                        System.out.println("✗ Invalid choice! Please try again.");
                }
                
                // Pause before showing menu again
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
                
            } catch (NumberFormatException e) {
                System.out.println("✗ Invalid input! Please enter a number.");
            } catch (Exception e) {
                System.out.println("✗ An error occurred: " + e.getMessage());
            }
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║            MAIN MENU                  ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║  1. Add New Book                      ║");
        System.out.println("║  2. Add New Student                   ║");
        System.out.println("║  3. Issue Book                        ║");
        System.out.println("║  4. Return Book                       ║");
        System.out.println("║  5. Search Book                       ║");
        System.out.println("║  6. Display All Books                 ║");
        System.out.println("║  7. Display All Students              ║");
        System.out.println("║  8. Display Issued Books              ║");
        System.out.println("║  9. Generate Fine Report              ║");
        System.out.println("║  0. Exit                              ║");
        System.out.println("╚════════════════════════════════════════╝");
    }
}
