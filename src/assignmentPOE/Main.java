/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package assignmentPOE;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!", "QuickChat", JOptionPane.INFORMATION_MESSAGE);

        // === User Registration ===
        String firstName, lastName, username, password, cellPhone;
        Login user;

        while (true) {
            firstName = JOptionPane.showInputDialog(null, "Enter First Name:", "Registration", JOptionPane.QUESTION_MESSAGE);
            lastName = JOptionPane.showInputDialog(null, "Enter Last Name:", "Registration", JOptionPane.QUESTION_MESSAGE);
            username = JOptionPane.showInputDialog(null, "Enter Username (must contain '_' and <=5 chars):", "Registration", JOptionPane.QUESTION_MESSAGE);
            password = JOptionPane.showInputDialog(null, "Enter Password (8+ chars, 1 uppercase, 1 number, 1 special):", "Registration", JOptionPane.QUESTION_MESSAGE);
            cellPhone = JOptionPane.showInputDialog(null, "Enter Cell Phone (+27XXXXXXXXX):", "Registration", JOptionPane.QUESTION_MESSAGE);

            user = new Login(username, password, cellPhone, firstName, lastName);
            String regMessage = user.registerUser();

            JOptionPane.showMessageDialog(null, regMessage, "Registration Result", JOptionPane.INFORMATION_MESSAGE);

            if (regMessage.contains("successfully")) break;
            JOptionPane.showMessageDialog(null, "Please fix the above errors and try again.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // === LOGIN ===
        boolean loggedIn = false;
        while (!loggedIn) {
            String enteredUser = JOptionPane.showInputDialog(null, "Enter Username:", "Login", JOptionPane.QUESTION_MESSAGE);
            String enteredPass = JOptionPane.showInputDialog(null, "Enter Password:", "Login", JOptionPane.QUESTION_MESSAGE);

            loggedIn = user.loginUser(enteredUser, enteredPass);
            JOptionPane.showMessageDialog(null, user.returnLoginStatus(loggedIn));

            if (!loggedIn)
                JOptionPane.showMessageDialog(null, "Please try again.", "Login Failed", JOptionPane.WARNING_MESSAGE);
        }

        JOptionPane.showMessageDialog(null, "Welcome to QuickChat!", "QuickChat", JOptionPane.INFORMATION_MESSAGE);

        // === CHAT MENU ===
        int choice = 0;
        while (choice != 3) {
            String input = JOptionPane.showInputDialog(null,
                    "Menu:\n1) Send Messages\n2) Show Recently Sent Messages\n3) Quit",
                    "QuickChat Menu", JOptionPane.QUESTION_MESSAGE);

            if (input == null) break; // user clicked cancel

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Enter 1–3.", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            switch (choice) {
                case 1 -> {
                    String numStr = JOptionPane.showInputDialog(null, "How many messages would you like to send?");
                    if (numStr == null) continue;
                    int numMessages;
                    try {
                        numMessages = Integer.parseInt(numStr);
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Invalid number entered.");
                        continue;
                    }

                    for (int i = 0; i < numMessages; i++) {
                        String recipient = JOptionPane.showInputDialog(null, "Enter Recipient (+27XXXXXXXXX):", "Recipient", JOptionPane.QUESTION_MESSAGE);
                        String messageText = JOptionPane.showInputDialog(null, "Enter Message (<=250 chars):", "Message", JOptionPane.QUESTION_MESSAGE);

                        if (recipient == null || messageText == null) continue;

                        Message msg = new Message(recipient, messageText);
                        JOptionPane.showMessageDialog(null, msg.getRecipientValidationMessage());
                        JOptionPane.showMessageDialog(null, msg.getMessageLengthStatus());
                        msg.sendMessageOption();
                    }

                    JOptionPane.showMessageDialog(null, "Total Messages Sent: " + Message.returnTotalMessages(), "Total", JOptionPane.INFORMATION_MESSAGE);
                }

                case 2 -> JOptionPane.showMessageDialog(null, Message.printMessages() + "\nTotal Sent: " + Message.returnTotalMessages(), "Sent Messages", JOptionPane.INFORMATION_MESSAGE);

                case 3 -> JOptionPane.showMessageDialog(null, "Exiting QuickChat. Goodbye!", "Exit", JOptionPane.INFORMATION_MESSAGE);

                default -> JOptionPane.showMessageDialog(null, "Invalid choice. Try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        System.exit(0);
    }
}
