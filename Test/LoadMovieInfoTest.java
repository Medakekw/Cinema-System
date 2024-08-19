import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import cinema.utils.FileManager;
import exceptions.MovieNotFoundException;

public class LoadMovieInfoTest {

    @Test
    public void testLoadMovieInfoSuccessful() throws Exception {
        String testFilename = "test_movie.txt";
        String movieContent = "Title: Test Movie\nGenre: Action\n";

        // Kreiranje test fajla sa informacijama o filmu
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write(movieContent);
        }

        // Testiranje
        String result = FileManager.loadMovieInfo(testFilename);
        assertEquals(movieContent, result);

        // Ciscenje test fajla
        Files.deleteIfExists(Paths.get(testFilename));
    }

    @Test(expected = MovieNotFoundException.class)
    //Testiranje da metoda baca movienotfoundexception kada fajl nije nadjen
    public void testLoadMovieInfoFileNotFound() throws Exception {
        String nonExistentFile = "nonexistent_movie.txt";
        FileManager.loadMovieInfo(nonExistentFile);
    }

    @Test
    public void testLoadMovieInfoEmptyFile() throws Exception {
        String testFilename = "empty_movie.txt";

        // Kreiranje praznog test fajla
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write("");
        }

        // Testiranje metode
        String result = FileManager.loadMovieInfo(testFilename);
        assertEquals("", result);

        // Ciscenje test fajla
        Files.deleteIfExists(Paths.get(testFilename));
    }
}
