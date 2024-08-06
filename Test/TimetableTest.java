package cinema;

import exceptions.MovieNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class TimetableTest {

    private Path movieFile;
    private Timetable timetable;

    @Before
    public void setUp() throws IOException {
        // Initialize file paths and Timetable instance
        movieFile = Files.createTempFile("timetable", ".txt");
        timetable = new Timetable();
    }

    @After
    public void tearDown() throws IOException {
        // Clean up files
        Files.deleteIfExists(movieFile);
    }

    // Tests for loadTimetable method
    @Test
    public void testLoadTimetable() throws IOException {
        Files.write(movieFile, "Inception,10.00,2:00 PM\nThe Matrix,12.00,4:00 PM".getBytes());

        timetable.loadTimetable(movieFile.toString());

        List<Movie> movies = timetable.getMovies();
        assertEquals("Should load 2 movies.", 2, movies.size());
        assertEquals("Inception", movies.get(0).getTitle());
        assertEquals("2:00 PM", movies.get(0).getPlayTime());
    }

    @Test
    public void testLoadTimetableEmptyFile() throws IOException {
        timetable.loadTimetable(movieFile.toString());

        List<Movie> movies = timetable.getMovies();
        assertTrue("Movies list should be empty for an empty file.", movies.isEmpty());
    }

    @Test
    public void testLoadTimetableInvalidFormat() throws IOException {
        Files.write(movieFile, "Invalid Movie Data".getBytes());

        timetable.loadTimetable(movieFile.toString());

        List<Movie> movies = timetable.getMovies();
        assertTrue("Movies list should be empty for invalid format.", movies.isEmpty());
    }

    // Tests for displayTimetable method
    @Test
    public void testDisplayTimetable() throws IOException {
        Files.write(movieFile, "Inception,10.00,2:00 PM\nThe Matrix,12.00,4:00 PM".getBytes());

        timetable.loadTimetable(movieFile.toString());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        timetable.displayTimetable();

        String output = outContent.toString().trim();
        assertTrue("Output should contain Inception and The Matrix.", output.contains("Inception - $10.0 (2:00 PM)"));
        assertTrue("Output should contain The Matrix.", output.contains("The Matrix - $12.0 (4:00 PM)"));
    }

    @Test
    public void testDisplayTimetableNoMovies() throws IOException {
        timetable.loadTimetable(movieFile.toString());

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        timetable.displayTimetable();

        String output = outContent.toString().trim();
        assertTrue("Output should indicate no movies available.", output.contains("No movies available"));
    }

    // Tests for getMoviePrice method
    @Test
    public void testGetMoviePrice() throws IOException, MovieNotFoundException {
        Files.write(movieFile, "Inception,10.00,2:00 PM\nThe Matrix,12.00,4:00 PM".getBytes());

        timetable.loadTimetable(movieFile.toString());

        double price = timetable.getMoviePrice(1);
        assertEquals("Price should be 10.00 for Inception.", 10.00, price, 0.01);
    }

    @Test
    public void testGetMoviePriceInvalidNumber() throws IOException {
        Files.write(movieFile, "Inception,10.00,2:00 PM\nThe Matrix,12.00,4:00 PM".getBytes());

        timetable.loadTimetable(movieFile.toString());

        try {
            timetable.getMoviePrice(3);
            fail("Expected MovieNotFoundException for invalid movie number.");
        } catch (MovieNotFoundException e) {
            assertEquals("Movie not found.", e.getMessage());
        }
    }

    @Test
    public void testGetMoviePriceEmptyFile() throws IOException {
        timetable.loadTimetable(movieFile.toString());

        try {
            timetable.getMoviePrice(1);
            fail("Expected MovieNotFoundException for empty timetable.");
        } catch (MovieNotFoundException e) {
            assertEquals("Movie not found.", e.getMessage());
        }
    }
}
