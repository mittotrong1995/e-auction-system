/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import auctions.Auction;
import auctions.Buyout;
import auctions.Item;
import java.util.ArrayList;
import system.Auctionator;

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
    
    public Auctioneer(String username) {
        super(username);
        
    }

    public ArrayList<Auction> getAuctionsWon() {
        return auctionsWon;
    }
    
    public Auction auctionItem(Item item, String type, Double price, Auctionator auctionHouse) {
        return auctionHouse.createAuction(item.getName(), type, item, this, price);
        
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }
    
    
    
    
}
