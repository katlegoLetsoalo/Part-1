/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPartOne;
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
    
    //constructor
    public Login(String username, String password, String cellPhone, String firstName, String lastName){
        this.username = username;
        this.password = password;
        this.cellPhone = cellPhone;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    //1) username must contain '_' and be <=5 chars
    public boolean checkUserName(){
        if (username == null) return false;
        return username.contains("_") && username.length() <= 5;
    }
    
    //2) password complexity at least 8 chars, at least one uppercase, one digit, one special char
    public boolean checkPasswordComplexity(){
        if (password == null) return false;
        String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z0-9]).{8,}$";
        return Pattern.matches(regex, password);
    }
    
    //3)cell phone must include international code and correct length. For South Africa we accept +27 followed by 9 digits: +27XXXXXXXXX
    public boolean checkCellPhoneNumber(){
        if (cellPhone == null) return false;
        String regex = "^\\+27\\d{9}$"; //example +27677718391
        return Pattern.matches(regex, cellPhone);
    }
    
    //registerUser returns either error message or single success message
    public String registerUser() {
    StringBuilder errors = new StringBuilder();

    // check username
    if (!checkUserName()) {
        errors.append("Username is not correctly formatted, ensure that your username contains an underscore and is no more than five characters in length.\n");
    }

    // check password
    if (!checkPasswordComplexity()) {
        errors.append("Password is not correctly formatted, ensure that your password contains at least eight characters, a capital letter, a number, and a special character.\n");
    }

    // check cellphone
    if (!checkCellPhoneNumber()) {
        errors.append("Cell number is not correctly formatted or does not contain an international code, please correct the number and try again.\n");
    }

    // if errors exist, return them
    if (errors.length() > 0) {
        return errors.toString().trim(); // remove last newline
    }

    // otherwise success
    return "User registered successfully!";
    }
    
    //loginUser verifies input credentials match stored ones
    public boolean loginUser(String enterUsername, String enterPassword){
        if (enterUsername == null || enterPassword == null) return false;
        return enterUsername.equals(this.username) && enterPassword.equals(this.password);
    }
    
    //returnLoginStatus returns the success/failure message exactly as required
    public String returnloginStatus(boolean loginSuccess){
        if (loginSuccess){
            return "Welcome " + firstName + " " + lastName + " it is nice to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    //getters
    public String username(){return username;}
    public String password(){return password;}
    public String cellPhone(){return cellPhone;}
    public String firstName(){return firstName;}
    public String lastName(){return lastName;}

}
