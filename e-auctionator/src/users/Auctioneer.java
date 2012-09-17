package users;

import auctions.Auction;
import auctions.Item;
import java.util.ArrayList;
import system.AuctionHouse;

/**
 * This class represents the auctioneers which is a subclass of a user. An
 * auctioneer can only have 10 active auctions and cannot buy items only sell on
 * the auction house according to the exercise.
 *
 *
 * @author Michaella Neirou
 */
public class Auctioneer extends User {

    private final int MAX_AUCTIONS = 10;
    private String afm;
    //ArrayList<Auction> auctionsWon;

    /**
     * The constructor creates a new user calling the constructor of the super
     * class with a certain username
     *
     * @param username The username of the user
     */
    public Auctioneer(String username) {
        super(username);

    }

    /**
     * This method allows an auctioneer user to auction an item in the auction
     * house.
     *
     * @param item The item to be auctioned
     * @param type The type of the auction. Valid strings are "Buyout" and "Bid"
     * @param price The price the user wants to sell the item for
     * @param auctionHouse The instance of the auction house that the user wants
     * create his auction on
     * @return The instance of an Auction
     */
    public Auction auctionItem(Item item, String type, Double price, AuctionHouse auctionHouse) {
        Auction auction = null;
        if (item != null) {
            if (this.getActiveAuctionsNumber() < this.MAX_AUCTIONS) {
                auction = auctionHouse.createAuction(item.getName(), type, item, this, price);
                this.addAuction(auction);
            } else {
                System.out.println("Προσοχή: Ένας δημοπράτης δεν μπορεί να έχει παραπάνω από " + this.MAX_AUCTIONS + " ενεργές δημοπρασίες");
            }
        } else {
            System.out.println("Warning(" + this.getUsername() + "): No item given to auction!");
        }

        return auction;

    }

    /**
     * This method allows us to set the afm of an auctioneer user
     *
     * @param afm
     */
    public void setAfm(String afm) {
        this.afm = afm;
    }

    /**
     * This method prints all the active auctions of a user. This method should
     * normally be put on the User class and it should work for both types of
     * users but since the exercise specifically asks for just the active
     * auctions of an auctioneer then it's placed here instead.
     */
    public void showActiveAuctions() {
        ArrayList<Auction> auctions = this.getAuctions();
        String line = "--------------------------------------------------";
        String str = "";
        str += line + "\n";
        str += "| Ενεργές δημοπρασίες του χρήστη " + this.getUsername() + "\n";
        str += line;
        System.out.println(str);
        for (Auction auction : auctions) {
            if (auction.isActive()) {
                auction.show();
            }
        }
        System.out.println(line);


    }

    /**
     * Prints the all winners of auctions created by an auctioneer.
     */
    public void showWinners() {
        ArrayList<Auction> auctions = this.getAuctions();
        String line = "--------------------------------------------------";
        String str = "";
        str += line + "\n";
        str += "| Νικητές δημοπρασιών του χρήστη " + this.getUsername() + "\n";
        str += line;
        System.out.println(str);
        for (Auction auction : auctions) {
            if (auction.hasWinner()) {
                auction.show();
            }
        }
        System.out.println(line);
    }
}
