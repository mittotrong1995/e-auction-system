/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import auctions.Auction;
import users.User;

/**
 *
 * @author Stratos
 */
public class Start {
    public static void main(String[] args) {
        Auctionator a = new Auctionator();
        a.printAuctionDuration();
        User u1 = a.createUser("auctioneer-1", "auctioneer");
        User u2 = a.createUser("auctioneer-2", "auctioneer");
        User u3 = a.createUser("buyer-1", "customer");
        User u4 = a.createUser("buyer-2", "customer");
        Auction a1 = a.createAuction("Ρολόι", "buyout", a.createItem("clock"), u1, 100.0);
        a1.printAuctionStats();
        Auction a2 = a.createAuction("Αυτοκίνητο", "bid", a.createItem("car"), u1, 2200.0);
        a2.printAuctionStats();
        
        
        
    }
    
}
