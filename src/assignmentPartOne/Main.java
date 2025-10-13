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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to QuickChat\n");

        // --- Registration ---
        System.out.println("=== Register a new user ===");
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Enter username (must contain '_' and be <= 5 chars): ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password (8+ chars, 1 uppercase, 1 number, 1 special): ");
        String password = scanner.nextLine();

        System.out.print("Enter cell phone (e.g. +27831234567): ");
        String cellPhone = scanner.nextLine().trim();

        Login user = new Login(username, password, cellPhone, firstName, lastName);

        // Registration result
        String regMessage = user.registerUser();
        System.out.println("\nRegistration result:");
        System.out.println(regMessage);

        // Proceed only if registration successful
        boolean loggedIn = false;
        if (regMessage.contains("successfully")) {

            // --- Login loop ---
            while (!loggedIn) {
                System.out.println("\n=== Login ===");
                System.out.print("Enter username: ");
                String enteredUser = scanner.nextLine().trim();

                System.out.print("Enter password: ");
                String enteredPass = scanner.nextLine();

                loggedIn = user.loginUser(enteredUser, enteredPass);
                System.out.println("\n" + user.returnLoginStatus(loggedIn));

                if (!loggedIn) {
                    System.out.println("Please try again.");
                }
            }

            // --- Messages setup ---
            System.out.print("\nHow many messages would you like to enter? ");
            int numMessages = scanner.nextInt();
            scanner.nextLine();
            String[] messages = new String[numMessages];
            int messageCount = 0;

            // --- Menu loop ---
            int choice = 0;
            while (choice != 3) {
                System.out.println("\nMenu:");
                System.out.println("1) Send messages");
                System.out.println("2) Show recently sent messages");
                System.out.println("3) Quit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        if (messageCount < numMessages) {
                            System.out.print("Enter message: ");
                            messages[messageCount] = scanner.nextLine();
                            System.out.println("Message sent!");
                            messageCount++;
                        } else {
                            System.out.println("You have reached the maximum number of messages.");
                        }
                        break;

                    case 2:
                        System.out.println("Coming soon");
                        break;

                    case 3:
                        System.out.println("Exiting QuickChat. Goodbye!");
                        break;

                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            }

        } else {
            System.out.println("\nPlease fix the errors above and restart the program.");
        }

        scanner.close();
    }
}