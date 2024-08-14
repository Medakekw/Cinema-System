package cinema;

public class Customer extends User {
    private String password;

    public Customer(String email, String password) {
        super(email);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public void displayUserInfo() {
        System.out.println("Customer Email: " + getEmail());
        System.out.println("Customer Password: " + password);
    }
    @Override
    public String toString() {
        return "Customer [email=" + getEmail() + ", password=" + password + "]";
    }
}
