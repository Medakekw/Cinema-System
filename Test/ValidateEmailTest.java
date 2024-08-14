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
    public void testValidEmail() {
        String validEmail = "test@example.com";
        try {
            cinemaSystem.validateEmail(validEmail);
        } catch (InvalidEmailException e) {
            fail("Exception should not be thrown for a valid email");
        }
    }

    @Test(expected = InvalidEmailException.class)
    public void testInvalidEmailMissingAtSymbol() throws InvalidEmailException {
        String invalidEmail = "testexample.com";
        cinemaSystem.validateEmail(invalidEmail);
    }

    @Test(expected = InvalidEmailException.class)
    public void testInvalidEmailMissingDot() throws InvalidEmailException {
        String invalidEmail = "test@examplecom";
        cinemaSystem.validateEmail(invalidEmail);
    }
}
