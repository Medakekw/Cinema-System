import org.junit.Test;
import static org.junit.Assert.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import cinema.utils.FileManager;
import cinema.Snack;

public class LoadSnacksTest {

    @Test
    public void testLoadSnacksSuccessful() throws Exception {
        String testFilename = "test_snacks.txt";
        String snackContent = "Popcorn,5.0\nNachos,4.0\n";

        // Kriranje novog fajla sa informacijama o snacks
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write(snackContent);
        }

        // Testiranje metode
        List<Snack> snacks = FileManager.loadSnacks(testFilename);

        // Provera da je snacks lsita ucitana tacno
        assertEquals(2, snacks.size());
        assertEquals("Popcorn", snacks.get(0).getName());
        assertEquals(5.0, snacks.get(0).getPrice(), 0.01);

        // Ciscenje test fajla
        Files.deleteIfExists(Paths.get(testFilename));
    }

    @Test
    public void testLoadSnacksFileNotFound() throws Exception {
        String nonExistentFile = "nonexistent_snacks.txt";

        // Test metode
        List<Snack> snacks = FileManager.loadSnacks(nonExistentFile);

        // Provera da je snack lista prazna
        assertTrue(snacks.isEmpty());
    }

    @Test
    public void testLoadSnacksEmptyFile() throws Exception {
        String testFilename = "empty_snacks.txt";

        // Pravljenje praznog test fajla
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testFilename))) {
            writer.write("");
        }

        // Testiranje metode
        List<Snack> snacks = FileManager.loadSnacks(testFilename);

        // Provera da je snacks lista prazna
        assertTrue(snacks.isEmpty());

        // Ciscenje test fajla
        Files.deleteIfExists(Paths.get(testFilename));
    }
}
