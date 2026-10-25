package bai9;
import java.io.Serializable;

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private double currentPrice;
    private String highestBidder;

    public Product(String name, double startPrice) {
        this.name = name;
        this.currentPrice = startPrice;
        this.highestBidder = "Chua co";
    }

    public String getName() {
        return name;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    public void setCurrentPrice(double price) {
        this.currentPrice = price;
    }

    public void setHighestBidder(String bidder) {
        this.highestBidder = bidder;
    }

    @Override
    public String toString() {
        return "San pham: " + name
                + " | Gia hien tai: " + currentPrice
                + " | Nguoi dat cao nhat: " + highestBidder;
    }
}