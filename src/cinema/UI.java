package cinema;

import exceptions.InvalidChoiceException;
import exceptions.InvalidEmailException;
import exceptions.MovieNotFoundException;
import exceptions.UserNotFoundException;

import java.util.Scanner;

/**
 * The UI class represents the user interface for the Cinema System.
 * It handles user interactions, including authentication, main menu navigation, and executing cinema-related actions.
 */
public class UI {
    private CinemaSystem cinemaSystem;

    /**
     * Constructs a new UI object associated with a specific CinemaSystem.
     *
     * @param cinemaSystem The CinemaSystem instance that this UI interacts with.
     */
    public UI(CinemaSystem cinemaSystem) {
        this.cinemaSystem = cinemaSystem;
    }

    /**
     * Returns the CinemaSystem instance associated with this UI.
     *
     * @return The CinemaSystem instance.
     */
    public CinemaSystem getCinemaSystem() {
        return cinemaSystem;
    }

    /**
     * Sets the CinemaSystem instance associated with this UI.
     *
     * @param cinemaSystem The CinemaSystem instance to set.
     */
    public void setCinemaSystem(CinemaSystem cinemaSystem) {
        this.cinemaSystem = cinemaSystem;
    }

    /**
     * Handles user authentication by presenting a menu with options to login, register, continue without an account, or exit.
     * If the user chooses to login or register, the respective methods from the CinemaSystem are called.
     */
    public void handleUserAuthentication() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Register(10% discount on purchases for users with an account!)");
            System.out.println("3. Continue without an account");
            System.out.println("4. Exit");
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        cinemaSystem.login();
                        return;
                    case 2:
                        cinemaSystem.register();
                        cinemaSystem.login();
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

    /**
     * Displays the main menu of the cinema system, providing options to see the timetable, buy a ticket, view customer information, or exit.
     * Executes the appropriate CinemaSystem methods based on the user's selection.
     */
    public void showMainMenu() {
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
                        cinemaSystem.showTimetable();
                        break;
                    case 2:
                        cinemaSystem.buyTicket();
                        break;
                    case 3:
                        cinemaSystem.displayCustomerInfo();
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

    /**
     * Returns a string representation of the UI object, including its associated CinemaSystem.
     *
     * @return A string representation of the UI.
     */
    @Override
    public String toString() {
        return "UI(cinemaSystem=" + cinemaSystem + ", UI=" + this + ")";
    }
}
