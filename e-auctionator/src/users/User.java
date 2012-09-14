
package users;

import auctions.Auction;
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
    
}
