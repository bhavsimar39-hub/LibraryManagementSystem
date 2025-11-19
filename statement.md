# Project Statement

## Problem Statement

So, I've been going to our college library pretty regularly, and I noticed they're still using those old register books to track everything. The librarian literally writes down every single detail by hand when you borrow or return a book.

Here's what problems I saw:

**The register gets messy:** After a few months, the handwriting becomes hard to read. Sometimes pages get torn or coffee gets spilled on them. I've actually seen the librarian struggle to read old entries.

**Everything takes forever:** During exam time when everyone needs books, you have to wait in a long queue. The librarian has to flip through pages to find if a book is available, then write all your details, calculate the return date manually... it takes like 5-10 minutes per person!

**Fine calculation mistakes:** When books are overdue, the librarian has to count days on a calendar and multiply by ₹5 using a calculator. Sometimes they make mistakes and students argue about the fine amount.

**No way to check availability:** You have to physically go to the library to ask if a book is available. Many times I've wasted trips because the book I wanted was already issued.

**Overdue books are hard to track:** The librarian told me they have to manually go through all entries to find who has overdue books. It's time-consuming and some students just forget to return books because there's no reminder.

**One register = one point of failure:** If that register gets lost or damaged (which happened last year during monsoon!), all the data is gone forever. They don't have any backup.

That's when I thought - why not make a simple program to solve all this? And that's how this project started.

---

## Scope of the Project

### What I've Built So Far:

I wanted to keep it simple and focus on the core problems first. Here's what my system can do:

**Book Management:**
- Add new books with all details (ID, title, author, category, number of copies)
- Search for books by typing any part of the title or author name
- See all books and how many copies are available
- The system automatically tracks when books are issued and returned

