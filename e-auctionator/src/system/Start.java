package system;

import auctions.Auction;
import auctions.Item;
import users.Auctioneer;
import users.Customer;
import users.User;

/**
 * This class helps the user easily start and test the program. It's a class
 * that just contains a main method and uses all the others to execute possible
 * scenarios.
 *
 * @author Michaella Neirou
 */
public class Start {

    public static void main(String[] args) {

        runDefaultScenario();
        //runScenario1();
    }

    /**
     * This method contains the exact scenario that is provided in e-class from
     * the file output.pdf. The output from this scenario is the same as the one
     * in the pdf file
     */
    public static void runDefaultScenario() {
        World w = new World(25, 2, 2012);
        AuctionHouse ah = w.getAuctionHouse();
        ah.printAuctionDuration();
        System.out.println("");
        User u1 = w.createUser("auctioneer-1", "auctioneer", "234523547");
        User u2 = w.createUser("auctioneer-2", "auctioneer", "634524361");
        System.out.println("");
        User b1 = w.createUser("buyer-1", "customer", "Αθήνα, Κ. Πατήσια");
        User b2 = w.createUser("buyer-2", "customer", "Αθήνα, Ν. Φιλαδέλφεια");
        System.out.println("");
        Item i1 = w.createItem("Ρολόι");
        Item i2 = w.createItem("Αυτοκίνητο");
        Item i3 = w.createItem("Laptop");
        w.giveItemToUser(i1, u1);
        w.giveItemToUser(i2, u1);
        w.giveItemToUser(i3, u1);
        Auction a1 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "buyout", 100.0, ah);
        System.out.println("");
        Auction a2 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "bid", 2200.0, ah);
        System.out.println("");
        w.advanceDay();
        System.out.println("");
        Auction a3 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "buyout", 200.0, ah);
        System.out.println("");
        w.advanceDay();
        System.out.println("");
        ((Customer) b1).makeOffer(2300.0, a2, ah);
        System.out.println("");
        ((Customer) b2).makeOffer(2400.0, a2, ah);
        System.out.println("");
        ((Customer) b1).makeOffer(2350.0, a2, ah);
        System.out.println("");
        w.advanceDay();
        w.advanceDay();
        w.advanceDay();
        System.out.println("");
        ((Customer) b1).buy(a1, ah);
        System.out.println("");
        ah.printAuctions();
    }

    /**
     * Custom made scenario to help test the program further.
     */
    public static void runScenario1() {
        World w = new World(25, 2, 2012);
        AuctionHouse ah = w.getAuctionHouse();
        ah.printAuctionDuration();
        User u1 = w.createUser("greedy-jake", "auctioneer", "234523547");
        User b1 = w.createUser("needy-mike", "customer", "Αθήνα, Κ. Πατήσια");
        User b2 = w.createUser("needy-pukka", "customer", "Αθήνα, Ν. Φιλαδέλφεια");
        Item i1 = w.createItem("Ρολόι");
        Item i2 = w.createItem("Αυτοκίνητο");
        Item i3 = w.createItem("Laptop");
        Item i4 = w.createItem("iPhone");
        w.giveItemToUser(i1, u1);
        w.giveItemToUser(i2, u1);
        w.giveItemToUser(i3, u1);
        w.giveItemToUser(i4, u1);
        Auction a1 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "bid", 100.0, ah);
        w.advanceDay();
        Auction a2 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "bid", 2200.0, ah);
        w.advanceDay();
        Auction a3 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "bid", 200.0, ah);
        w.advanceDay();
        Auction a4 = ((Auctioneer) u1).auctionItem(u1.getItemFromBags(), "buyout", 300.0, ah);
        w.advanceDay();
        ((Auctioneer) u1).showActiveAuctions();
        ah.printAuctions();



        ((Customer) b1).makeOffer(2300.0, a2, ah);
        ((Customer) b2).makeOffer(2400.0, a2, ah);
        ((Customer) b1).makeOffer(2350.0, a2, ah);
        ((Customer) b1).buy(a1, ah);
        ((Customer) b2).buy(a2, ah);
        ((Customer) b1).buy(a3, ah);
        ((Customer) b2).buy(a4, ah);
        ((Customer) b1).makeOffer(2300.0, a3, ah);
        ((Customer) b2).makeOffer(2400.0, a3, ah);
        ((Customer) b1).makeOffer(2350.0, a3, ah);
        w.advanceDay();
        ((Auctioneer) u1).showActiveAuctions();
        ah.printAuctions();


        ((Auctioneer) u1).showWinners();
    }
}
