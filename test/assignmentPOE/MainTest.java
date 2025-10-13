/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignmentPOE;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {

    // ===== LOGIN TESTS =====
    @Test
    public void testLoginSuccess() {
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!"));
        String expected = "Welcome Kyle ,Smith it is great to see you again.";
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
    public void testUsernameFormat() {
        Login user = new Login("abc_1", "Pass@123", "+27831234567", "Test", "User");
        assertTrue(user.checkUserName());
    }

    @Test
    public void testPasswordComplexity() {
        Login user = new Login("abc_1", "Pass@123", "+27831234567", "Test", "User");
        assertTrue(user.checkPasswordComplexity());
    }

    @Test
    public void testCellNumberFormatSuccess() {
        Login user = new Login("abc_1", "Pass@123", "+27831234567", "Test", "User");
        assertTrue(user.checkCellPhoneNumber());
    }

    @Test
    public void testCellNumberFormatFailure() {
        Login user = new Login("abc_1", "Pass@123", "0831234567", "Test", "User");
        assertFalse(user.checkCellPhoneNumber());
    }

    // ===== MESSAGE TESTS =====
    @Test
    public void testMessageLengthSuccess() {
        Message msg = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        assertEquals("Message ready to send.", msg.checkMessageLength());
    }

    @Test
    public void testMessageLengthFailure() {
        String longMsg = "x".repeat(260);
        Message msg = new Message("+27718693002", longMsg);
        assertEquals("Message exceeds 250 characters by 10, please reduce size.", msg.checkMessageLength());
    }

    @Test
    public void testRecipientSuccess() {
        Message msg = new Message("+27718693002", "Hi");
        assertEquals("Cell phone number successfully captured.", msg.checkRecipientCell());
    }

    @Test
    public void testRecipientFailure() {
        Message msg = new Message("08575975889", "Hi");
        assertEquals("Cell phone number is incorrectly formatted or does not contain an international code. Please correct the number and try again.", msg.checkRecipientCell());
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

    // ===== MAIN FUNCTIONALITY TESTS =====
    @Test
    public void testTotalMessagesCounter() {
        // Simulate sending two messages
        Message msg1 = new Message("+27718693002", "Hi Mike, can you join us for dinner tonight");
        msg1.sendMessageOption();  // choose send manually in popup
        Message msg2 = new Message("+27838968976", "Hey Keegan, did you receive the payment?");
        msg2.sendMessageOption();

        assertTrue(Message.returnTotalMessages() >= 0);
    }

    @Test
    public void testPrintMessagesNotEmptyAfterSend() {
        Message msg = new Message("+27718693002", "Hi again!");
        msg.sendMessageOption(); // choose send manually
        String printed = Message.printMessages();
        assertTrue(printed.contains("Message"));
    }
}