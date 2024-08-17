package cinema;

import cinema.utils.FileManager;
import exceptions.MovieNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * The Timetable class represents a schedule of movies available in the cinema.
 * It provides methods to load, display, and manage the list of movies.
 */
public class Timetable {
    private List<Movie> movies;

    /**
     * Constructs a new Timetable object with an empty list of movies.
     */
    public Timetable() {
        this.movies = new ArrayList<>();
    }

    /**
     * Constructs a new Timetable object with the specified list of movies.
     *
     * @param movies The list of movies to initialize the timetable with.
     */
    public Timetable(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Loads the timetable by reading movie data from a specified file.
     *
     * @param filename The name of the file containing movie data.
     */
    public void loadTimetable(String filename) {
        this.movies = FileManager.loadMovies(filename);
    }

    /**
     * Displays the timetable, listing all movies currently in the timetable.
     * If no movies are available, a message indicating this is displayed.
     */
    public void displayTimetable() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
    }

    /**
     * Sets the list of movies for the timetable.
     *
     * @param movies The list of movies to set.
     */
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    /**
     * Returns the list of movies in the timetable.
     *
     * @return The list of movies.
     */
    public List<Movie> getMovies() {
        return movies;
    }

    /**
     * Returns the price of a movie based on its number in the timetable.
     *
     * @param movieNumber The number of the movie in the timetable.
     * @return The price of the movie.
     * @throws MovieNotFoundException If the movie number is invalid.
     */
    public double getMoviePrice(int movieNumber) throws MovieNotFoundException {
        if (movieNumber <= 0 || movieNumber > movies.size()) {
            throw new MovieNotFoundException("Movie not found.");
        }
        return movies.get(movieNumber - 1).getPrice();
    }

    /**
     * Returns a string representation of the timetable, including all movies.
     *
     * @return A string representation of the timetable.
     */
    @Override
    public String toString() {
        return "Timetable{" + "movies=" + movies + '}';
    }
}
