
package cinema;

import exceptions.InvalidChoiceException;
import exceptions.InvalidEmailException;
import exceptions.MovieNotFoundException;
import exceptions.UserNotFoundException;

import java.util.Scanner;

public class UI {
    private CinemaSystem cinemaSystem;

    public UI(CinemaSystem cinemaSystem) {
        this.cinemaSystem = cinemaSystem;
    }


    public CinemaSystem getCinemaSystem() {
        return cinemaSystem;
    }

    public void setCinemaSystem(CinemaSystem cinemaSystem) {
        this.cinemaSystem = cinemaSystem;
    }

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
    @Override
    public String toString() {
        return "UI(cinemaSystem=" + cinemaSystem + ", UI=" + this + ")";
    }
}