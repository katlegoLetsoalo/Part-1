# Part-1
Project Overview

This project is a simple Java console application that demonstrates user registration and login functionality. It consists of three main parts:

# Main.java
- Handles user interaction through the console.
- Guides the user through registration (first name, last name, username, password, and cellphone number).
- Once registration is successful, prompts the user to log in with their chosen credentials.

# Login.java
- Contains the business logic for validating user details.
- Username must contain an underscore (_) and be no longer than five characters.
- Password must be at least 8 characters long, with at least one uppercase letter, one number, and one special character.
- Cellphone number must follow the South African international format (e.g., +27831234567).
- Provides methods for registering a user, checking credentials, and returning login status messages.

# LoginTest.java
- JUnit 4 test class for unit testing the methods in Login.java.
Includes tests for:
- Correct and incorrect usernames.
- Correct and incorrect passwords.
- Correct and incorrect cellphone numbers.
- Registration success and failure.
- Login success and failure.
- Getter methods (username(), password(), cellPhone(), firstName(), lastName()).

# Purpose
The purpose of the project is to show understanding of object-oriented programming, data validation, and unit testing in Java. It can also serve as a basic model for more complex authentication systems in real-world applications.

# Part 2
Sending Messages of the “QuickChat” application. It has been fully coded and tested to meet the assignment requirements. The focus was on loops, string manipulation, file handling (JSON), and basic software testing principles using JUnit.

1. Functional Overview

The program simulates a simple chat system that allows users to log in, send, store, and manage messages.
It includes the following components:

a. Login Functionality

Class: Login.java

Handles user authentication.

Users must log in with valid credentials before using any features.

Displays success or failure messages via JOptionPane.

b. Message Management

Class: Message.java

Handles all message-related operations, including:

Generating a unique 10-digit message ID.

Validating recipient phone numbers (must start with “+” and be ≤13 digits).

Validating message length (≤250 characters).

Generating a Message Hash (first two digits of ID + message number + first and last words).

Allowing user options: Send, Disregard, or Store.

Displaying message details in the correct order.

Storing messages in a JSON file (messages.json) using the storeMessage() method.

Counting total messages sent.

c. Main Application

Class: Main.java

Handles the user interface loop via JOptionPane menus.

Menu options:

Send Messages

Show Recently Sent Messages (coming soon)

Quit

Ensures the app runs until the user selects “Quit.”

Displays total messages sent at the end.

d. Unit Testing

Class: MainTest.java

Implements JUnit tests to verify:

Message length validation (success and failure cases)

Recipient number formatting

Message hash generation

Message ID creation

JSON storage file creation and persistence

Confirms that all key functions operate correctly according to assignment criteria.

e. JSON Storage

Messages are stored in a structured format (messages.json) for persistence.

Uses the json-simple library to parse and write JSON.

Ensures new messages are appended correctly without data loss.

f. GitHub & CI/CD

The project is designed for Git version control using a feature branch workflow (git checkout -b featureBranch).

Compatible with GitHub Actions for CI testing when code is pushed.

2. Programming Techniques Demonstrated
Concept	Demonstrated In	Description
Loops	Main.java	Iterates through multiple user-entered messages
String Handling	Message.java	Manipulates recipient numbers and message text
File I/O	Message.java	Reads and writes JSON message files
Error Handling	Message.java	Uses try-catch blocks for file operations
Encapsulation	Login.java, Message.java	Uses private fields with public methods
JUnit Testing	MainTest.java	Tests program logic and data validation
GitHub/CI Workflow	Entire project	Supports modern DevOps practices
3. Outcome

✅ The project:

Meets all assignment specifications from Part 2.

Demonstrates practical understanding of loops, strings, object-oriented design, file handling, and unit testing.

Is ready for CI/CD setup using GitHub Actions for automated testing.

# Part 3 — Store Data and Display Task Report
1. Purpose

The purpose of this part of the application is to store, manage, and display messages in the QuickChat system. Users can send, store, disregard, and view messages, while the system keeps track of Message IDs and Message Hashes for management and reporting purposes.

2. Features Implemented
2.1 Arrays

The following arrays were implemented in MessageManager:

Array	Description
Sent Messages	Contains all messages successfully sent.
Disregarded Messages	Contains messages discarded by the user.
Stored Messages	Contains messages saved for later; read from and written to JSON files.
Message Hashes	Contains hashes of all messages for unique identification.
Message IDs	Contains unique IDs of all messages.
2.2 Functional Capabilities

