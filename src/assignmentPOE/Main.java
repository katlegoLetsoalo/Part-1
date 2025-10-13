/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignmentPOE;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to QuickChat\n");

        // === User Registration ===
        String firstName, lastName, username, password, cellPhone;
        Login user;

        while (true) {
            System.out.println("=== Register a new user ===");

            System.out.print("Enter first name: ");
            firstName = scanner.nextLine().trim();

            System.out.print("Enter last name: ");
            lastName = scanner.nextLine().trim();

            System.out.print("Enter username (must contain '_' and <=5 chars): ");
            username = scanner.nextLine().trim();

            System.out.print("Enter password (8+ chars, 1 uppercase, 1 number, 1 special): ");
            password = scanner.nextLine();

            System.out.print("Enter cell phone (+27XXXXXXXXX): ");
            cellPhone = scanner.nextLine().trim();

            user = new Login(username, password, cellPhone, firstName, lastName);
            String regMessage = user.registerUser();
            System.out.println("\nRegistration result:");
            System.out.println(regMessage);

            if (regMessage.contains("successfully")) break;
            System.out.println("Please fix the above errors and try again.\n");
        }

        // === Login Loop ===
        boolean loggedIn = false;
        while (!loggedIn) {
            System.out.println("\n=== Login ===");
            System.out.print("Enter username: ");
            String enteredUser = scanner.nextLine().trim();
            System.out.print("Enter password: ");
            String enteredPass = scanner.nextLine();

            loggedIn = user.loginUser(enteredUser, enteredPass);
            System.out.println("\n" + user.returnLoginStatus(loggedIn));
            if (!loggedIn) System.out.println("Please try again.\n");
        }

        // === Chat Menu ===
        int choice = 0;
        while (choice != 3) {
            System.out.println("\nMenu:");
            System.out.println("1) Send messages");
            System.out.println("2) Show recently sent messages (Coming Soon)");
            System.out.println("3) Quit");
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Try again.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Ask how many messages to send
                    int numMessages = 0;
                    while (true) {
                        System.out.print("How many messages would you like to send? ");
                        String msgCountStr = scanner.nextLine();
                        try {
                            numMessages = Integer.parseInt(msgCountStr);
                            if (numMessages > 0) break;
                        } catch (Exception e) {}
                        System.out.println("Enter a valid positive number.");
                    }

                    for (int i = 0; i < numMessages; i++) {
                        String recipient;
                        while (true) {
                            System.out.print("Enter recipient (+27XXXXXXXXX): ");
                            recipient = scanner.nextLine().trim();
                            Message tempMsg = new Message(recipient, "");
                            if (tempMsg.checkRecipientCell()) break;
                            System.out.println("Invalid recipient format. Try again.");
                        }

                        String msgText;
                        while (true) {
                            System.out.print("Enter message (<=250 chars): ");
                            msgText = scanner.nextLine();
                            if (msgText.length() <= 250) break;
                            System.out.println("Message too long. Please enter less than 250 characters.");
                        }

                        Message messageObj = new Message(recipient, msgText);
                        messageObj.sendMessageOption();
                    }
                    break;

                case 2:
                    System.out.println("\nSent Messages:");
                    System.out.println(Message.printMessages());
                    System.out.println("Total Sent: " + Message.returnTotalMessages());
                    break;

                case 3:
                    System.out.println("Exiting QuickChat. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }
}