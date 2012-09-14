package auctions;

import system.Auctionator;
import users.User;

/**
 * This class represents an Auction in the system.
 *
 * @author Michaella Neirou
 */
public class Auction {

    private Integer id;
    private User owner;
    private Item item;
    private String title;
    private String description;
    private static Integer duration = 3;
    private Double finalPrice;
    private User winner;
    private boolean active;

    public Auction(String title, Item item, User owner) {
        this.id = Auctionator.getNextAuctionId();
        this.title = title;
        this.item = item;
        this.owner = owner;

    }

    public String getTitle() {
        return title;
    }

    public String getType(String lang) {
        String str = "";
        if (lang.equalsIgnoreCase("en")) {
            if (this instanceof Buyout) {
                str = "Buyout";
            } else if (this instanceof Bid) {
                str = "Bid";
            } else {
                str = "Auction";
            }
        } else if (lang.equalsIgnoreCase("gr")) {
            if (this instanceof Buyout) {
                str = "Σταθερή τιμή";
            } else if (this instanceof Bid) {
                str = "Ελεύθερη τιμή";
            } else {
                str = "Άγνωστη Δημοπρασία";
            }
        }

        return str;
    }

    public Double getPrice() {
        Double d = 0.0;

        if (this instanceof Buyout) {
            d = ((Buyout) this).getBuyoutPrice();
            
        } else if (this instanceof Bid) {
            d = ((Bid) this).getBidPrice();
            
        } else {
            
        }


        return d;
    }

    public void printAuctionStats() {

        System.out.println("----- Auction stats -----");
        System.out.println("| id: " + this.getId());
        System.out.println("| title: " + this.getTitle());
        System.out.println("| type: " + this.getType("en"));
        System.out.println("| price: " + this.getPrice());
        System.out.println("| owner: " + this.getOwner().getUsername());
        System.out.println("------------------------");


    }

    public Integer getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public static Integer getDuration() {
        return duration;
    }
    
    
}
