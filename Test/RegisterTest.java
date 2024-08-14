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
    public void testSuccessfulRegistration() throws InvalidEmailException {
        System.setIn(new java.io.ByteArrayInputStream("test@example.com\npassword123\n".getBytes()));

        cinemaSystem.register();

        assertTrue(FileManager.verifyLogin("test@example.com", "password123"));
    }

    @Test(expected = InvalidEmailException.class)
    public void testInvalidEmailFormat() throws InvalidEmailException {

        System.setIn(new java.io.ByteArrayInputStream("invalid-email\npassword123\n".getBytes()));

        cinemaSystem.register();
    }

    @Test
    public void testRegistrationWithExistingEmail() throws InvalidEmailException {

        System.setIn(new java.io.ByteArrayInputStream("test@example.com\npassword123\n".getBytes()));
        cinemaSystem.register();

        System.setIn(new java.io.ByteArrayInputStream("test@example.com\nnewpassword\n".getBytes()));
        cinemaSystem.register();

        assertTrue(FileManager.verifyLogin("test@example.com", "password123"));
        assertFalse(FileManager.verifyLogin("test@example.com", "newpassword"));
    }
}
