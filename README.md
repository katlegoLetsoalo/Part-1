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

# 📚 Reference List

Baeldung, 2025. Guide to Regular Expressions in Java [online]. Available at: https://www.baeldung.com/regular-expressions-java
 [Accessed 17 September 2025]

JUnit, 2025. JUnit 4 Testing Framework [online]. Available at: https://junit.org/junit4/
 [Accessed 28 August 2025].
 
 OpenAI, 2025. ChatGPT (Mar 1 version) [online]. Available at: https://chat.openai.com/
 [Accessed 28 August 2025].

Oracle, 2025. Class Scanner (Java Platform SE 17) [online]. Available at: https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html
 [Accessed 9 September 2025].
