package system;

import auctions.Auction;
import auctions.Bid;
import auctions.Buyout;
import auctions.Item;
import java.util.ArrayList;
import java.util.Date;
import tools.AuctionCalendar;
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
    //private static Date date;

    public Auctionator(int d, int m, int y) {
        this.users = new ArrayList<>();
        this.auctions = new ArrayList<>();
        this.items = new ArrayList<>();
        AuctionCalendar.init(d, m, y);
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
            
        } else if (type.equalsIgnoreCase("customer")) {
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
            
        } else if (type.equalsIgnoreCase("bid")) {
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
        AuctionCalendar.nextDay();
        deactivateExpiredAuctions();
    }
    
    private void deactivateExpiredAuctions() {
        for (Auction a : auctions) {
            Date d = a.getCreationDate();
            Integer days = a.getDuration();
            boolean expired = AuctionCalendar.isCurrentDateNDaysAfter(d, days - 1);
            if (expired && a.isActive()) {
                a.expire();
            }
            
        }
    }

    /**
     * This method allows the customer to place a bid for a certain auction.
     *
     * @param bid The bid price
     * @param auction The auction that the user wants to bid on
     * @param user The user
     */
    public void placeBid(Double bid, Auction auction, User user) {
        if (user instanceof Customer) {
            ((Customer) user).makeOffer(bid, auction);
        } else if (user instanceof Auctioneer) {
            System.out.println("Προσοχή: Μονάχα οι πελάτες μπορούν να κάνουν προσφορές.");
            
        }
        
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
