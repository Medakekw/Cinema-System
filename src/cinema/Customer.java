package cinema;

/**
 * The Customer class represents a customer in the cinema system. It extends the User class and adds a password
 * field, along with methods to get and set the password.
 */
public class Customer extends User {
    private String password;

    /**
     * Constructs a new Customer object with the specified email and password.
     *
     * @param email    The email of the customer.
     * @param password The password of the customer.
     */
    public Customer(String email, String password) {
        super(email);
        this.password = password;
    }

    /**
     * Returns the password of the customer.
     *
     * @return The customer's password.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password for the customer.
     *
     * @param password The new password to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Displays the customer's email and password information.
     * Overrides the displayUserInfo method from the User class.
     */
    @Override
    public void displayUserInfo() {
        System.out.println("Customer Email: " + getEmail());
        System.out.println("Customer Password: " + password);
    }

    /**
     * Returns a string representation of the customer object, including the email and password.
     *
     * @return A string representation of the customer.
     */
    @Override
    public String toString() {
        return "Customer [email=" + getEmail() + ", password=" + password + "]";
    }
}
