/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignmentPOE;

/**
 *
 * @author RC_Student_Lab
 */
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

public class MainTest {

    // ===== LOGIN TESTS =====
    @Test
    public void testLoginSuccess() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
        String expected = "Welcome Kyle Smith, it is great to see you again.";
        assertEquals(expected, user.returnLoginStatus(true));
    }

    @Test
    public void testLoginFail() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(user.loginUser("wrong", "wrongPass"));
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, user.returnLoginStatus(false));
    }

    @Test
    public void testRegistrationValid() {
        Login user = new Login("ky_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = user.registerUser();
        assertTrue(result.contains("successfully"));
    }

    @Test
    public void testRegistrationInvalid() {
        Login user = new Login("baduser", "pass", "12345", "kyle", "123");
        String result = user.registerUser();
        assertTrue(result.contains("Username must contain"));
        assertTrue(result.contains("Password must have"));
        assertTrue(result.contains("Cell number must"));
        assertTrue(result.contains("First and last names must"));
    }

    // ===== MESSAGE VALIDATION TESTS =====
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertTrue(msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "x".repeat(260);
        Message msg = new Message("+27718693002", longMsg);
        assertFalse(msg.checkMessageLength());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Hi");
        assertTrue(msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message("08375975889", "Hi");
        assertFalse(msg.checkRecipientCell());
    }

    @Test
    public void testMessageIDLength() {
        Message msg = new Message("+27718693002", "Hi");
        assertTrue(msg.checkMessageID());
    }

    @Test
    public void testMessageHashFormat() {
        Message msg = new Message("+27718693002", "Hi Keegan, did you receive the payment?");
        String hash = msg.createMessageHash();
        assertTrue(hash.contains(":"));
    }

    // ===== MESSAGE MANAGER TESTS USING PART 3 TEST DATA =====
    @Test
    public void testSentMessagesArrayPopulation() {
        MessageManager manager = new MessageManager();

        // Test Data Messages
        Message m1 = new Message("+27834557896", "Did you get the cake?"); // Sent
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time."); // Stored
        Message m3 = new Message("+27834484567", "Yohoooo, I am at your gate."); // Disregard
        Message m4 = new Message("0838884567", "It is dinner time!"); // Sent
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you."); // Stored

        manager.addSentMessage(m1);
        manager.addStoredMessage(m2);
        manager.addDisregardedMessage(m3);
        manager.addSentMessage(m4);
        manager.addStoredMessage(m5);

        List<Message> sent = manager.getSentMessages();
        assertEquals(2, sent.size());
        assertEquals("Did you get the cake?", sent.get(0).getMessage());
        assertEquals("It is dinner time!", sent.get(1).getMessage());
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27834557896", "Did you get the cake?");
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m4 = new Message("0838884567", "It is dinner time!");

        manager.addSentMessage(m1);
        manager.addSentMessage(m2);
        manager.addSentMessage(m4);

        String longest = manager.displayLongestSentMessage();
        assertEquals("Longest Message:\nWhere are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchMessageByID() {
        MessageManager manager = new MessageManager();
        Message m4 = new Message("0838884567", "It is dinner time!");
        manager.addSentMessage(m4);

        String result = manager.searchMessageByID(m4.getMessageID());
        assertTrue(result.contains("It is dinner time!"));
        assertTrue(result.contains("0838884567"));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        MessageManager manager = new MessageManager();
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time."); // Stored
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you."); // Stored

        manager.addStoredMessage(m2);
        manager.addStoredMessage(m5);

        List<String> results = manager.searchMessagesByRecipient("+27838884567");
        assertEquals(2, results.size());
        assertTrue(results.contains("Where are you? You are late! I have asked you to be on time."));
        assertTrue(results.contains("Ok, I am leaving without you."));
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageManager manager = new MessageManager();
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        manager.addSentMessage(m2);

        String msgHash = m2.getMessageHash();
        String response = manager.deleteMessageByHash(msgHash);
        assertTrue(response.contains("successfully deleted"));
        assertEquals(0, manager.getSentMessages().size());
    }

    @Test
    public void testDisplayReport() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27834557896", "Did you get the cake?");
        Message m4 = new Message("0838884567", "It is dinner time!");

        manager.addSentMessage(m1);
        manager.addSentMessage(m4);

        String report = manager.displayReport();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
        assertTrue(report.contains(m1.getMessageHash()));
        assertTrue(report.contains(m4.getMessageHash()));
    }
}
