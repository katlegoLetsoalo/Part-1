/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPOE;
 
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Message {
    private String messageID;
    private String recipient;
    private String message;
    private String messageHash;
    private static int totalMessages = 0;
    private static List<Message> sentMessages = new ArrayList<>();

    // Constructor
    public Message(String recipient, String message) {
        this.messageID = generateMessageID();
        this.recipient = recipient;
        this.message = message;
        this.messageHash = createMessageHash();
    }

    // Generate random 10-digit ID
    private String generateMessageID() {
        Random rand = new Random();
        long id = 1000000000L + (long) (rand.nextDouble() * 8999999999L);
        return String.valueOf(id);
    }

    //  Ensure message ID not more than 10 chars
    public boolean checkMessageID() {
        return messageID.length() <= 10;
    }

    //  Ensure recipient cell number starts with +27 and is exactly 12 characters long (+27XXXXXXXXX)
    public boolean checkRecipientCell() {
        if (recipient == null) return false;
        return recipient.matches("^\\+27\\d{9}$");
    }

    // Returns a readable message for validation results
    public String getRecipientValidationMessage() {
        if (checkRecipientCell()) {
            return "Cell phone number successfully captured.";
        } else {
            return "Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.";
        }
    }

    //  Ensure message ≤ 250 chars
    public boolean checkMessageLength() {
        return message.length() <= 250;
    }

    public String getMessageLengthStatus() {
        if (checkMessageLength()) {
            return "Message ready to send.";
        } else {
            int over = message.length() - 250;
            return "Message exceeds 250 characters by " + over + ", please reduce size.";
        }
    }

    //  Create Message Hash (first two digits of ID : total count : first and last words)
    public String createMessageHash() {
        String[] words = message.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : "";
        String idPrefix = messageID.substring(0, 2);
        return (idPrefix + ":" + totalMessages + ":" + firstWord + lastWord).toUpperCase();
    }

    //  Send / Store / Discard Message
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
                storeMessage();
                return "Message stored for later.";

            case 2: // Discard
                return "Message discarded.";

            default:
                return "No action selected.";
        }
    }

    //  Show message details (using JOptionPane)
    public void showMessageDetails() {
        String info = "Message ID: " + messageID +
                "\nMessage Hash: " + messageHash +
                "\nRecipient: " + recipient +
                "\nMessage: " + message;
        JOptionPane.showMessageDialog(null, info, "Message Details", JOptionPane.INFORMATION_MESSAGE);
    }

    //  Store message in JSON file
    public void storeMessage() {
        JSONObject obj = new JSONObject();
        obj.put("MessageID", messageID);
        obj.put("MessageHash", messageHash);
        obj.put("Recipient", recipient);
        obj.put("Message", message);

        try (FileWriter file = new FileWriter("messages.json", true)) {
            file.write(obj.toJSONString() + "\n");
            file.flush();
        } catch (IOException e) {
            System.out.println("Error storing message: " + e.getMessage());
        }
    }

    // Return list of all sent messages
    public static String printMessages() {
        StringBuilder sb = new StringBuilder();
        for (Message msg : sentMessages) {
            sb.append("ID: ").append(msg.messageID)
                    .append(" | Hash: ").append(msg.messageHash)
                    .append(" | Recipient: ").append(msg.recipient)
                    .append(" | Message: ").append(msg.message)
                    .append("\n");
        }
        return sb.isEmpty() ? "No messages sent yet." : sb.toString();
    }

    //  Return total number of sent messages
    public static int returnTotalMessages() {
        return totalMessages;
    }
}