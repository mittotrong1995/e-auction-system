package auctions;

import java.util.Date;
import system.AuctionHouse;
import system.World;
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

    /**
     * This basic constructor creates a new Auction given basic info: the title,
     * the item auctioned and the user the creates it. We can possibly create
     * more constructors that allow to input more information directly, like the
     * description without having to call the setter method later like in the
     * case of the description field.
     *
     * @param title The title of the auction. this is conveniently set to be the
     * same as the name of the item when we create auctions but it can also
     * differ.
     * @param item The item being auctioned
     * @param owner The user that creates the auction and also the owner of the
     * item (and the auction)
     */
    public Auction(String title, Item item, User owner) {
        this.id = AuctionHouse.getNextAuctionId();
        this.title = title;
        this.item = item;
        this.owner = owner;
        this.creationDate = AuctionCalendar.getDate();
        this.active = true;
        this.description = "No description set";
    }

    /**
     * This method returns the field Title. Since all the fields are private we
     * need such get methods aka getter methods.
     *
     * @return A String containing the title of the Auction
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method returns the type of the Auction. An auction can be of two
     * types. Bid and Buyout. Theoretically it can also be of Auction type but
     * it's not practically used anywhere in this program.
     *
     * @param lang This method gets a string argument indicating the language on
     * which we need our Type to be returned to. Right now it supports "en" for
     * English and "gr" for Greek. This makes printing easier.
     * @return A String containing the type of the Auction in the language
     * specified by the argument
     */
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

    /**
     * Returns the "price" of the Auction. The meaning of price changes
     * depending on the type of Auction. For Buyout types the price is merely
     * the steady, initial price of the Auction but for Bid types this field
     * contains the current Bid of the Auction.
     *
     * @return The price of the Auction depending on it's type.
     */
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

    /**
     * This method prints several fields of the Auction and it was created for
     * testing reasons while writing the program to make sure that an Auctions
     * contains the right values. It's not required by the exercise and it could
     * as well be deleted.
     */
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

    /**
     * Returns a date indicating when the Auction was created.
     *
     * @return The creation date that is a Date type.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Checks if the auction is active (true) or not (false)
     *
     * @return A boolean indicating if the Auction is active or not.
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the id of the Auction. Each Auction has a unique id even if it's
     * a Bid or Buyout.
     *
     * @return An Integer containing the unique id of an auction.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the owner of the Auction which is the user that created it.
     *
     * @return A User indicating the owner of the Auction.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Gets the duration of an auction. The exercise assumes that all auctions
     * share the same duration (ex. 3 days) thus the duration field is static
     * and is initialized once. Also we do not provide a setter method for the
     * duration field because it would be unsafe and it would increase
     * complexity. For example an auction that has expired after 3 days and then
     * we set the duration to 6 days.
     *
     * @return An Integer representing the days that an auction can be active
     * for
     */
    public static Integer getDuration() {
        return duration;
    }

    /**
     * This method expires an auction of type Bid. It is called when an auction
     * has been up for more days than it's allowed duration. It checks if there
     * is a higher bidder than the initial bid and in that case sets that bidder
     * as the winner and the final price of the Auction the same as the highest
     * bid. This method allows the customers that made offers on auctions to win
     * them when they expire after a few days.
     */
    public void expire() {
        this.active = false;
        //We can disable system messages by setting them to false. They are helpful but not asked by the excercise.
        if (World.SYSTEM_MESSAGES) {
            System.out.println("++Auction " + this.getTitle() + " has expired!");
        }
        if (this instanceof Bid) {
            if (((Bid) this).getCurrentHighestBidder() == null) {
                this.winner = null;
            } else {
                this.winner = ((Bid) this).getCurrentHighestBidder();
                this.finalPrice = ((Bid) this).getCurrentBid();
                this.winner.addWonAuction(this);
                //We can disable system messages by setting them to false. They are helpful but not asked by the excercise.
                if (World.SYSTEM_MESSAGES) {
                    System.out.println("++User " + this.winner.getUsername() + " won auction " + this.getTitle());
                }
            }
        } else if (this instanceof Buyout) {
            //System.out.println("Auction " + this.getTitle() + " is closing");
        }
    }

    /**
     * This method checks if the auction has a winner. A winner is set when the
     * auction expires and there is a highest bidder or when a customer
     * instantly buys an auction with a steady price aka Buyout.
     *
     * @return True or false depending if it has a winner
     */
    public boolean hasWinner() {
        boolean b = false;
        if (this.winner != null) {
            b = true;
        }
        return b;
    }

    /**
     * Each auction can cantain only one item and this method returns that item.
     *
     * @return The item being auctioned which is of Item type.
     */
    public Item getItem() {
        return item;
    }

    /**
     * Returns the user that is the winner of the auction. It has no protection
     * against null.
     *
     * @return A user of type User that indicates the winner of the auction.
     */
    public User getWinner() {
        return winner;
    }

    /**
     * Allows to set the winner of the auction.
     *
     * @param winner The user that we want to set as the winner.
     */
    public void setWinner(User winner) {
        this.winner = winner;
    }

    /**
     * Allows to set the final price of the Auction.
     *
     * @param finalPrice A Double that we want to set as the final price.
     */
    public void setFinalPrice(Double finalPrice) {
        this.finalPrice = finalPrice;
    }

    /**
     * This method closes the auction aka making it inactive.
     */
    public void close() {
        this.active = false;
    }

    /**
     * This method shows basic stats about an auction in just one line
     *
     */
    public void show() {
        String str = "";
        if (this instanceof Buyout) {
            str += "| " + this.getTitle() + " - " + this.getType("gr") + " - " + ((Buyout) this).getBuyoutPrice() + " ευρώ";
        } else if (this instanceof Bid) {
            str += "| " + this.getTitle() + " - " + this.getType("gr") + " - " + ((Bid) this).getCurrentBid() + " ευρώ";
        }
        System.out.println(str);
    }

    /**
     * This method aids with printing an auction the way it is required by the
     * exercise. Prints only one line.
     */
    public void print() {
        String str = "";
        String active = "Ανενεργή";
        if (this.isActive()) {
            active = "Ενεργή";
        }
        String winner = "Χωρίς νικητή";
        if (this.winner != null) {
            winner = "Νικητής ο " + this.getWinner().getUsername();
        }
        if (this instanceof Buyout) {
            str += this.getTitle() + " - " + active + " - " + winner;
        } else if (this instanceof Bid) {
            str += this.getTitle() + " - " + active + " - " + winner + " (" + ((Bid) this).getCurrentBid() + " ευρώ)";
        }
        System.out.println(str);
    }

    /**
     * Returns the description of the aution. The description is not used as an
     * arguement on the constructor so it has to be set first with
     * setDescription or else it will be initialized as "No description set".
     *
     * @return A String containing the description of the auction.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method allows us to set the description of the auction.
     *
     * @param description The String we want to set as the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the final price of the auction.
     *
     * @return A Double indicating the final price.
     */
    public Double getFinalPrice() {
        return finalPrice;
    }
}
