/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignmentPartOne;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Register a new user ===");
        System.out.println("Enter first name: ");
        String firstName = scanner.nextLine().trim();
        
        System.out.println("Enter last name: ");
        String lastName = scanner.nextLine().trim();
        
        System.out.println("Enter username (must contain '_' and be <= 5 chars): ");
        String username = scanner.nextLine().trim();
        
        System.out.println("Enter password (8+ chars, 1 uppercase, 1 special char, 1 digit): ");
        String password = scanner.nextLine().trim();
        
        System.out.println("Enter cell phone (include international code, e.g +2783...): ");
        String cellPhone = scanner.nextLine().trim();
        
        //login instance
        Login user = new Login(username, password, cellPhone, firstName, lastName);
        
        //registration result
        String regMessage = user.registerUser();
        System.out.println("\nRegistration result");
        System.out.println(regMessage);
        
        //if registration succeeded, ask for login
        if (regMessage.equals("User registered successfully!")){
            System.out.println("\n=== Login ===");
            System.out.println("Enter Username: ");
            String enteredUser = scanner.nextLine();
            
            System.out.println("Enter Password: ");
            String enteredPass = scanner.nextLine();
            
            boolean loginOk = user.loginUser(enteredUser, enteredPass);
            System.out.println("\n" + user.returnloginStatus(loginOk));
        } else {
            System.out.println("\nFix the issue above and run the program again.");
        }
        
        scanner.close();
    }
}