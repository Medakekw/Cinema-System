import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileManagerTest {

    @Test
    public void testLoadMovieInfoSuccessful() {
        String testFilename = "test_movie.txt";
        String movieContent = "Title: Test Movie\nGenre: Action\n";


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write(movieContent);
        } catch (IOException e) {
            fail("Setup failed: Could not create test file.");
        }

        try {
            String result = FileManager.loadMovieInfo(testFilename);
            assertEquals(movieContent, result);
        } catch (MovieNotFoundException e) {
            fail("MovieNotFoundException should not be thrown for a valid file.");
        } finally {
            try {
                Files.deleteIfExists(Paths.get(testFilename));
            } catch (IOException e) {
                System.out.println("Cleanup failed: Could not delete test file.");
            }
        }
    }

    @Test(expected = MovieNotFoundException.class)
    public void testLoadMovieInfoFileNotFound() throws MovieNotFoundException {
        String nonExistentFile = "nonexistent_movie.txt";

        FileManager.loadMovieInfo(nonExistentFile);
    }

    @Test
    public void testLoadMovieInfoWithIOException() {
        String testFilename = "test_movie.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write("Test data");
        } catch (IOException e) {
            fail("Setup failed: Could not create test file.");
        }

        try {
            Files.setPosixFilePermissions(Paths.get(testFilename), java.util.Collections.emptySet());
        } catch (IOException e) {
            fail("Setup failed: Could not restrict file permissions.");
        }

        try {
            String result = FileManager.loadMovieInfo(testFilename);
            assertTrue(result.isEmpty());
        } catch (MovieNotFoundException e) {
            fail("MovieNotFoundException should not be thrown when an IOException occurs.");
        } finally {
            try {
                Files.setPosixFilePermissions(Paths.get(testFilename), java.util.Set.of(java.nio.file.attribute.PosixFilePermission.OWNER_READ, java.nio.file.attribute.PosixFilePermission.OWNER_WRITE));
                Files.deleteIfExists(Paths.get(testFilename));
            } catch (IOException e) {
                System.out.println("Cleanup failed: Could not restore permissions or delete test file.");
            }
        }
    }
}
