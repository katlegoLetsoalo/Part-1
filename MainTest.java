/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package assignmentPartOne;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author RC_Student_lab
 */
public class MainTest {
    //first name test
     @Test
    public void testFirstName() {
        Login user = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Kyle", user.firstName());
    }

    //last name test
    @Test
    public void testLastName() {
        Login user = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals("Smith", user.lastName());
    }
    
   //username tests
    @Test
    public void testCheckUserNameCorrect(){
        Login user = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(user.checkUserName()); //boolean check
        assertEquals("User registered successfully!", user.registerUser());
    }
    
    @Test
    public void testCheckUserNameIncorrect(){
        Login user = new Login("kyle", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(user.checkUserName()); //boolean check
        String expected = "Username is not correctly formatted, ensure that your username contains an underscore and is no more than five characters in length.";
        assertEquals(expected, user.registerUser());
    }
    
    //password tests
    @Test
    public void testPasswordCorrect(){
        Login user = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(user.checkPasswordComplexity()); //boolean check
        assertEquals("User registered successfully!", user.registerUser());
    }
    
    @Test
    public void testPasswordIncorrect(){
        Login user = new Login("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertFalse(user.checkPasswordComplexity()); //boolean check
        String expected = "Password is not correctly formatted, ensure that your password contains at least eight characters, a capital letter, a number, and a special character.";
        assertEquals(expected, user.registerUser());
    }
    
    //cellphone tests
    @Test
    public void testCellPhoneCorrect(){
        Login user = new Login("Kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(user.checkCellPhoneNumber()); //boolean check
        assertEquals("User registered successfully!", user.registerUser());
    }
    
    @Test
    public void testCellPhoneIncorrect(){
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "0838968976", "Kyle", "Smith");
        assertFalse(user.checkCellPhoneNumber()); //boolean check
        String expected = "Cell number is not correctly formatted or does not contain an international code, please correct the number and try again.";
        assertEquals(expected, user.registerUser());
    }
    
    //login tests
    @Test
    public void testLoginSuccess(){
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(user.loginUser("kyl_1", "Ch&&sec@ke99!")); //boolean check
        String expected = "Welcome Kyle Smith it is nice to see you again.";
        assertEquals(expected, user.returnloginStatus(true));
    }
    
    @Test
    public void testLoginFail(){
        Login user = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(user.loginUser("wrong", "wrongPass")); //boolean check
        String expected = "Username or password incorrect, please try again.";
        assertEquals(expected, user.returnloginStatus(false));
    }

}
