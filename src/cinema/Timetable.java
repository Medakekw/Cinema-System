package cinema;

import cinema.utils.FileManager;
import exceptions.MovieNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class Timetable {
    private List<Movie> movies;

    public Timetable() {
        this.movies = new ArrayList<>();
    }

    public void loadTimetable(String filename) {
        this.movies = FileManager.loadMovies(filename);
    }

    public void displayTimetable() {
        if (movies.isEmpty()) {
            System.out.println("No movies available.");
            return;
        }

        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ". " + movies.get(i));
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public double getMoviePrice(int movieNumber) throws MovieNotFoundException {
        if (movieNumber <= 0 || movieNumber > movies.size()) {
            throw new MovieNotFoundException("Movie not found.");
        }
        return movies.get(movieNumber - 1).getPrice();
    }
}
