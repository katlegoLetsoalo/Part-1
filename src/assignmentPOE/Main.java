/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignmentPOE;

import javax.swing.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MessageManager manager = new MessageManager();

        // Load stored messages from JSON at startup
        manager.loadStoredMessagesFromJSON();

        // === User Registration ===
        Login user = registerUserGUI();
        if (user == null) return; // User canceled registration

        // === Login ===
        boolean loggedIn = loginGUI(user);
        if (!loggedIn) return; // User canceled login

        // === Main Menu ===
        int choice = 0;
        while (choice != 6) {
            String input = JOptionPane.showInputDialog(
                    "QuickChat Menu:\n" +
                            "1) Send Message\n" +
                            "2) Display Sent Messages\n" +
                            "3) Display Report\n" +
                            "4) Search by Message ID\n" +
                            "5) Search by Recipient\n" +
                            "6) Quit\n\n" +
                            "Enter your choice:"
            );
            if (input == null) break; // user pressed cancel
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number 1-6.");
                continue;
            }

            switch (choice) {
                case 1:
                    sendMessageGUI(manager);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, manager.displaySentMessages());
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, manager.displayReport());
                    break;
                case 4: // Search by Message ID
                    String id = JOptionPane.showInputDialog("Enter Message ID:");
                    if (id != null) {
                        JOptionPane.showMessageDialog(null, manager.searchMessageByID(id));
                    }
                    break;
                case 5: // Search by Recipient
                    String recipient = JOptionPane.showInputDialog("Enter Recipient Number (+27XXXXXXXXX):");
                    if (recipient != null) {
                        List<String> results = manager.searchMessagesByRecipient(recipient);
                        if (results.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "No messages found for this recipient.");
                        } else {
                            JOptionPane.showMessageDialog(null, String.join("\n", results));
                        }
                    }
                    break;
                case 6:
                    manager.saveStoredMessagesToJSON();
                    JOptionPane.showMessageDialog(null, "Exiting QuickChat. Goodbye!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice. Enter 1-6.");
            }
        }
    }

    // === Registration GUI ===
    private static Login registerUserGUI() {
        while (true) {
            String firstName = JOptionPane.showInputDialog("Enter First Name:");
            if (firstName == null) return null;

            String lastName = JOptionPane.showInputDialog("Enter Last Name:");
            if (lastName == null) return null;

            String username = JOptionPane.showInputDialog("Enter Username (must contain '_' and <=5 chars):");
            if (username == null) return null;

            String password = JOptionPane.showInputDialog("Enter Password (8+ chars, 1 uppercase, 1 number, 1 special char):");
            if (password == null) return null;

            String cellPhone = JOptionPane.showInputDialog("Enter Cell Phone (+27XXXXXXXXX):");
            if (cellPhone == null) return null;

            Login user = new Login(username, password, cellPhone, firstName, lastName);
            String regMessage = user.registerUser();
            JOptionPane.showMessageDialog(null, regMessage);

            if (regMessage.contains("successfully")) return user;
        }
    }

    // === Login GUI ===
    private static boolean loginGUI(Login user) {
        while (true) {
            String enteredUser = JOptionPane.showInputDialog("Enter Username:");
            if (enteredUser == null) return false;

            String enteredPass = JOptionPane.showInputDialog("Enter Password:");
            if (enteredPass == null) return false;

            boolean loggedIn = user.loginUser(enteredUser, enteredPass);
            JOptionPane.showMessageDialog(null, user.returnLoginStatus(loggedIn));

            if (loggedIn) return true;
        }
    }

    // === Send Message GUI ===
    private static void sendMessageGUI(MessageManager manager) {
        String recipient = JOptionPane.showInputDialog("Enter recipient (+27XXXXXXXXX):");
        if (recipient == null) return;

        Message tempMsg = new Message(recipient, "");
        while (!tempMsg.checkRecipientCell()) {
            recipient = JOptionPane.showInputDialog("Invalid recipient. Enter recipient (+27XXXXXXXXX):");
            if (recipient == null) return;
            tempMsg = new Message(recipient, "");
        }

        String messageText = JOptionPane.showInputDialog("Enter message (<=250 chars):");
        if (messageText == null) return;
        while (messageText.length() > 250) {
            messageText = JOptionPane.showInputDialog("Message too long. Enter <=250 chars:");
            if (messageText == null) return;
        }

        Message msg = new Message(recipient, messageText);
        String action = msg.sendMessageOption();

        // Add message to manager arrays
        switch (action) {
            case "Message sent." -> manager.addSentMessage(msg);
            case "Message stored for later." -> manager.addStoredMessage(msg);
            case "Message discarded." -> manager.addDisregardedMessage(msg);
        }
    }
}