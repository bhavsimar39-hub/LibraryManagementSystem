# Library Management System

Hey! This is my library management project for college. Built it using Java to help solve the problem of managing books manually in our college library.

## About Me

**Name:**BHAV SIMAR SINGH
**Roll No:** 24BSA10145  
**Branch:** BTech CSE - Cloud Computing (2nd Year)  
**College:** VIT Bhopal  


## Why I Built This

So basically, I noticed that our college library still uses those old register books to track everything. The librarian has to write down manually when you issue a book, calculate fines with a calculator, and sometimes records get lost or become unreadable. 

I thought - why not make a simple program that does all this automatically? That's how this project started.

## What This Project Does

Here's what I managed to build:

**Book Stuff:**
- You can add books with all their details (ID, title, author, etc.)
- Search for books by name or author
- Shows how many copies are available
- Everything gets saved so data doesn't disappear when you close the program

**Student Management:**
- Register students with their roll numbers
- Keeps track of how many books each student has borrowed
- Made sure students can't issue more than 3 books (library rule!)

**Issuing and Returning Books:**
- When you issue a book, it automatically sets the due date to 14 days later
- When returning, it calculates fine automatically if the book is late
- ₹5 per day fine - just like real library rules

**Reports:**
- Can see which books are currently issued
- Who has overdue books
- Total fine collected

The best part? Everything saves to simple CSV files. No complicated database needed!

## Tech Stack (What I Used)

- Java (version 25 - the one installed on my laptop)
- Used OOP concepts we learned in class - inheritance, polymorphism, all that
- ArrayList and HashMap for storing data
- File handling with BufferedReader/Writer
- LocalDate for handling dates and calculating overdue days

## How My Project is Organized

I divided everything into packages to keep it clean:
My Project Folder/
├── src/
│ ├── models/ (All my entity classes here)
│ │ ├── Person.java (Made this abstract - parent class)
│ │ ├── Student.java (Inherits from Person)
│ │ ├── Book.java
│ │ └── Transaction.java
│ │
│ ├── services/ (Business logic stuff)
│ │ ├── FileHandler.java (Handles all CSV operations)
│ │ ├── FineCalculator.java (Calculates fines)
│ │ └── LibraryManager.java (Main controller)
│ │
│ └── Main.java (Entry point - has the menu)
│
├── bin/ (compiled .class files go here)
├── data/ (CSV files are stored here)
└── run.bat (made this so I can run with one click)

## Running the Project

### If You're on Windows (Easy Way):
Just double-click the `run.bat` file I created. It compiles and runs everything automatically!

### Command Line Way:
First compile everything
javac -d bin src/models/.java src/services/.java src/Main.java

Then run it
java -cp bin Main


## How to Actually Use It

When you run the program, you'll see a menu with numbered options.

**Adding Books:**
- Choose option 1
- Enter Book ID (like B001, B002, etc.)
- Fill in title, author, category, number of copies
- Done! It saves automatically

**Registering Students:**
- Option 2
- Student ID, name, email, phone, roll number
- Pretty straightforward

**Issuing Books:**
- Option 3
- Enter student ID and book ID
- Program checks if student already has 3 books (won't let them take more)
- Also checks if book is available
- Creates a transaction with due date

**Returning Books:**
- Option 4
- Enter the transaction ID (you get this when you issue the book)
- If it's late, it calculates fine automatically
- Shows you how much fine to collect

There are more options like searching, viewing all books, generating reports, etc.

## Testing I Did

I tested a bunch of scenarios to make sure everything works:

- Added 5 different books ✓
- Registered 3 students ✓
- Tried issuing when book was unavailable - showed error ✓
- Tried issuing 4th book to same student - correctly denied ✓
- Returned book on time - ₹0 fine ✓
- Returned book 7 days late - showed ₹35 fine (7 × 5) ✓
- Closed program and reopened - all data was still there ✓
- Searched for books - worked perfectly ✓

## Problems I Faced (and Fixed!)

**Problem 1:** Date Parsing Issue  
When I first tried to load transactions from CSV, the program crashed if someone hadn't returned a book yet (because return date was empty). Fixed it by checking if the field is empty before trying to parse the date.

**Problem 2:** File Not Found Error  
On first run, the program couldn't find the data folder and gave an error. So I added code to create the folder automatically if it doesn't exist.

**Problem 3:** Student Book Count Not Updating  
When a student returned a book, the count wasn't decreasing. Turned out I forgot to update the Student object after modifying the transaction. Spent like 2 hours debugging this lol.

## What I Learned

This project taught me a LOT:

- **OOP in Practice:** We studied inheritance in class, but using it in a real project helped me understand it way better. Like how Student and Librarian both extend Person.

- **File Handling:** First time doing actual file operations. Learned about BufferedReader/Writer, handling exceptions, formatting data in CSV.

- **Collections:** Used ArrayList everywhere. Learned how to iterate, search, and filter data.

- **Date Calculations:** The java.time package is really useful! ChronoUnit.DAYS.between() made calculating overdue days so easy.

- **Debugging:** Spent hours fixing bugs, but each bug taught me something new about how Java works.

## Things I Want to Add Later

Right now it's just a console program, but I have plans:

- Maybe add a proper GUI using Swing (we'll learn that next semester)
- Send email notifications when books are due
- Generate PDF receipts instead of just showing on screen
- Add a proper database instead of CSV files
- Maybe make it web-based someday?

## What I Used as Reference

- Our Java textbook by Herbert Schildt
- Java documentation when I got stuck
- GeeksforGeeks for understanding file handling
- Stack Overflow when I got weird errors
- Asked some seniors for help with the date calculation part

## Acknowledgments

Thanks to:
- My course instructor for the project idea and guidance
- My roommate who tested the program and found bugs
- My parents for supporting me
- Coffee for keeping me awake during late night coding sessions!

## Final Thoughts

This was my first proper Java project and I'm pretty happy with how it turned out. Sure, it's not perfect and there's a lot I could improve, but it works and solves a real problem. 

Learned way more by building this than just studying theory. If you're reading this and working on a similar project, good luck! Feel free to use any ideas from my code.

---

**Made with ☕ and lot of debugging**  
**VIT Bhopal | November 2025**

**P.S.** - If you find any bugs, let me know! 😅


