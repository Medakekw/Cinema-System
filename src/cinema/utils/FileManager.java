package cinema.utils;

import cinema.Movie;
import cinema.Snack;
import exceptions.MovieNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The FileManager class provides utility methods for loading and saving data related to movies, snacks, and user logins.
 * It includes methods to load movies and snacks from files, retrieve detailed movie information,
 * save user login details, and verify user login credentials.
 */
public class FileManager {

    /**
     * Loads a list of movies from a specified file.
     * The file should contain movie data in the format: title,price,playTime on each line.
     *
     * @param filename The name of the file containing movie data.
     * @return A list of Movie objects loaded from the file.
     */
    public static List<Movie> loadMovies(String filename) {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }

                String title = parts[0];
                double price;
                String playTime = parts[2];

                try {
                    price = Double.parseDouble(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in line: " + line);
                    continue;
                }

                Movie movie = new Movie(title, price, playTime);
                movies.add(movie);
            }
        } catch (IOException e) {
            System.out.println("Error loading movies: " + e.getMessage());
        }
        return movies;
    }

    /**
     * Loads detailed information about a specific movie from a specified file.
     *
     * @param filename The name of the file containing detailed movie information.
     * @return A string containing the movie information.
     * @throws MovieNotFoundException If the file containing the movie information is not found.
     */
    public static String loadMovieInfo(String filename) throws MovieNotFoundException {
        StringBuilder movieInfo = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                movieInfo.append(line).append("\n");
            }
        } catch (FileNotFoundException e) {
            throw new MovieNotFoundException("Movie information not found. Please select a valid movie.");
        } catch (IOException e) {
            System.out.println("Error loading movie information: " + e.getMessage());
        }
        return movieInfo.toString();
    }

    /**
     * Loads a list of snacks from a specified file.
     * The file should contain snack data in the format: name,price on each line.
     *
     * @param filename The name of the file containing snack data.
     * @return A list of Snack objects loaded from the file.
     */
    public static List<Snack> loadSnacks(String filename) {
        List<Snack> snacks = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                double price = Double.parseDouble(parts[1]);
                snacks.add(new Snack(name, price));
            }
        } catch (IOException e) {
            System.out.println("Error loading snacks: " + e.getMessage());
        }
        return snacks;
    }

    /**
     * Saves a user's email and password to the login file.
     * The login details are appended to the file in the format: email,password.
     *
     * @param email    The user's email.
     * @param password The user's password.
     */
    public static void saveLogin(String email, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/login.txt", true))) {
            bw.write(email + "," + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving login: " + e.getMessage());
        }
    }

    /**
     * Verifies a user's login credentials by checking the login file.
     *
     * @param email    The user's email.
     * @param password The user's password.
     * @return true if the login credentials are valid, false otherwise.
     */
    public static boolean verifyLogin(String email, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("data/login.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(email) && parts[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.out.println("Error verifying login: " + e.getMessage());
        }
        return false;
    }
}
