/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPOE;

import java.util.regex.Pattern;

public class Login {
    private String username;
    private String password;
    private String cellPhone;
    private String firstName;
    private String lastName;

    public Login(String username, String password, String cellPhone, String firstName, String lastName) {
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean checkUserName() {
        return username != null && username.contains("_") && username.length() <= 5;
    }

    public boolean checkPasswordComplexity() {
        if (password == null) return false;
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
        return Pattern.matches(regex, password);
    }

    public boolean checkCellPhoneNumber() {
        if (cellPhone == null) return false;
        String regex = "^\\+27\\d{9}$";
        return Pattern.matches(regex, cellPhone);
    }

    public boolean checkNameFormat(String name) {
        return name != null && name.matches("^[A-Z][a-zA-Z]*$");
    }

    public String registerUser() {
        StringBuilder errors = new StringBuilder();

        if (!checkUserName())
            errors.append("Username must contain '_' and be 5 characters or fewer.\n");
        if (!checkPasswordComplexity())
            errors.append("Password must have 8+ chars, 1 uppercase, 1 number, and 1 special character.\n");
        if (!checkCellPhoneNumber())
            errors.append("Cell number must start with +27 and contain 9 digits.\n");
        if (!checkNameFormat(firstName) || !checkNameFormat(lastName))
            errors.append("First and last names must start with uppercase and contain only letters.\n");

        if (errors.length() > 0)
            return errors.toString().trim();

        return "User registered successfully!";
    }

    public boolean loginUser(String enteredUsername, String enteredPassword) {
        return enteredUsername.equals(username) && enteredPassword.equals(password);
    }

    public String returnLoginStatus(boolean success) {
        if (success)
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        else
            return "Username or password incorrect, please try again.";
    }
}