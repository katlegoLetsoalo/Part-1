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

The Independent Institute of Education (IIE). 2025. IIE Assessment Guidelines and Harvard Referencing Guide. [online] Available at: https://www.iie.ac.za/
 [Accessed 13 October 2025].
— (used to ensure compliance with IIE Harvard referencing style and academic integrity).

Oracle, 2025. Class Scanner (Java Platform SE 17) [online]. Available at: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
 [Accessed 9 September 2025].
