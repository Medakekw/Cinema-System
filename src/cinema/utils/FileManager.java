package cinema.utils;

import cinema.Movie;
import cinema.Snack;
import exceptions.MovieNotFoundException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Movie> loadMovies(String filename) {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length < 3) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;  // Skip lines that don't have enough parts
                }

                String title = parts[0];
                double price;
                String playTime = parts[2];

                try {
                    price = Double.parseDouble(parts[1]);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid price format in line: " + line);
                    continue;  // Skip lines with invalid price format
                }

                Movie movie = new Movie(title, price, playTime);
                movies.add(movie);
            }
        } catch (IOException e) {
            System.out.println("Error loading movies: " + e.getMessage());
        }
        return movies;
    }

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

    public static void saveLogin(String email, String password) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("data/login.txt", true))) {
            bw.write(email + "," + password);
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving login: " + e.getMessage());
        }
    }

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
