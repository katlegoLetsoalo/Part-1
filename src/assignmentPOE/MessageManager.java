/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPOE;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class MessageManager {

    private List<Message> sentMessages = new ArrayList<>();
    private List<Message> disregardedMessages = new ArrayList<>();
    private List<Message> storedMessages = new ArrayList<>();
    private List<String> messageHashes = new ArrayList<>();
    private List<String> messageIDs = new ArrayList<>();

    // === Add Methods ===
    public void addSentMessage(Message msg) {
        sentMessages.add(msg);
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    public void addDisregardedMessage(Message msg) {
        disregardedMessages.add(msg);
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    public void addStoredMessage(Message msg) {
        storedMessages.add(msg);
        messageHashes.add(msg.getMessageHash());
        messageIDs.add(msg.getMessageID());
    }

    // === Save Stored Messages to JSON ===
    public void saveStoredMessagesToJSON() {
        JSONArray arr = new JSONArray();
        for (Message msg : storedMessages) {
            JSONObject obj = new JSONObject();
            obj.put("MessageID", msg.getMessageID());
            obj.put("MessageHash", msg.getMessageHash());
            obj.put("Recipient", msg.getRecipient());
            obj.put("Message", msg.getMessage());
            arr.add(obj);
        }

        try (FileWriter writer = new FileWriter("messages.json")) {
            writer.write(arr.toJSONString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }

    // === Load Stored Messages from JSON ===
    public void loadStoredMessagesFromJSON() {
        storedMessages.clear();
        messageHashes.clear();
        messageIDs.clear();

        JSONParser parser = new JSONParser();
        try (FileReader reader = new FileReader("messages.json")) {
            JSONArray arr = (JSONArray) parser.parse(reader);

            for (Object o : arr) {
                JSONObject msgObj = (JSONObject) o;
                String recipient = (String) msgObj.get("Recipient");
                String messageText = (String) msgObj.get("Message");
                String messageID = (String) msgObj.get("MessageID");
                String messageHash = (String) msgObj.get("MessageHash");

                Message msg = new Message(recipient, messageText, messageID, messageHash);
                storedMessages.add(msg);
                messageHashes.add(messageHash);
                messageIDs.add(messageID);
            }
        } catch (IOException | org.json.simple.parser.ParseException e) {
            System.out.println("No stored messages found or error reading file: " + e.getMessage());
        }
    }

    // === Reporting / Searching ===
    public String displaySentMessages() {
        if (sentMessages.isEmpty()) return "No sent messages.";
        StringBuilder sb = new StringBuilder("=== Sent Messages ===\n");
        for (Message msg : sentMessages) {
            sb.append("Recipient: ").append(msg.getRecipient())
              .append("\nMessage: ").append(msg.getMessage())
              .append("\n\n");
        }
        return sb.toString();
    }

    public String displayLongestSentMessage() {
        if (sentMessages.isEmpty()) return "No sent messages.";
        Message longest = sentMessages.stream()
                .max(Comparator.comparingInt(m -> m.getMessage().length()))
                .orElse(null);
        return "Longest Message:\n" + (longest != null ? longest.getMessage() : "None");
    }

    public String searchMessageByID(String id) {
        for (Message msg : sentMessages) {
            if (msg.getMessageID().equals(id)) {
                return "Recipient: " + msg.getRecipient() + "\nMessage: " + msg.getMessage();
            }
        }
        return "No message found with ID: " + id;
    }

    public List<String> searchMessagesByRecipient(String recipient) {
        List<String> results = new ArrayList<>();
        for (Message msg : sentMessages) {
            if (msg.getRecipient().equals(recipient)) results.add(msg.getMessage());
        }
        for (Message msg : storedMessages) {
            if (msg.getRecipient().equals(recipient)) results.add(msg.getMessage());
        }
        return results;
    }

    public String deleteMessageByHash(String hash) {
        Iterator<Message> iterator = sentMessages.iterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.getMessageHash().equals(hash)) {
                iterator.remove();
                return "Message \"" + msg.getMessage() + "\" successfully deleted.";
            }
        }
        return "Message not found.";
    }

    public String displayReport() {
        if (sentMessages.isEmpty()) return "No messages to report.";
        StringBuilder report = new StringBuilder("=== SENT MESSAGES REPORT ===\n");
        for (Message msg : sentMessages) {
            report.append("Message Hash: ").append(msg.getMessageHash())
                  .append("\nRecipient: ").append(msg.getRecipient())
                  .append("\nMessage: ").append(msg.getMessage())
                  .append("\n------------------------\n");
        }
        return report.toString();
    }

    // === Getters ===
    public List<Message> getSentMessages() { return sentMessages; }
    public List<Message> getStoredMessages() { return storedMessages; }
    public List<Message> getDisregardedMessages() { return disregardedMessages; }
    public List<String> getMessageHashes() { return messageHashes; }
    public List<String> getMessageIDs() { return messageIDs; }
}