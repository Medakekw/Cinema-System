package cinema;

public abstract class User {
    protected String email;

    public User(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public abstract void displayUserInfo();

    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "User [email=" + email + "]";
    }
}
