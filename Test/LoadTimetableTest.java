import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import cinema.utils.FileManager;
import cinema.Timetable;
import cinema.Movie;

public class LoadTimetableTest {

    @Test
    public void testLoadTimetableSuccessful() throws Exception {
        String testFilename = "test_timetable.txt";
        String movieContent = "Test Movie,10.0,120\n";

        // Kreiranje test fajla
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write(movieContent);
        }

        // Testiranje
        Timetable timetable = new Timetable();
        timetable.loadTimetable(testFilename);

        // Ucitani filmovi
        List<Movie> movies = timetable.getMovies();

        // Provera sta je zapravo ucitano
        System.out.println("Movies loaded: " + movies);

        // Provera da je dobro ucitano
        assertFalse("Timetable should not be empty after loading valid movie data.", movies.isEmpty());

        // Ciscenje test fajla
        Files.deleteIfExists(Paths.get(testFilename));
    }

    @Test
    public void testLoadTimetableEmptyFile() throws Exception {
        String testFilename = "empty_timetable.txt";

        // Kreiranje praznog test fajla
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write("");
        }

        // Testiranje
        Timetable timetable = new Timetable();
        timetable.loadTimetable(testFilename);

        // Provera da je timetable prazan
        assertTrue("Timetable should be empty after loading from an empty file.", timetable.getMovies().isEmpty());

        // Ciscenje test fajla
        Files.deleteIfExists(Paths.get(testFilename));
    }

    @Test
    public void testLoadTimetableFileNotFound() throws Exception {
        String nonExistentFile = "nonexistent_timetable.txt";

        // Testiranje
        Timetable timetable = new Timetable();
        timetable.loadTimetable(nonExistentFile);

        // Provera da je timetable stvarno prazan
        assertTrue("Timetable should be empty after attempting to load from a non-existent file.", timetable.getMovies().isEmpty());
    }
}
