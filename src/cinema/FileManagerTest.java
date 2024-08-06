package cinema.utils;

import cinema.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.Assert.*;

public class FileManagerTest {

    private Path loginFile;
    private Path movieFile;

    @Before
    public void setUp() throws IOException {
        // Initialize file paths
        loginFile = Files.createTempFile("login", ".txt");
        movieFile = Files.createTempFile("timetable", ".txt");
    }

    @After
    public void tearDown() throws IOException {
        // Clean up files
        Files.deleteIfExists(loginFile);
        Files.deleteIfExists(movieFile);
    }

    // Tests for saveLogin method
    @Test
    public void testSaveLogin() throws IOException {
        FileManager.saveLogin("user@example.com", "password123");

        List<String> lines = Files.readAllLines(loginFile);
        assertEquals("Login file should contain one line.", 1, lines.size());
        assertEquals("user@example.com,password123", lines.get(0));
    }

    @Test
    public void testSaveLoginOverwrites() throws IOException {
        FileManager.saveLogin("user@example.com", "password123");
        FileManager.saveLogin("newuser@example.com", "newpassword123");

        List<String> lines = Files.readAllLines(loginFile);
        assertEquals("Login file should contain one line after overwriting.", 1, lines.size());
        assertEquals("newuser@example.com,newpassword123", lines.get(0));
    }

    @Test
    public void testSaveLoginEmpty() throws IOException {
        FileManager.saveLogin("", "");

        List<String> lines = Files.readAllLines(loginFile);
        assertEquals("Login file should contain one line with empty credentials.", 1, lines.size());
        assertEquals(",", lines.get(0));
    }

    // Tests for verifyLogin method
    @Test
    public void testVerifyLoginCorrect() throws IOException {
        FileManager.saveLogin("user@example.com", "password123");

        boolean result = FileManager.verifyLogin("user@example.com", "password123");
        assertTrue("Login should be successful with correct credentials.", result);
    }

    @Test
    public void testVerifyLoginIncorrect() throws IOException {
        FileManager.saveLogin("user@example.com", "password123");

        boolean result = FileManager.verifyLogin("user@example.com", "wrongpassword");
        assertFalse("Login should fail with incorrect password.", result);
    }

    @Test
    public void testVerifyLoginNonExistent() throws IOException {
        boolean result = FileManager.verifyLogin("nonexistent@example.com", "password123");
        assertFalse("Login should fail with non-existent user.", result);
    }

    // Tests for loadMovies method
    @Test
    public void testLoadMovies() throws IOException {
        Files.write(movieFile, "Inception,10.00,2:00 PM\nThe Matrix,12.00,4:00 PM".getBytes());

        List<Movie> movies = FileManager.loadMovies(movieFile.toString());

        assertEquals("Should load 2 movies.", 2, movies.size());
        assertEquals("Inception", movies.get(0).getTitle());
        assertEquals(10.00, movies.get(0).getPrice(), 0.01);
        assertEquals("2:00 PM", movies.get(0).getPlayTime());
    }

    @Test
    public void testLoadMoviesEmptyFile() throws IOException {
        List<Movie> movies = FileManager.loadMovies(movieFile.toString());
        assertTrue("Movies list should be empty for an empty file.", movies.isEmpty());
    }

    @Test
    public void testLoadMoviesInvalidFormat() throws IOException {
        Files.write(movieFile, "Invalid Movie Data".getBytes());

        List<Movie> movies = FileManager.loadMovies(movieFile.toString());
        assertTrue("Movies list should be empty for invalid format.", movies.isEmpty());
    }
}
