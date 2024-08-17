package cinema;

/**
 * The User class is an abstract representation of a user in the cinema system.
 * It contains basic user information such as email and provides methods to manage this information.
 * Subclasses should implement the abstract method {@link #displayUserInfo()} to provide specific user information.
 */
public abstract class User {
    protected String email;

    /**
     * Constructs a new User object with the specified email.
     *
     * @param email The email of the user.
     */
    public User(String email) {
        this.email = email;
    }

    /**
     * Returns the email of the user.
     *
     * @return The user's email.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the user.
     *
     * @param email The email to set for the user.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Abstract method to display user information.
     * Subclasses must implement this method to provide specific details about the user.
     */
    public abstract void displayUserInfo();

    /**
     * Returns a string representation of the user, including the email.
     *
     * @return A string representation of the user.
     */
    @Override
    public String toString() {
        return "User [email=" + email + "]";
    }
}
