package cinema;

/**
 * The Movie class represents a movie in the cinema system, including its title, price, and playtime.
 */
public class Movie {
    private String title;
    private double price;
    private String playTime;

    /**
     * Constructs a new Movie object with the specified title, price, and playtime.
     *
     * @param title    The title of the movie.
     * @param price    The price of the movie ticket.
     * @param playTime The playtime of the movie.
     */
    public Movie(String title, double price, String playTime) {
        this.title = title;
        this.price = price;
        this.playTime = playTime;
    }

    /**
     * Sets the title of the movie.
     *
     * @param title The title to set for the movie.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the price of the movie ticket.
     *
     * @param price The price to set for the movie ticket.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Sets the playtime of the movie.
     *
     * @param playTime The playtime to set for the movie.
     */
    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    /**
     * Returns the title of the movie.
     *
     * @return The title of the movie.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the price of the movie ticket.
     *
     * @return The price of the movie ticket.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns the playtime of the movie.
     *
     * @return The playtime of the movie.
     */
    public String getPlayTime() {
        return playTime;
    }

    /**
     * Returns a string representation of the movie, including the title, price, and playtime.
     *
     * @return A string representation of the movie.
     */
    @Override
    public String toString() {
        return title + " - $" + price + " (" + playTime + ")";
    }
}
