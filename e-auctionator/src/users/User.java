package users;

import auctions.Auction;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;

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

    public User(String username) {
        this.id = system.Auctionator.getNextUserId();
        this.username = username;
        this.password = generateRandomPassword();

    }

    public void whoAmI() {
        System.out.println("----- User Profile -----");
        System.out.println("| id: " + this.getId());
        System.out.println("| username: " + this.getUsername());
        System.out.println("| password: " + this.getPassword());
        System.out.println("| Type: " + this.getType("en"));
        System.out.println("------------------------");

    }

    public String generateRandomPassword() {
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
}