**Student Stuff:**
- Register students with their details (BHAV SIMAR,24BSA10145)
- Keep track of how many books each student has borrowed
- Make sure no student takes more than 3 books (that's the library rule)

**Issue and Return:**
- When issuing a book, it automatically calculates the return date (14 days from today)
- Creates a transaction ID so we can track everything
- When returning, it automatically calculates if there's any fine
- No calculator needed - the computer does it instantly!

**Reports:**
- Can see which books are currently with students
- Shows which books are overdue
- Calculates total fine collected
- Helps librarian identify who needs to be reminded

**Data Saving:**
- Everything saves to simple CSV files (like Excel files)
- So even if you close the program, all data is safe
- Can easily take backup by copying these files

### What I Didn't Include (Yet):

To be honest, I had to keep some things simple because I'm still learning:

- Right now it's just a console program (that black command window). No fancy graphics or buttons.
- Only one person can use it at a time. Can't handle multiple librarians working together.
- Using CSV files instead of a proper database. It works fine for our college size though!
- No automatic email or SMS reminders for due dates (that would be cool to add later)

### What I Want to Add in Future:

If I get time (and maybe learn more stuff), I'd love to add:
- A proper GUI with buttons and windows
- Send automatic emails to students 2 days before due date
- Make it web-based so students can check book availability from their phone
- Add a barcode scanner to make issuing faster
- Maybe even a recommendation system like "Students who borrowed this book also liked..."

But for now, even this basic version solves the main problems!

---

## Target Users

I designed this system thinking about who will actually use it:

### The Main Users:

**Librarians (Uncle/Aunty at the desk)**

They're the ones who will use this system all day. I made sure it's super simple because not all librarians are tech-savvy. Just numbered menu options - press 1 for add book, press 2 for register student, etc.

What they'll do:
- Add new books when library buys them
- Register new students (first years mostly)
- Issue books to students
- Take books back when returned
- Collect fines if someone's late
- Check reports at end of the day

I tested it with our college librarian, and she said even she can use it without help!

**Students (That's Us!)**

We don't directly use the system - the librarian operates it for us. But we benefit from:
- Faster service (no more 10-minute waits)
- Accurate fine calculation (no more arguments)
- Fair system (computer doesn't have favorites!)
- Our borrowing history is tracked properly

**Library In-charge / Management**

They need reports to know:
- How many books are being borrowed
- Which books are most popular (helps decide what to buy next)
- How much fine is being collected
- If the library is being used properly

They don't need to know Java or how the program works. Librarian just shows them the reports.

**IT Support (If Something Goes Wrong)**

Our college IT department can help if:
- Program stops working
- Need to install it on a new computer
- Need to take backup of data
- Any technical issues

Since I've written clean code with comments, they should be able to understand it.

### Who This Won't Help:

To be honest, this won't help:
- Libraries with 10,000+ books (need a proper database)
- Libraries wanting fancy features (need professional software)
- Places with multiple branches (need network system)

But for a college library like ours? Perfect fit!

---

## High-Level Features

Let me explain what my system actually does:

### Feature 1: Managing Books

This is the basic stuff - keeping track of all books in the library.

**What You Can Do:**
- Add a new book by entering Book ID (like B001), title, author, category, and how many copies
- Search for any book - just type part of the name and it shows all matches
- See all books in one list with how many are available
- If a book has 5 copies and 2 are issued, it shows "Available: 3/5"

**How It Helps:**
No more flipping through pages! Type "java" and instantly see all Java books. Type an author name and see all their books.

**Smart Features I Added:**
- You can't add duplicate Book IDs (computer checks automatically)
- When someone issues a book, available count goes down by 1
- When they return it, count goes back up
- Everything saves automatically

**Example:**
Librarian adds book:
Book ID: B001
Title: Java Programming
Author: Herbert Schildt
Category: Programming

System saves it. Now if 2 students borrow this book,
it

### Feature 2: Student Registration

Before a student can borrow books, they need to be in the system.

**What Gets Stored:**
- Student ID (like S001, S002...)
- Full name
- Roll number (like 23BCE1234)
- Email and phone (for contact)
- How many books they currently have

**The 3-Book Rule:**
Our library only allows 3 books per student. I've coded this rule - if someone already has 3 books, the system won't let them take a 4th. Shows error message: "Sorry, you already have maximum books!"

**Why This Matters:**
Some students used to hoard books before exams. Now everyone gets fair chance!

### Feature 3: Issuing and Returning Books

This is the main part - when students actually borrow and return books.

**When Issuing:**
1. Librarian enters Student ID and Book ID
2. Computer checks:
   - Does this student exist? ✓
   - Does this book exist? ✓
   - Is book available? ✓
   - Does student already have 3 books? ✓
3. If all checks pass:
   - Creates a Transaction ID (like TXN001)
   - Sets today as issue date
   - Automatically calculates due date (today + 14 days)
   - Updates book count
   - Saves everything
   - Shows transaction receipt with due date

**When Returning:**
1. Librarian enters Transaction ID
2. Computer checks:
   - Does this transaction exist? ✓
   - Was it already returned? ✓
3. Calculates fine:
   - If returned on or before due date: Fine = ₹0 ✓
   - If late: Fine = (days overdue) × ₹5
4. Updates everything:
   - Marks transaction as returned
   - Updates book availability
   - Updates student's book count
   - Shows fine amount (if any)

**Example:**
ssue Date: Nov 1, 2025
Due Date: Nov 15, 2025
Return Date: Nov 20, 2025

Overdue = 5 days
Fine = 5 × ₹5 = ₹25

Computer shows: "Book overdue by 5 days. Please collect ₹25 fine."

No manual calculation needed! No mistakes possible!

### Feature 4: Reports and Fine Tracking

At the end of the day, librarian needs to know what happened.

**Reports I've Included:**

**Currently Issued Books Report:**
- Shows all books that are currently with students
- Their due dates
- Which ones are overdue (shown in red alert)
- Who has them

**Fine Collection Report:**
- Total fine collected today/this month
- How much is pending (overdue books not yet returned)
- List of students with overdue books

**All Books Overview:**
- Complete library inventory
- What's available, what's issued

**Why Reports Help:**
- Management can see if library is being used
- Can identify students who never return on time
- Helps plan budget (fine collection data)
- Easy to spot if any book is lost

### Feature 5: Data Never Gets Lost

This was super important to me - data safety.

**How I Did It:**
Instead of keeping everything in computer memory (which disappears when you close the program), I save everything to files.

**Three Files Created:**
1. `books.csv` - All book details
2. `students.csv` - All student records
3. `transactions.csv` - Complete history of issues/returns

**Why CSV?**
- Simple format that even Excel can open
- Easy to read and edit if needed
- No database installation needed
- Easy to backup - just copy the data folder!

**When Data Saves:**
- Every time you add a book → saves immediately
- Every time you issue/return → saves immediately
- Never have to click "Save" button - it's automatic!

**What If Computer Crashes?**
No problem! When you restart the program, it loads all data from CSV files. Nothing is lost!

I tested this - added 10 books, closed program without saving, opened again - all 10 books were there! ✓

---

## How I Built This

Just documenting my approach so someone reading this understands:

**Programming Language:** Java (because that's what we're learning in college)

**OOP Concepts I Used:**
- Made `Person` as parent class, then `Student` and `Librarian` inherit from it (Inheritance)
- Used private variables with public getter/setter methods (Encapsulation)
- Same method name `displayInfo()` but different implementation for Student vs Book (Polymorphism)

**Data Structures:**
- ArrayList to store books, students, transactions (because size can grow)
- Used loops to search through them

**File Handling:**
- BufferedReader to read CSV files
- BufferedWriter to write to CSV files
- Added error handling in case file doesn't exist

**Date Handling:**
- Used `LocalDate` class for dates (modern way)
- Used `ChronoUnit.DAYS.between()` to calculate days between dates
- No more manual calendar counting!

---

## Testing I Did

Before submitting, I tested everything multiple times:

- Added 20 different books ✓
- Registered 10 students ✓  
- Tried to issue 4th book to a student → correctly showed error ✓
- Tried to issue unavailable book → correctly showed error ✓
- Returned book on time → showed ₹0 fine ✓
- Returned book 7 days late → correctly calculated ₹35 fine ✓
- Closed program and reopened → all data still there ✓
- Searched with wrong book name → showed "no results" ✓
- Generated all reports → working fine ✓

Also asked my roommate to test it - found 2 bugs which I fixed!

---

## Challenges I Faced

Not gonna lie, building this wasn't easy. Some problems I struggled with:

**Problem 1: Date Parsing Headache**
When I tried to load transactions from CSV, if a book wasn't returned yet, the "return date" field was empty. My code tried to parse empty string as date and crashed! Took me 3 hours to figure this out. Fixed by checking `if (!parts[5].isEmpty())` before parsing.

**Problem 2: File Not Found on First Run**
Forgot that `data/` folder won't exist when someone runs the program first time. Program crashed with "FileNotFoundException". Added code to create folder automatically if it doesn't exist.

**Problem 3: Student Book Count Not Updating**
This was frustrating! When a student returned a book, their `booksIssued` count wasn't decreasing. Turned out I was updating the Transaction object but forgetting to update the Student object. Fixed by calling `student.returnBook()` method.

---

## What I Learned

This project taught me way more than just studying for exams:

- **File handling is tricky:** So many edge cases - file doesn't exist, empty file, corrupted data, etc.
- **User input validation is important:** Users can type anything! Need to handle all cases.
- **Breaking code into classes helps:** At first I had everything in one big Main class. Then separated into models and services - much easier to manage!
- **Testing is important:** Found so many bugs by testing different scenarios
- **Commenting helps:** When I came back to my code after 2 days, I had forgotten what some parts did. Comments saved me!

---

## Future Plans

If I continue this project:
- Add a GUI (maybe using JavaFX - looks cool in YouTube tutorials)
- Connect to a real database (MySQL)
- Add email notifications
- Make it web-based
- Add more reports and statistics

But honestly? Even this simple version solves real problems. And I'm proud of it!

---

**Made by:** [Your Name]  
**Roll Number:** [Your Roll Number]  
**College:** VIT Bhopal  
**Semester:** 3 (2nd Year)  
**Date:** November 2025

**P.S.** - This was my first real Java project. There might be bugs I haven't discovered yet, but hey, that's how we learn! 😊
