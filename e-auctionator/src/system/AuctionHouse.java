package system;

import auctions.Auction;
import auctions.Bid;
import auctions.Buyout;
import auctions.Item;
import java.util.ArrayList;
import java.util.Date;
import tools.AuctionCalendar;
import users.Auctioneer;
import users.Customer;
import users.User;

/**
 * This class represents the auction house that handles everything that have to
 * do with auctions. Originally this class represented the whole system, it kept
 * lists of the users and items as well but those were later moved to the World
 * class. Now this class only keeps a list of all the auctions. The split
 * between this class and World was first to divide the load to different
 * classes then also because it makes more sense realistically and lastly
 * because it gives the program better architecture that can aid in a possible
 * future expansion of it (ex. We have many auction houses and not just one)
 *
 * @author Michaella Neirou
 */
public class AuctionHouse {

    //private static ArrayList<User> users;
    private static ArrayList<Auction> auctions;
    //private static ArrayList<Item> items;
    //private static Date date;

    /**
     * The constructor merely initializes the list of the auctions that this
     * class keeps track.
     */
    public AuctionHouse() {
        AuctionHouse.auctions = new ArrayList<>();
    }

    /**
     * Gets the global duration value of each auction from the Auction class and
     * prints a message about it.
     */
    public void printAuctionDuration() {
        Integer duration = Auction.getDuration();
        if (duration == 1) {
            System.out.println("Η διάρκεια των δημοπρασιών είναι 1 ημέρα");
        } else {
            System.out.println("Η διάρκεια των δημοπρασιών είναι " + duration + " ημέρες");
        }

    }

    /**
     * This method uses the ready class tool.AuctionCalendar that was provided
     * to aid this exercise, to check if an auction has surpassed it's allowed
     * duration in days. In that case it expires the auction which can also mean
     * that it can have a winner if someone did an offer while it was active. In
     * case we have a winner the method ships the item to the Customer using
     * their shipping address and not directly.
     */
    public void deactivateExpiredAuctions() {
        for (Auction a : this.getAuctions()) {
            Date d = a.getCreationDate();
            Integer days = a.getDuration();
            boolean expired = AuctionCalendar.isCurrentDateNDaysAfter(d, days - 1);
            if (expired && a.isActive()) {
                a.expire();
                if (a.hasWinner()) {
                    ship(a.getItem(), a.getWinner());
                }
            }
        }
    }

    /**
     * This method is not required by the exercise and we could as well not
     * include it. It creates a "mail" with a winning message. It attaches the
     * item to it and mails it to the user using his/her shipping address.
     * (Notice that when we say mail we do not mean an actual real mail!). We
     * could as well directly give the item to the user (or not take any action
     * at all even ) but this way is more realistic and fun!
     *
     * @param item
     * @param user
     */
    public void ship(Item item, User user) {
        if (user instanceof Customer) {
            String msg = "";
            msg += "*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-" + "\n";
            msg += "| Αγαπητέ χρήστη " + user.getUsername() + "," + "\n";
            msg += "| \n";
            msg += "| Κερδίσατε την δημοπρασία για " + "\n";
            msg += "| το αντικείμενο " + item.getName() + " το οποίο " + "\n";
            msg += "| σας αποστέλεται στην διεύθυνσή σας" + "\n";
            msg += "| \n";
            msg += "| Συγχαρητήρια," + "\n";
            msg += "| Το ηλεκτρονικό σύστημα δημοπρασιών" + "\n";
            msg += "*~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-~-" + "\n";
            Mail mail = new Mail(msg);
            mail.attach(item);
            World.mailTo(mail, ((Customer) user).getShippingAddress());
        }

    }

    /**
     * This method creates a new auction in the Auction house. This gives us two
     * ways of creating auctions. 1) By creating using this method and creating
     * a custom auction with no limits 2) By allowing a user (Auctioneer) to
     * create an auction using the method Auctioneer.auctionItem which is more
     * realistic and better in terms of Object Oriented(OO) theory since a user
     * uses the auction house to create the auction but he knows how to perforn
     * the task of creating an auction himself. We use the 2nd way on our
     * scenarios in Start.main
     *
     * @param title The title of the auction
     * @param type A String indicating the type of the auction. Valid strings
     * are "Buyout" and "Bid"
     * @param item The item we want auctioned
     * @param user The user that makes the auction.
     * @param price The price of the auction which is a bid or buyout price
     * depending on the type.
     *
     * @return Returns the created auction.
     */
    public Auction createAuction(String title, String type, Item item, User user, Double price) {
        Auction a = null;
        if (type.equalsIgnoreCase("buyout")) {
            a = new Buyout(title, item, user, price);
            AuctionHouse.auctions.add(a);
            System.out.println("Νέα δημοπρασία για το: " + a.getTitle());
            System.out.println("Τύπος: " + a.getType("gr") + " (" + price + " ευρώ)");
            System.out.println("Η δημοπρασία " + a.getTitle() + " ανήκει στο χρήστη: " + user.getUsername());

        } else if (type.equalsIgnoreCase("bid")) {
            a = new Bid(title, item, user, price);
            AuctionHouse.auctions.add(a);
            System.out.println("Νέα δημοπρασία για το: " + a.getTitle());
            System.out.println("Τύπος: " + a.getType("gr") + " (αρχική τιμή " + price + " ευρώ)");
            System.out.println("Η δημοπρασία " + a.getTitle() + " ανήκει στο χρήστη: " + user.getUsername());

        } else {
        }
        return a;
    }

    /**
     * This method allows the customer to place a bid for a certain auction of
     * type Bid. The exercise assumes that only customers can place bids. This
     * method is supposed to be used only for Bid auctions.
     *
     * @param bid The bid price
     * @param auction The auction that the user wants to bid on
     * @param user The user
     */
    public void placeBid(Double bid, Auction auction, User user) {
        if (user instanceof Customer) {
            ((Bid) auction).receiveOffer(bid, user);
            //((Customer) user).makeOffer(bid, auction);
        } else if (user instanceof Auctioneer) {
            System.out.println("Προσοχή: Μονάχα οι πελάτες μπορούν να κάνουν προσφορές.");

        }
    }

    /**
     * This method is used sell auctions of type Buyout. It makes no sense to
     * make offers for them so we provided a separate method. This method is
     * supposed to be used only for Buyout auctions.
     *
     * @param auction The auction being won
     * @param user The user the makes the offer/buys the buyout auction.
     */
    public void sell(Auction auction, User user) {
        if (user instanceof Customer) {
            ((Buyout) auction).receivePurchase(user);
            auction.close();
            user.addWonAuction(auction);
            ship(auction.getItem(), auction.getWinner());
            //((Customer) user).makeOffer(bid, auction);
        } else if (user instanceof Auctioneer) {
            System.out.println("Προσοχή: Μονάχα οι πελάτες μπορούν να αγοράσουν.");
        }
    }

    /**
     * This method provides a unique id by checking it's current list of
     * auctions. We use this everytime we want to create a new auction to make
     * sure the id is unique
     *
     * @return An Integer indicating a unique id for the auction.
     */
    public static Integer getNextAuctionId() {
        int size = AuctionHouse.auctions.size();
        size++;
        return size;
    }

    /**
     * Prints all the auctions that the auction house is tracking
     */
    public void printAuctions() {
        String str = "";
        for (Auction auction : auctions) {
            auction.print();
        }
    }

    /**
     * Returns a list of the auctions tracked by this auction house.
     *
     * @return An ArrayList indicating the list of the auctions of this auctin
     * house
     */
    public ArrayList<Auction> getAuctions() {
        return auctions;
    }
}
