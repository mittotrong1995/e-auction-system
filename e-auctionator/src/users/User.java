package users;

import auctions.Auction;
import auctions.Item;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import system.Mail;

/**
 *
 * Αυτή η κλάση αναπαριστά τον γενικό χρήστη του συστήματος που μπορεί να είναι
 * είτε αγοραστής είτε δημοπράτης.
 *
 * @author Michaella Neirou
 */
public class User {

    private Integer id;
    private String username;
    private String password;
    private ArrayList<Auction> auctions;
    private ArrayList<Item> bags;
    private ArrayList<Mail> mailbox;

    public User(String username) {
        this.id = system.World.getNextUserId();
        this.username = username;
        this.password = generateRandomPassword();
        this.auctions = new ArrayList<>();
        this.bags = new ArrayList<>();
        this.mailbox = new ArrayList<>();

    }

    public void whoAmI() {
        System.out.println("----- User Profile -----");
        System.out.println("| id: " + this.getId());
        System.out.println("| username: " + this.getUsername());
        System.out.println("| password: " + this.getPassword());
        System.out.println("| Type: " + this.getType("en"));
        System.out.println("------------------------");

    }

    private String generateRandomPassword() {
        SecureRandom random = new SecureRandom();
        String str = new BigInteger(130, random).toString(32);
        return str;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    

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

    public void receiveItem(Item item) {
        bags.add(item);
    }
    
    public void receiveMail(Mail mail) {
        mailbox.add(mail);        
    }
    
    public void openMail() {
        Mail mail = mailbox.remove(0);
        System.out.println(mail.getMessage());
        if(mail.hasAttachment()) {
            Item i = mail.getAttachment();
            bags.add(i);
            System.out.println("++User " + this.getUsername() + " received item " + i.getName());
        }
    }
    
    public void checkMail() {
        if(!mailbox.isEmpty()) {
            openMail();
        }
    }
    
    public Item getItemFromBags() {
        return bags.remove(0);
    }
}
