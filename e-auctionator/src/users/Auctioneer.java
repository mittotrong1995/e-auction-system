/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import auctions.Auction;
import java.util.ArrayList;

/**
 * Αυτή η κλαση αναπαριστά τους δημοπράτες και είναι υποκλάση της γενικότερης
 * κλάσης που αναπαριστά ένα χρήστη.
 *
 * @author Michaella Neirou
 */
public class Auctioneer extends User {

    private final int MAX_AUCTIONS = 10;
    private String afm;
    ArrayList<Auction> auctionsWon;

    public ArrayList<Auction> getAuctionsWon() {
        return auctionsWon;
    }
}
