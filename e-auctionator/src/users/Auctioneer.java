/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package users;

import auctions.Auction;
import auctions.Buyout;
import auctions.Item;
import java.util.ArrayList;
import system.AuctionHouse;

/**
 * Αυτή η κλαση αναπαριστά τους δημοπράτες και είναι υποκλάση της γενικότερης
 * κλάσης που αναπαριστά ένα χρήστη.
 *
 * @author Michaella Neirou
 */
public class Auctioneer extends User {

    private final int MAX_AUCTIONS = 10;
    private String afm;
    //ArrayList<Auction> auctionsWon;

    public Auctioneer(String username) {
        super(username);

    }

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
            System.out.println("Warning("+this.getUsername()+"): No item given to auction!");
        }

        return auction;

    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

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
