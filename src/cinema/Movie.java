package cinema;

public class Movie {
    private String title;
    private double price;
    private String playTime;

    public Movie(String title, double price, String playTime) {
        this.title = title;
        this.price = price;
        this.playTime = playTime;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getPlayTime() {
        return playTime;
    }

    @Override
    public String toString() {
        return title + " - $" + price + " (" + playTime + ")";
    }
}