Using these arrays, the system can:

Display the sender and recipient of all sent messages.

Display the longest sent message.

Search for a message by its Message ID and display its recipient and content.

Search for all messages sent to a particular recipient.

Delete a message using its Message Hash.

Display a report listing all sent messages with full details (hash, recipient, content).

3. Unit Tests Implemented

JUnit tests ensure the correct functionality of the application:

Login Tests

Valid login.

Invalid login.

Registration validation (success and failure scenarios).

Message Validation Tests

Message length check (≤250 characters).

Recipient validation (+27 format).

Message ID and hash generation.

Message Manager Tests

Adding messages to Sent, Stored, and Disregarded arrays.

Displaying the longest message.

Searching by Message ID.

Searching all messages for a specific recipient.

Deleting a message using Message Hash.

Displaying the report of all sent messages.

Test data used (as per assignment instructions):

Message	Recipient	Message Content	Flag
1	+27834557896	Did you get the cake?	Sent
2	+27838884567	Where are you? You are late! I have asked you to be on time.	Stored
3	+27834484567	Yohoooo, I am at your gate.	Disregard
4	0838884567	It is dinner time!	Sent
5	+27838884567	Ok, I am leaving without you.	Stored
4. Workflow

User logs in or registers.

Messages are sent, stored, or disregarded.

Sent and stored messages are saved to JSON on exit.

Users can display reports or search messages as required.


# 📚 Reference List

Baeldung, 2025. Guide to Regular Expressions in Java [online]. Available at: https://www.baeldung.com/regular-expressions-java
 [Accessed 17 September 2025]

JUnit, 2025. JUnit 4 Testing Framework [online]. Available at: https://junit.org/junit4/
 [Accessed 28 August 2025].
 
 OpenAI, 2025. ChatGPT (Mar 1 version) [online]. Available at: https://chat.openai.com/
 [Accessed 28 August 2025].
 Erasmus, B.J., Rudansky-Kloppers, S. and others. 2022. Introduction to Business Management. 11th ed. Cape Town: Oxford University Press Southern Africa.
— (used for contextual understanding of structured business logic and professionalism in software design, as part of your IIE coursework framework).

Oracle. 2024. The Java™ Tutorials: Essential Classes – Basic I/O. [online] Available at: https://docs.oracle.com/javase/tutorial/essential/io/
 [Accessed 13 October 2025].
— (used for understanding Java file input/output and exception handling).

JSON.simple. 2012. JSON.simple – A simple Java toolkit for JSON. [online] Available at: https://code.google.com/archive/p/json-simple/
 [Accessed 13 October 2025].
— (used for implementing JSON storage and parsing logic).

JUnit. 2023. JUnit 4 Documentation. [online] Available at: https://junit.org/junit4/
 [Accessed 13 October 2025].
— (used to create and run automated unit tests in MainTest.java).

GitHub Docs. 2024. GitHub Actions Documentation. [online] Available at: https://docs.github.com/en/actions
 [Accessed 13 October 2025].
— (used to guide integration of automated CI testing as mentioned in assignment instructions).

YouTube. 2022. Automate your tests with GitHub Actions. [video online] Available at: https://www.youtube.com/watch?v=b3cIRsVPLR4&t=282s
 [Accessed 13 October 2025].
— (referenced in assignment brief for CI/CD setup guidance).
Oracle, 2025. Class Scanner (Java Platform SE 17) [online]. Available at: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
 [Accessed 9 September 2025].

The Independent Institute of Education (IIE). 2025. IIE Assessment Guidelines and Harvard Referencing Guide. [online] Available at: https://www.iie.ac.za/
 [Accessed 13 October 2025].
— (used to ensure compliance with IIE Harvard referencing style and academic integrity).
Erasmus, B., Rudansky-Kloppers, S. & Strydom, J. (eds.) 2023. Introduction to Business Management. 11th ed. Cape Town: Oxford University Press Southern Africa.

Oracle. 2025. The Java™ Tutorials: JUnit. [Online]. Available at: https://docs.oracle.com/javase/tutorial/junit/
 [Accessed 18 November 2025].

Geekulcha. 2024. About Us. [Online]. Available at: https://geekulcha.com
 [Accessed 18 November 2025].

JSON.org. 2025. JSON Simple Library Documentation. [Online]. Available at: https://www.json.org/json-en.html
 [Accessed 17 November 2025].
