package exceptions;

/**
 * The UserNotFoundException class is a custom exception that is thrown when a specified user
 * cannot be found in the system, such as during login when the provided credentials do not match any existing user.
 */
public class UserNotFoundException extends Exception {

    /**
     * Constructs a new UserNotFoundException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
