package exceptions;

/**
 * The InvalidEmailException class is a custom exception that is thrown when a user provides an invalid email address
 * during operations such as registration or login in the application.
 */
public class InvalidEmailException extends Exception {

    /**
     * Constructs a new InvalidEmailException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidEmailException(String message) {
        super(message);
    }
}
