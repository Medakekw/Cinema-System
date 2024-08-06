package cinema;

import cinema.utils.FileManager;
import cinema.utils.Greetable;
import exceptions.InvalidEmailException;
import exceptions.InvalidChoiceException;
import exceptions.MovieNotFoundException;
import exceptions.UserNotFoundException;

import java.util.List;
import java.util.Scanner;

public class CinemaSystem implements Greetable {
    private Timetable timetable;
    private List<Snack> snacks;
    private Customer customer;
    private boolean loggedIn = false;

    public CinemaSystem() {
        this.timetable = new Timetable();
        this.snacks = FileManager.loadSnacks("data/snacks.txt");
    }

    public void start() {
        greet();
        try {
            handleUserAuthentication();
            showMainMenu();
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    @Override
    public void greet() {
        System.out.println("Welcome to the Cinema System!");
    }

    private void handleUserAuthentication() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register(10% discount on purchases for users with an account!)");
            System.out.println("3. Continue without an account");
            System.out.println("4. Exit");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        login();
                        return;
                    case 2:
                        register();
                        login();
                        return;
                    case 3:
                        return;
                    case 4:
                        System.out.println("Goodbye!");
                        System.exit(0);
                    default:
                        throw new InvalidChoiceException("Invalid choice. Please select a valid option.");
                }
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void login() throws InvalidEmailException, UserNotFoundException {
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

    private void register() throws InvalidEmailException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your email:");
        String email = scanner.nextLine();
        validateEmail(email);
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();

        FileManager.saveLogin(email, password);
        System.out.println("Registration successful! Please login.");
    }

    private void validateEmail(String email) throws InvalidEmailException {
        if (!email.contains("@") || !email.contains(".")) {
            throw new InvalidEmailException("Invalid email format. Please enter a valid email.");
        }
    }

    private void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. See Timetable");
            System.out.println("2. Buy Ticket");
            System.out.println("3. View My Info");
            System.out.println("4. Exit");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (choice) {
                    case 1:
                        showTimetable();
                        break;
                    case 2:
                        buyTicket();
                        break;
                    case 3:
                        if (customer != null) {
                            customer.displayUserInfo();
                        } else {
                            System.out.println("No customer info available.");
                        }
                        break;
                    case 4:
                        System.out.println("Goodbye!");
                        return;
                    default:
                        throw new InvalidChoiceException("Invalid choice. Please select a valid option.");
                }
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    private void showTimetable() {
        timetable.loadTimetable("data/timetable.txt");
        timetable.displayTimetable();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie number to see more details, or 0 to return to main menu:");
        try {
            int movieNumber = scanner.nextInt();
            scanner.nextLine();  // consume newline

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

    private void buyTicket() {
        timetable.loadTimetable("data/timetable.txt");
        timetable.displayTimetable();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter movie number to buy a ticket:");
        try {
            int movieNumber = scanner.nextInt();
            scanner.nextLine();  // consume newline

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
            }

            System.out.println("Total cost: $" + totalCost);
            System.out.println("Confirmation receipt sent to " + (customer != null ? customer.getEmail() : "your email"));
            System.out.println("Ticket purchased successfully!");
        } catch (MovieNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (InvalidChoiceException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

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
                scanner.nextLine();  // consume newline

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
