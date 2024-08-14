import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CinemaSystemTest {

    private CinemaSystem cinemaSystem;

    @Before
    public void setUp() {
        cinemaSystem = new CinemaSystem();
    }

    @Test
    public void testSuccessfulLogin() throws InvalidEmailException, UserNotFoundException {

        FileManager.saveLogin("test@example.com", "password123");

        System.setIn(new java.io.ByteArrayInputStream("test@example.com\npassword123\n".getBytes()));

        cinemaSystem.login();

        assertTrue(cinemaSystem.isLoggedIn());
        assertEquals("test@example.com", cinemaSystem.getCustomer().getEmail());
    }

    @Test(expected = InvalidEmailException.class)
    public void testInvalidEmailFormat() throws InvalidEmailException, UserNotFoundException {
        System.setIn(new java.io.ByteArrayInputStream("invalid-email\npassword123\n".getBytes()));

        cinemaSystem.login();
    }

    @Test(expected = UserNotFoundException.class)
    public void testUserNotFound() throws InvalidEmailException, UserNotFoundException {
        System.setIn(new java.io.ByteArrayInputStream("nonexistent@example.com\nwrongpassword\n".getBytes()));
        cinemaSystem.login();
    }
}
