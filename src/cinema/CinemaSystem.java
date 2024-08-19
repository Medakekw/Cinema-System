package cinema;

import cinema.utils.FileManager;
import cinema.utils.Greetable;
import exceptions.InvalidChoiceException;
import exceptions.InvalidEmailException;
import exceptions.MovieNotFoundException;
import exceptions.UserNotFoundException;

import java.util.List;
import java.util.Scanner;

/**
 * The CinemaSystem class represents a system that manages various operations related to a cinema,
 * including handling user authentication, managing the timetable, selling tickets, and offering snacks.
 */
public class CinemaSystem implements Greetable {
    private Timetable timetable;
    private List<Snack> snacks;
    private Customer customer;
    private boolean loggedIn = false;
    private UI ui;

    /**
     * Constructs a new CinemaSystem object and initializes the timetable, snacks, and UI components.
     */
    public CinemaSystem() {
        this.timetable = new Timetable();
        this.snacks = FileManager.loadSnacks("data/snacks.txt");
        this.ui = new UI(this);
    }

    /**
     * Starts the CinemaSystem, including greeting the user, handling user authentication,
     * and displaying the main menu.
     */
    public void start() {
        greet();
        try {
            ui.handleUserAuthentication();
            ui.showMainMenu();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Greets the user with a welcome message.
     */
    @Override
    public void greet() {
        System.out.println("Welcome to the Cinema System!");
    }

    /**
     * Handles user login by asking for an email and password, and verifying the credentials.
     * If the login is successful, the user is marked as logged in.
     *
     * @throws InvalidEmailException If the email format is invalid.
     * @throws UserNotFoundException If the user is not found or the credentials are incorrect.
     */
    public void login() throws InvalidEmailException, UserNotFoundException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();
        validateEmail(email);
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        if (FileManager.verifyLogin(email, password)) {
            customer = new Customer(email, password);
            loggedIn = true;
            System.out.println("Login successful!");
        } else {
            throw new UserNotFoundException("Invalid email or password. Please try again.");
        }
    }

    /**
     * Handles user registration by asking for an email and password, and saving the credentials.
     * After registration, the user is prompted to log in.
     *
     * @throws InvalidEmailException If the email format is invalid.
     */
    public void register() throws InvalidEmailException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();
        validateEmail(email);
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        FileManager.saveLogin(email, password);
        System.out.println("Registration successful! Please login.");
    }

    /**
     * Validates the format of the email.
     *
     * @param email The email address to validate.
     * @throws InvalidEmailException If the email format is invalid.
     */
    private void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException("Invalid email format. Please enter a valid email.");
        }
    }

    /**
     * Displays the movie timetable, allowing the user to view details of a selected movie.
     */
    public void showTimetable() {
        timetable.loadTimetable("data/timetable.txt");
        timetable.displayTimetable();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie number to see more details, or 0 to return to main menu:");
        try {
            int movieNumber = scanner.nextInt();
            scanner.nextLine();

            if (movieNumber == 0) {
                return;
            }

            String movieInfo = FileManager.loadMovieInfo("data/movie" + movieNumber + "_info.txt");
            System.out.println(movieInfo);
        } catch (MovieNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Allows the user to buy a ticket for a selected movie, with an optional snack purchase.
     * Applies a discount if the user is logged in.
     */
    public void buyTicket() {
        timetable.loadTimetable("data/timetable.txt");
        timetable.displayTimetable();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie number to buy a ticket:");
        try {
            int movieNumber = scanner.nextInt();
            scanner.nextLine();

            if (movieNumber <= 0 || movieNumber > timetable.getMovies().size()) {
                throw new InvalidChoiceException("Invalid movie number. Please select a valid movie number.");
            }

            double totalCost = timetable.getMoviePrice(movieNumber);

            System.out.println("Would you like to buy snacks? (yes/No)");
            String buySnacks = scanner.nextLine();

            if (buySnacks.equalsIgnoreCase("yes")) {
                totalCost += selectSnacks();
            }

            if (loggedIn) {
                totalCost *= 0.9; // Apply 10% discount
                System.out.println("Confirmation receipt sent to " + (customer != null ? customer.getEmail() : "your email"));

            }

            System.out.println("Total cost: $" + totalCost);
            System.out.println("Ticket purchased successfully!");
        } catch (MovieNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays information about the logged-in customer.
     * If no customer is logged in, a message indicating that no customer info is available is displayed.
     */
    public void displayCustomerInfo() {
        if (customer != null) {
            customer.displayUserInfo();
        } else {
            System.out.println("No customer info available.");
        }
    }

    /**
     * Allows the user to select snacks for purchase.
     * The total cost of the selected snacks is calculated and returned.
     *
     * @return The total cost of the selected snacks.
     */
    private double selectSnacks() {
        Scanner scanner = new Scanner(System.in);
        double snackCost = 0.0;

        for (int i = 0; i < snacks.size(); i++) {
            System.out.println((i + 1) + ". " + snacks.get(i));
        }
        System.out.println("Enter snack number to buy (0 to finish):");

        while (true) {
            try {
                int snackNumber = scanner.nextInt();
                scanner.nextLine();

                if (snackNumber == 0) {
                    break;
                }

                if (snackNumber < 1 || snackNumber > snacks.size()) {
                    throw new InvalidChoiceException("Invalid snack number. Please select a valid option.");
                }

                Snack selectedSnack = snacks.get(snackNumber - 1);
                snackCost += selectedSnack.getPrice();
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        return snackCost;
    }
}
