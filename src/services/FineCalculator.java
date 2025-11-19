package services;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FineCalculator {
    // Fine per day in rupees
    public static final double FINE_PER_DAY = 5.0;
    
    // Calculate fine based on due date and actual return date
    public static double calculateFine(LocalDate dueDate, LocalDate actualReturnDate) {
        if (actualReturnDate == null || actualReturnDate.isBefore(dueDate) || actualReturnDate.isEqual(dueDate)) {
            return 0.0;
        }
        
        // Calculate overdue days
        long overdueDays = ChronoUnit.DAYS.between(dueDate, actualReturnDate);
        double fine = overdueDays * FINE_PER_DAY;
        
        System.out.println("Book is overdue by " + overdueDays + " days.");
        System.out.println("Fine: ₹" + fine);
        
        return fine;
    }
    
    // Check if book is overdue currently
    public static boolean isOverdue(LocalDate dueDate) {
        return LocalDate.now().isAfter(dueDate);
    }
    
    // Calculate potential fine if returned today
    public static double calculateCurrentFine(LocalDate dueDate) {
        if (!isOverdue(dueDate)) {
            return 0.0;
        }
        return calculateFine(dueDate, LocalDate.now());
    }
}
