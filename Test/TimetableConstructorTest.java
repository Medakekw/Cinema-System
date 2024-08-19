import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import cinema.Movie;
import cinema.Timetable;

public class TimetableConstructorTest {

    @Test

    // Inicijalizacija praznog timetable
    public void testDefaultConstructorInitializesEmptyTimetable() {
        Timetable timetable = new Timetable();
        assertTrue("The movies list should be empty after using the default constructor.", timetable.getMovies().isEmpty());
    }

    @Test
    // Test da li se timetable dobro inicijalizira sa odredjenom listom filmova
    public void testConstructorWithPredefinedList() {
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie("Test Movie", 10.0, "120"));
        Timetable timetable = new Timetable(movieList);
        assertEquals("The movies list should match the predefined list.", movieList, timetable.getMovies());
    }

    @Test

    //Dodavanje filma nakon inicijalizacije
    public void testAddMovieToTimetableAfterInitialization() {
        Timetable timetable = new Timetable();
        Movie movie = new Movie("New Movie", 12.5, "90");
        timetable.getMovies().add(movie);
        assertFalse("The movies list should contain one movie after adding it.", timetable.getMovies().isEmpty());
        assertEquals("The movies list should contain the added movie.", movie, timetable.getMovies().getFirst());
    }
}
