package system;

import auctions.Auction;
import auctions.Bid;
import auctions.Buyout;
import auctions.Item;
import java.util.ArrayList;
import users.Auctioneer;
import users.Customer;
import users.User;

/**
 * This class represents the whole auction system. It will use all the other
 * classes and provide functions that allows us to make auctions, place bids,
 * create users, etc.
 *
 * @author Michaella Neirou
 */
public class Auctionator {


    private static ArrayList<User> users;
    private static ArrayList<Auction> auctions;
    private static ArrayList<Item> items;
    
    public Auctionator() {
        users = new ArrayList<User>();
        auctions = new ArrayList<Auction>();
        items = new ArrayList<Item>();
    }
    
    public void printAuctionDuration() {
        Integer duration = Auction.getDuration();
        if (duration == 1) {
            System.out.println("Η διάρκεια των δημοπρασιών είναι 1 ημέρα");
        } else {
            System.out.println("Η διάρκεια των δημοπρασιών είναι " + duration + " ημέρες");
        }
        
    }

    /**
     * This method creates a new user with a random password.
     *
     * @param username
     * @param type
     * @return Returns the created user
     */
    public User createUser(String username, String type) {
        User u = null;
        if (type.equalsIgnoreCase("auctioneer")) {
            //Auctioneer a;
            u = new Auctioneer(username);
            Auctionator.users.add(u);
            System.out.println("Νέος χρήστης με όνομα χρήστη: " + u.getUsername());
            System.out.println("Τύπος: " + u.getType("gr"));
            
        } else if(type.equalsIgnoreCase("customer")) {
            //Customer c;
            u = new Customer(username);
            Auctionator.users.add(u);
            System.out.println("Νέος χρήστης με όνομα χρήστη: " + u.getUsername());
            System.out.println("Τύπος: " + u.getType("gr"));
            
        } else {
            
        }
        return u;

    }
    
    public Item createItem(String name) {
        Item i = null;
        i = new Item(name);
        Auctionator.items.add(i);
        return i;
    }

    /**
     * This method creates a new auction.
     *
     * @param title
     * @param description
     * @param item
     * @param user
     * @return Returns the created auction.
     */
    public Auction createAuction(String title, String type, Item item, User user, Double price) {
        Auction a = null;
        if (type.equalsIgnoreCase("buyout")) {
            a = new Buyout(title, item, user, price);
            Auctionator.auctions.add(a);
            System.out.println("Νέα δημοπρασία για το: " + a.getTitle());
            System.out.println("Τύπος: " + a.getType("gr") + " (" + price + " ευρώ)");
            System.out.println("Η δημοπρασία " + a.getTitle() + " ανήκει στο χρήστη: " + user.getUsername());
            
        } else if(type.equalsIgnoreCase("bid")) {
            a = new Bid(title, item, user, price);
            Auctionator.auctions.add(a);
            System.out.println("Νέα δημοπρασία για το: " + a.getTitle());
            System.out.println("Τύπος: " + a.getType("gr") + " (αρχική τιμή " + price + " ευρώ)");
            System.out.println("Η δημοπρασία " + a.getTitle() + " ανήκει στο χρήστη: " + user.getUsername());
            
        } else {
            
        }
        return a;
    }

    /**
     * This method goes to the next day with the aid of the provided
     * AuctionCalendar class.
     *
     */
    public void advanceDay() {
    }

    /**
     * This method allows to place a bid for a certain auction.
     *
     * @param bid The bid price
     * @param id The id of the auction
     */
    public void placeBid(Double bid, Integer id) {
    }

    /**
     * This method shows us all the active auctions of a user. The active
     * auctions of an auctioneer can't be more than 10. A buyer doesn't have a
     * limit.
     *
     * @param user
     */
    public void printAuctions(User user) {
    }

    /**
     * This method shows the auctions that an auctioneer has won.
     *
     * @param a
     */
    public void printWonAuctions(Auctioneer a) {
    }
    
    public static Integer getNextUserId() {
        int size = Auctionator.users.size();
        size++;
        return size;
    }
    
    public static Integer getNextAuctionId() {
        int size = Auctionator.auctions.size();
        size++;
        return size;
    }
    
    public static Integer getNextItemId() {
        int size = Auctionator.items.size();
        size++;
        return size;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Auction> getAuctions() {
        return auctions;
    }
    
    
}
