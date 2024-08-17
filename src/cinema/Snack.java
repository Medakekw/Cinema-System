package cinema;

/**
 * The Snack class represents a snack item available in the cinema, including its name and price.
 */
public class Snack {
    private String name;
    private double price;

    /**
     * Constructs a new Snack object with the specified name and price.
     *
     * @param name  The name of the snack.
     * @param price The price of the snack.
     */
    public Snack(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Sets the name of the snack.
     *
     * @param name The name to set for the snack.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the price of the snack.
     *
     * @param price The price to set for the snack.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Returns the name of the snack.
     *
     * @return The name of the snack.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the price of the snack.
     *
     * @return The price of the snack.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the snack, including the name and price.
     *
     * @return A string representation of the snack.
     */
    @Override
    public String toString() {
        return name + " - $" + price;
    }
}
