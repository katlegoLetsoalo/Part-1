/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPOE;

import java.util.regex.Pattern;
/**
 *
 * @author RC_Student_lab
 */
public class Login {
    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    // Constructor
    public Login(String username, String password, String cellPhone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Username must contain '_' and <=5 chars
    public boolean checkUserName() {
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }

    // Password complexity: 8+ chars, 1 uppercase, 1 number, 1 special char
    public boolean checkPasswordComplexity() {
        if (password == null) return false;
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
        return Pattern.matches(regex, password);
    }

    // Cell phone must include +27 and 9 digits
    public boolean checkCellPhoneNumber() {
        if (cellPhone == null) return false;
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellPhone);
    }

    // First and last name must start with uppercase letters
    public boolean checkNameFormat(String name) {
        if (name == null) return false;
        return name.matches("^[A-Z][a-zA-Z]*$");
    }

    // Register user with combined error messages
    public String registerUser() {
        StringBuilder errors = new StringBuilder();

        if (!checkUserName()) {
            errors.append("Username is not correctly formatted, ensure that your username contains an underscore and is no more than five characters in length.\n");
        }
        if (!checkPasswordComplexity()) {
            errors.append("Password is not correctly formatted, ensure that your password contains at least eight characters, a capital letter, a number, and a special character.\n");
        }
        if (!checkCellPhoneNumber()) {
            errors.append("Cell number is not correctly formatted or does not contain an international code, please correct the number and try again.\n");
        }
        if (!checkNameFormat(firstName) || !checkNameFormat(lastName)) {
            errors.append("First or last name is incorrectly formatted. Must start with a capital letter and contain only letters.\n");
        }

        if (errors.length() > 0) {
            return errors.toString().trim();
        }

        return "User registered successfully!";
    }

    // Login user
    public boolean loginUser(String enteredUsername, String enteredPassword) {
        if (enteredUsername == null || enteredPassword == null) return false;
        return enteredUsername.equals(this.username) && enteredPassword.equals(this.password);
    }

    // Return login status message
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + " ," + lastName + " it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }

    // Getters
    public String username() { return username; }
    public String password() { return password; }
    public String cellPhone() { return cellPhone; }
    public String firstName() { return firstName; }
    public String lastName() { return lastName; }
}