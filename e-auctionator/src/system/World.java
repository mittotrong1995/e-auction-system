package system;

import auctions.Item;
import java.util.ArrayList;
import tools.AuctionCalendar;
import users.Auctioneer;
import users.Customer;
import users.User;

/**
 * This class represents the world in our program. The exercise doesn't ask for
 * a class like that so it could be omitted. It holds a list of all the users
 * existing in our world and all the items. It provides methods to create items
 * and users, give them unique ids, send in world mails and advance the time to
 * the next day.
 *
 * @author Michaella Neirou
 */
public class World {

    public static boolean SYSTEM_MESSAGES = true;
    private static ArrayList<User> users;
    private static ArrayList<Item> items;
    private AuctionHouse auctionHouse;

    /**
     * The constructor creates a world with a certain month, day and year as a
     * starting point. It uses the ready class tools.AuctionCalendar to go to
     * the next day. We also assume that only one auction house can exist in our
     * world.
     *
     * @param d day
     * @param m month
     * @param y year
     */
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
     * @param name The name of the item
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
     * tools.AuctionCalendar class.
     *
     */
    public void advanceDay() {
        AuctionCalendar.nextDay();
        this.auctionHouse.deactivateExpiredAuctions();
    }

    /**
     * Provides a unique id for a newly created user
     *
     * @return An Integer indicating the id of the user
     */
    public static Integer getNextUserId() {
        int size = World.users.size();
        size++;
        return size;
    }

    /**
     * Provides a unique id for a newly created item
     *
     * @return An Integer indicating the id of the item
     */
    public static Integer getNextItemId() {
        int size = World.items.size();
        size++;
        return size;
    }

    /**
     * Returns a list of the users that the world keeps track
     *
     * @return An ArrayList of the users that exist in the world
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Returns an instance of the auction house that exists in the world
     *
     * @return An AuctionHouse instance representing the auction house of the
     * world
     */
    public AuctionHouse getAuctionHouse() {
        return auctionHouse;
    }

    /**
     * This method allows the world to directly give the item to a user once it
     * is created.
     *
     * @param item The item
     * @param user The user that gets the item
     */
    void giveItemToUser(Item item, User user) {
        user.receiveItem(item);
    }

    /**
     * This method uses the private method mail to send an in world mail to a
     * user based on their shipping address. In our world that means that only
     * customers can get mail and that mail is sent when they win an item from
     * the auction house either when an auction expires and they were the
     * highest bidder or when they immediately buy an auction with steady price
     * aka Buyout
     *
     * @param mail The mail to be sent
     * @param address The shipping address of the customer
     */
    public static void mailTo(Mail mail, String address) {
        for (User user : users) {
            if (user instanceof Customer) {
                if (((Customer) user).getShippingAddress().equalsIgnoreCase(address)) {
                    mail(mail, user);
                }
            }

        }

    }

    /**
     * Private method that aids the mailTo method to send the item to the user
     *
     * @param mail the mail to be sent
     * @param user the user we want to send the mail to
     */
    private static void mail(Mail mail, User user) {
        user.receiveMail(mail);
        if (SYSTEM_MESSAGES) {
            System.out.println("++Mail sent to " + user.getUsername());
        }
    }
}
