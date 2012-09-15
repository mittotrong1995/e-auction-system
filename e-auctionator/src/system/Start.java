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
        Auctionator ah = new Auctionator(25, 2, 2012);
        ah.printAuctionDuration();
        User a1 = ah.createUser("auctioneer-1", "auctioneer");
        User a2 = ah.createUser("auctioneer-2", "auctioneer");
        User b1 = ah.createUser("buyer-1", "customer");
        User b2 = ah.createUser("buyer-2", "customer");
        Auction i1 = ah.createAuction("Ρολόι", "buyout", ah.createItem("clock"), a1, 100.0);
        //a1.printAuctionStats();
        Auction i2 = ah.createAuction("Αυτοκίνητο", "bid", ah.createItem("car"), a1, 2200.0);
        ah.advanceDay();
        Auction i3 = ah.createAuction("Laptop", "buyout", ah.createItem("laptop"), a1, 200.0);
        //a3.printAuctionStats();
        ah.advanceDay();
        ah.placeBid(2300.0, i2, b1);
        ah.placeBid(2400.0, i2, b2);
        ah.placeBid(2350.0, i2, b1);
        //i2.printAuctionStats();
        ah.advanceDay();
        ah.advanceDay();
        ah.advanceDay();
        
        
        
    }
    
}
