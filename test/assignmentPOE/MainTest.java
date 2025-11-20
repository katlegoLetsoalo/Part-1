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

    // ===== MESSAGE MANAGER TESTS =====
    @Test
    public void testAddSentAndStoredMessages() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27834557896", "Did you get the cake?");
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m3 = new Message("+27834484567", "Yohoooo, I am at your gate.");
        Message m4 = new Message("0838884567", "It is dinner time!");
        Message m5 = new Message("+27838884567", "Ok, I am leaving without you.");

        manager.addSentMessage(m1);
        manager.addStoredMessage(m2);
        manager.addDisregardedMessage(m3);
        manager.addSentMessage(m4);
        manager.addStoredMessage(m5);

        assertEquals(2, manager.getSentMessages().size());
        assertEquals(2, manager.getStoredMessages().size());
        assertEquals(1, manager.getDisregardedMessages().size());
    }

    @Test
    public void testDisplayLongestMessage() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27834557896", "Did you get the cake?");
        Message m2 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        manager.addSentMessage(m1);
        manager.addSentMessage(m2);
        String longest = manager.displayLongestSentMessage();
        assertEquals("Longest Message:\nWhere are you? You are late! I have asked you to be on time.", longest);
    }

    @Test
    public void testSearchMessageByID() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("0838884567", "It is dinner time!");
        manager.addSentMessage(m1);
        String result = manager.searchMessageByID(m1.getMessageID());
        assertTrue(result.contains("It is dinner time!"));
    }

    @Test
    public void testSearchMessagesByRecipient() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        Message m2 = new Message("+27838884567", "Ok, I am leaving without you.");
        manager.addStoredMessage(m1);
        manager.addStoredMessage(m2);
        List<String> results = manager.searchMessagesByRecipient("+27838884567");
        assertEquals(2, results.size());
        assertTrue(results.contains(m1.getMessage()));
        assertTrue(results.contains(m2.getMessage()));
    }

    @Test
    public void testDeleteMessageByHash() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27838884567", "Where are you? You are late! I have asked you to be on time.");
        manager.addSentMessage(m1);
        String msgHash = m1.getMessageHash();
        String response = manager.deleteMessageByHash(msgHash);
        assertTrue(response.contains("successfully deleted"));
        assertEquals(0, manager.getSentMessages().size());
    }

    @Test
    public void testDisplayReport() {
        MessageManager manager = new MessageManager();
        Message m1 = new Message("+27834557896", "Did you get the cake?");
        Message m2 = new Message("0838884567", "It is dinner time!");
        manager.addSentMessage(m1);
        manager.addSentMessage(m2);
        String report = manager.displayReport();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
    }
}
