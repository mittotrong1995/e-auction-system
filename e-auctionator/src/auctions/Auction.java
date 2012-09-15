package auctions;

import java.util.Date;
import system.Auctionator;
import tools.AuctionCalendar;
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
    private boolean active = false;
    private Date creationDate;

    public Auction(String title, Item item, User owner) {
        this.id = Auctionator.getNextAuctionId();
        this.title = title;
        this.item = item;
        this.owner = owner;
        this.creationDate = AuctionCalendar.getDate();
        this.active = true;


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
            d = ((Bid) this).getCurrentBid();

        } else {
        }


        return d;
    }

    public void printAuctionStats() {

        //String str = "";
        //str += "----- Auction stats -----" + "\n";
        //str += "| id: " + this.getId() + "\n";
        //str += "| title: " + this.getTitle() + "\n";

        System.out.println("----- Auction stats -----");
        System.out.println("| id: " + this.getId());
        System.out.println("| title: " + this.getTitle());
        System.out.println("| type: " + this.getType("en"));
        System.out.println("| price: " + this.getPrice());
        System.out.println("| owner: " + this.getOwner().getUsername());
        System.out.println("| created: " + this.getCreationDate());
        System.out.println("| active: " + this.isActive());
        System.out.println("------------------------");


    }

    public Date getCreationDate() {
        return creationDate;
    }

    public boolean isActive() {
        return active;
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

    public void expire() {
        this.active = false;
        System.out.println("Auction " + this.getTitle() + " has expired!");
    }
    
    
}
