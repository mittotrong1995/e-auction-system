/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import auctions.Auction;
import auctions.Item;
import users.Auctioneer;
import users.Customer;
import users.User;

/**
 *
 * @author Stratos
 */
public class Start {
    public static void main(String[] args) {
        World w = new World(25, 2, 2012);
        Auctionator ah = w.getAuctionHouse();
        ah.printAuctionDuration();
        User u1 = w.createUser("auctioneer-1", "auctioneer", "234523547");
        User u2 = w.createUser("auctioneer-2", "auctioneer", "634524361");
        User b1 = w.createUser("buyer-1", "customer", "Αθήνα, Κ. Πατήσια");
        User b2 = w.createUser("buyer-2", "customer", "Αθήνα, Ν. Φιλαδέλφεια");
        Item i1 = w.createItem("Ρολόι");
        Item i2 = w.createItem("Αυτοκίνητο");
        Item i3 = w.createItem("Laptop");
        w.giveItemToUser(i1, u1);
        w.giveItemToUser(i2, u1);
        w.giveItemToUser(i3, u1);
        Auction a1 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "buyout", 100.0, ah);
        Auction a2 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "bid", 2200.0, ah);
        Auction a3 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "buyout", 200.0, ah);
        //Auction a1 = ah.createAuction("Ρολόι", "buyout", w.createItem("clock"), u1, 100.0);
        //a1.printAuctionStats();
        //Auction a2 = ah.createAuction("Αυτοκίνητο", "bid", w.createItem("car"), u1, 2200.0);
        w.advanceDay();
        //Auction a3 = ah.createAuction("Laptop", "buyout", w.createItem("laptop"), u1, 200.0);
        //a3.printAuctionStats();
        w.advanceDay();
        ((Customer) b1).makeOffer(2300.0, a2, ah);
        ((Customer) b2).makeOffer(2400.0, a2, ah);
        ((Customer) b1).makeOffer(2350.0, a2, ah);
        //ah.placeBid(2300.0, a2, b1);
        //ah.placeBid(2400.0, a2, b2);
        //ah.placeBid(2350.0, a2, b1);
        //a2.printAuctionStats();
        ((Customer) b1).buy(a1, ah);
        b1.checkMail();
        w.advanceDay();
        
        w.advanceDay();
        
        w.advanceDay();
        b2.checkMail();
        
        
        
        
    }
    
}
