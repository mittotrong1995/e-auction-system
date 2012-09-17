package users;

import auctions.Auction;
import auctions.Item;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import system.Mail;

/**
 * This class represents a user in our world who can be either an auctioneer or
 * a buyer.
 *
 * @author Michaella Neirou
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private ArrayList<Auction> auctions;
    private ArrayList<Auction> auctionsWon;
    private ArrayList<Item> bags;
    private ArrayList<Mail> mailbox;

    /**
     * This basic and lightweight constructor creates a new user with a random
     * password thus we only need to provide his username. More constructors can
     * be made that allow us to instantly enter more information.
     *
     * @param username
     */
    public User(String username) {
        this.id = system.World.getNextUserId();
        this.username = username;
        this.password = generateRandomPassword();
        this.auctions = new ArrayList<>();
        this.auctionsWon = new ArrayList<>();
        this.bags = new ArrayList<>();
        this.mailbox = new ArrayList<>();

    }

    /**
     * Prints several values of a user. This method was used while testing the
     * program. It is not required by the exercise and it could be deleted.
     */
    public void whoAmI() {
        System.out.println("----- User Profile -----");
        System.out.println("| id: " + this.getId());
        System.out.println("| username: " + this.getUsername());
        //System.out.println("| password: " + this.getPassword());
        System.out.println("| Type: " + this.getType("en"));
        System.out.println("------------------------");

    }

    /**
     * This method generates a random password for the user
     *
     * @return A String with the password
     */
    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        String str = new BigInteger(130, random).toString(32);
        return str;
    }

    /**
     * Returns the id of the user
     *
     * @return An Integer indicating the id of the user
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the username of the user
     *
     * @return A String holding the username
     */
    public String getUsername() {
        return username;
    }

    /*
     * This private method returns the password for in class use. It's debatable
     * if such a method should even exist for security reasons
     *
     * @return
     *
     private String getPassword() {
     return password;
     }*/
    /**
     * This method returns the type of the user. The argument provides the
     * language the type will be returned to.
     *
     * @param lang "en" for English, "gr" for Greek
     * @return A String containing the type of the user in the appropriate
     * language
     */
    public String getType(String lang) {
        String str = "";
        if (lang.equalsIgnoreCase("en")) {
            if (this instanceof Auctioneer) {
                str = "Auctioneer";
            } else if (this instanceof Customer) {
                str = "Customer";
            } else {
                str = "User";
            }
        } else if (lang.equalsIgnoreCase("gr")) {
            if (this instanceof Auctioneer) {
                str = "Δημοπράτης";
            } else if (this instanceof Customer) {
                str = "Αγοραστής";
            } else {
                str = "Χρήστης";
            }
        }

        return str;
    }

    /**
     * Allows the user to receive an item which goes to his bags
     *
     * @param item The item receiving
     */
    public void receiveItem(Item item) {
        bags.add(item);
    }

    /**
     * Allows a user to recieve a mail which goes to his mailbox
     *
     * @param mail The mail receiving
     */
    public void receiveMail(Mail mail) {
        mailbox.add(mail);
    }

    /**
     * Allows a user to open a mail. If the mail has an attachment it then goes
     * to his bags while also providing a system message indicating that the
     * user indeed received the item
     */
    public void openMail() {
        Mail mail = mailbox.remove(0);
        System.out.println(mail.getMessage());
        if (mail.hasAttachment()) {
            Item i = mail.getAttachment();
            bags.add(i);
            System.out.println("++User " + this.getUsername() + " received item " + i.getName());
        }
    }

    /**
     * This method allows the user to check his mailbox for a mail. If the
     * mailbox is not empty then he opens the first mail
     */
    public void checkMail() {
        if (!mailbox.isEmpty()) {
            openMail();
        }
    }

    /**
     * This method allows the user to get the first item he finds in his bags
     * list
     *
     * @return An instance of the Item that user gets
     */
    public Item getItemFromBags() {
        Item i = null;
        if (bags.size() != 0) {
            i = bags.remove(0);
        } else {
            System.out.println("Warning(" + this.getUsername() + "): No more items in bags!");
        }
        return i;
    }

    /**
     * Returns the all the auction that a user has taken part into
     *
     * @return An ArrayList with the auctions
     */
    public ArrayList<Auction> getAuctions() {
        return this.auctions;
    }

    /**
     * Allows us to add an auction to a user when he has taken part in it.
     *
     * @param auction The instance of an Auction to add.
     */
    public void addAuction(Auction auction) {
        this.auctions.add(auction);
    }

    /**
     * Allows us to add won auction in the seperate auctionsWon list.
     *
     * @param auction An instance of the won Auction.
     */
    public void addWonAuction(Auction auction) {
        this.auctionsWon.add(auction);
    }

    /**
     * Returns a number of all the auctions of a user
     *
     * @return An int number of all the auctions
     */
    public int getAuctionsNumber() {
        return this.auctions.size();
    }

    /**
     * Returns a number of all the active auctions of the user
     *
     * @return An int number of the active auctions
     */
    public int getActiveAuctionsNumber() {
        int i = 0;
        for (Auction auction : auctions) {
            if (auction.isActive()) {
                i++;
            }
        }
        return i;
    }

    /**
     * Returns the list that indicates the auctions won by a user
     *
     * @return An ArrayList list of all the auctions won by that user
     */
    public ArrayList<Auction> getAuctionsWon() {
        return auctionsWon;
    }
}
