/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPOE;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private static int totalMessages = 0;
    private static List<Message> sentMessages = new ArrayList<>();

    // === Constructors ===
    public Message(String recipient, String message) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // Constructor for loading from JSON (keeps IDs and hashes consistent)
    public Message(String recipient, String message, String messageID, String messageHash) {
        this.recipient = recipient;
        this.message = message;
        this.messageID = messageID;
        this.messageHash = messageHash;
    }

    // === Generate random 10-digit ID ===
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long) (rand.nextDouble() * 8999999999L);
        return String.valueOf(id);
    }

    // === Validation Methods ===
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    public boolean checkRecipientCell() {
        return recipient != null && recipient.matches("^\\+27\\d{9}$");
    }

    public boolean checkMessageLength() {
        return message.length() <= 250;
    }

    // === Message Hash ===
    public String createMessageHash() {
        String[] words = message.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : "";
        String idPrefix = messageID.substring(0, 2);
        return (idPrefix + ":" + totalMessages + ":" + firstWord + lastWord).toUpperCase();
    }

    // === Send / Store / Discard Message ===
    public String sendMessageOption() {
        String[] options = {"Send", "Store", "Discard"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "Choose an option for this message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]
        );

        switch (choice) {
            case 0: // Send
                totalMessages++;
                sentMessages.add(this);
                showMessageDetails();
                return "Message sent.";
            case 1: // Store
                return "Message stored for later.";
            case 2: // Discard
                return "Message discarded.";
            default:
                return "No action selected.";
        }
    }

    // === Show message details ===
    public void showMessageDetails() {
        String info = "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + message;
        JOptionPane.showMessageDialog(null, info, "Message Details", JOptionPane.INFORMATION_MESSAGE);
    }

    // === Getters ===
    public String getMessageID() { return messageID; }
    public String getRecipient() { return recipient; }
    public String getMessage() { return message; }
    public String getMessageHash() { return messageHash; }
    public static int returnTotalMessages() { return totalMessages; }
    public static List<Message> getSentMessagesList() { return sentMessages; }
}