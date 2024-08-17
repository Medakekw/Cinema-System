package exceptions;

/**
 * The InvalidChoiceException class is a custom exception that is thrown when a user makes an invalid choice
 * in the application, such as selecting an option that is not available in a menu.
 */
public class InvalidChoiceException extends Exception {

    /**
     * Constructs a new InvalidChoiceException with the specified detail message.
     *
     * @param message The detail message explaining the reason for the exception.
     */
    public InvalidChoiceException(String message) {
        super(message);
    }
}
