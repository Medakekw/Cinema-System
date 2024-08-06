package cinema;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    private Customer customer;

    @Before
    public void setUp() {
        // Initialize Customer instance
        customer = new Customer("user@example.com", "password123");
    }

    @After
    public void tearDown() {
        // Clean up if needed
        customer = null;
    }

    // Tests for updateEmail method
    @Test
    public void testUpdateEmail() {
        customer.updateEmail("newemail@example.com");
        assertEquals("newemail@example.com", customer.getEmail());
    }

    @Test
    public void testUpdateEmailInvalid() {
        customer.updateEmail(""); // Assuming empty email is invalid
        assertEquals("user@example.com", customer.getEmail()); // Ensure email hasn't changed
    }

    @Test
    public void testUpdateEmailEdgeCase() {
        customer.updateEmail("edge_case_email_with_@_symbol.com");
        assertEquals("edge_case_email_with_@_symbol.com", customer.getEmail());
    }

    // Tests for updatePassword method
    @Test
    public void testUpdatePassword() {
        customer.updatePassword("newpassword456");
        assertEquals("newpassword456", customer.getPassword());
    }

    @Test
    public void testUpdatePasswordEmpty() {
        customer.updatePassword("");
        assertEquals("password123", customer.getPassword()); // Ensure password hasn't changed
    }

    @Test
    public void testUpdatePasswordSpecialCharacters() {
        customer.updatePassword("P@ssw0rd!");
        assertEquals("P@ssw0rd!", customer.getPassword());
    }

    // Tests for displayUserInfo method
    @Test
    public void testDisplayUserInfo() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        customer.displayUserInfo();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Email: user@example.com"));
        assertTrue(output.contains("Password: password123"));
    }

    @Test
    public void testDisplayUserInfoUpdated() {
        customer.updateEmail("updated@example.com");
        customer.updatePassword("updatedpass");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        customer.displayUserInfo();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Email: updated@example.com"));
        assertTrue(output.contains("Password: updatedpass"));
    }

    @Test
    public void testDisplayUserInfoNoInfo() {
        Customer newCustomer = new Customer("", ""); // Creating with empty credentials

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        newCustomer.displayUserInfo();

        String output = outContent.toString().trim();
        assertTrue(output.contains("Email: "));
        assertTrue(output.contains("Password: "));
    }
}
