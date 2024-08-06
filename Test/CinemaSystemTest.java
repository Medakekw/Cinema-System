package cinema;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CinemaSystemTest {

    private CinemaSystem cinemaSystem;
    private Movie inception;
    private Movie matrix;

    @Before
    public void setUp() {
        // Initialize CinemaSystem instance and sample movies
        cinemaSystem = new CinemaSystem();
        inception = new Movie("Inception", 10.00, "2:00 PM");
        matrix = new Movie("The Matrix", 12.00, "4:00 PM");
    }

    @After
    public void tearDown() {
        // Clean up if needed
        cinemaSystem = null;
        inception = null;
        matrix = null;
    }

    // Tests for addMovie method
    @Test
    public void testAddMovie() {
        cinemaSystem.addMovie(inception);
        assertTrue("Movie should be added to the system.", cinemaSystem.getMovies().contains(inception));
    }

    @Test
    public void testAddMovieDuplicate() {
        cinemaSystem.addMovie(inception);
        cinemaSystem.addMovie(inception); // Adding the same movie again
        assertEquals("There should be only one instance of the movie.", 1, cinemaSystem.getMovies().size());
    }

    @Test
    public void testAddMovieNull() {
        cinemaSystem.addMovie(null); // Adding a null movie
        assertEquals("Movies list should not change when adding null.", 0, cinemaSystem.getMovies().size());
    }

    // Tests for buyTicket method
    @Test
    public void testBuyTicketSuccessful() {
        cinemaSystem.addMovie(inception);
        double amount = cinemaSystem.buyTicket(1); // Movie number 1
        assertEquals("Ticket price should be 10.00.", 10.00, amount, 0.01);
    }

    @Test
    public void testBuyTicketInvalidNumber() {
        cinemaSystem.addMovie(inception);
        try {
            cinemaSystem.buyTicket(2); // Invalid movie number
            fail("Expected IllegalArgumentException for invalid movie number.");
        } catch (IllegalArgumentException e) {
            assertEquals("Movie number not found.", e.getMessage());
        }
    }

    @Test
    public void testBuyTicketNoMovies() {
        try {
            cinemaSystem.buyTicket(1); // No movies added yet
            fail("Expected IllegalStateException for no movies.");
        } catch (IllegalStateException e) {
            assertEquals("No movies available to purchase.", e.getMessage());
        }
    }

    // Tests for applyDiscount method
    @Test
    public void testApplyDiscountLoggedIn() {
        double originalPrice = 10.00;
        double discountedPrice = cinemaSystem.applyDiscount(true, originalPrice); // Logged in
        assertEquals("Discounted price should be 9.00 with 10% discount.", 9.00, discountedPrice, 0.01);
    }

    @Test
    public void testApplyDiscountNotLoggedIn() {
        double originalPrice = 10.00;
        double discountedPrice = cinemaSystem.applyDiscount(false, originalPrice); // Not logged in
        assertEquals("Price should remain the same without discount.", 10.00, discountedPrice, 0.01);
    }

    @Test
    public void testApplyDiscountEdgeCase() {
        double originalPrice = 0.00; // Edge case with zero price
        double discountedPrice = cinemaSystem.applyDiscount(true, originalPrice);
        assertEquals("Discounted price should be 0.00 even with a discount.", 0.00, discountedPrice, 0.01);
    }
}
