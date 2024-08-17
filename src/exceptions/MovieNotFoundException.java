package exceptions;

/**
 * The MovieNotFoundException class is a custom exception that is thrown when a specified movie
 * cannot be found in the system, such as when trying to retrieve details for a non-existent movie.
 */
public class MovieNotFoundException extends Exception {

    /**
     * Constructs a new MovieNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public MovieNotFoundException(String message) {
        super(message);
    }
}
