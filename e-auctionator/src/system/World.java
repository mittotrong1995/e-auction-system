package system;

import auctions.Auction;
import auctions.Item;
import java.util.ArrayList;
import java.util.Date;
import tools.AuctionCalendar;
import users.Auctioneer;
import users.Customer;
import users.User;

/**
 *
 * @author Stratos
 */
public class World {

    public static boolean SYSTEM_MESSAGES = true;
    private static ArrayList<User> users;
    private static ArrayList<Item> items;
    private AuctionHouse auctionHouse;

    public World(int d, int m, int y) {
        this.users = new ArrayList<>();
        this.items = new ArrayList<>();
        this.auctionHouse = new AuctionHouse();
        AuctionCalendar.init(d, m, y);
    }

    /**
     * This method creates a new user with a random password.
     *
     * @param username
     * @param type
     * @param info Should contain the AFM if it's an auctioneer or the shipping
     * address if it's a customer.
     * @return Returns an instance of the created user
     */
    public User createUser(String username, String type, String info) {
        User u = null;
        if (type.equalsIgnoreCase("auctioneer")) {
            u = new Auctioneer(username);
            ((Auctioneer) u).setAfm(info);
            World.users.add(u);
            System.out.println("Νέος χρήστης με όνομα χρήστη: " + u.getUsername());
            System.out.println("Τύπος: " + u.getType("gr"));

        } else if (type.equalsIgnoreCase("customer")) {
            u = new Customer(username);
            ((Customer) u).setShippingAddress(info);
            World.users.add(u);
            System.out.println("Νέος χρήστης με όνομα χρήστη: " + u.getUsername());
            System.out.println("Τύπος: " + u.getType("gr"));

        } else {
        }
        return u;

    }

    /**
     * This method creates a new item in the world
     *
     * @param name
     * @return An instance of the item created
     */
    public Item createItem(String name) {
        Item i = null;
        i = new Item(name);
        World.items.add(i);
        return i;
    }

    /**
     * This method goes to the next day with the aid of the provided
     * AuctionCalendar class.
     *
     */
    public void advanceDay() {
        AuctionCalendar.nextDay();
        this.auctionHouse.deactivateExpiredAuctions();
    }

    public static Integer getNextUserId() {
        int size = World.users.size();
        size++;
        return size;
    }

    public static Integer getNextItemId() {
        int size = World.items.size();
        size++;
        return size;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public AuctionHouse getAuctionHouse() {
        return auctionHouse;
    }

    void giveItemToUser(Item item, User user) {
        user.receiveItem(item);
    }

    public static void mailTo(Mail mail, String address) {
        for (User user : users) {
            if (user instanceof Customer) {
                if (((Customer) user).getShippingAddress().equalsIgnoreCase(address)) {
                    mail(mail, user);
                }
            }

        }

    }

    private static void mail(Mail mail, User user) {
        user.receiveMail(mail);
        if (SYSTEM_MESSAGES) {
            System.out.println("++Mail sent to " + user.getUsername());
        }
    }
}
