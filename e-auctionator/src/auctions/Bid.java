package auctions;

import users.User;

/**
 * This class represents an auction of the bid type which means that there is a
 * starting price called bid and each buyer makes offers. The highest offer when
 * the auction expires wins the bid auction
 *
 * @author Michaella Neirou
 */
public class Bid extends Auction {

    Double startingBid;
    Double currentBid;

    public Bid(String title, Item item, User owner, Double bidPrice) {
        super(title, item, owner);
        this.startingBid = bidPrice;
        this.currentBid = bidPrice;

    }

    public Double getCurrentBid() {
        return currentBid;
    }
    
    public void receiveOffer(Double bid) {
        if (bid > this.currentBid) {
            this.currentBid = bid;
            System.out.println("Η προσφορά είναι καλύτερη από την τρέχουσα!");
        } else {
            System.out.println("Η προσφορά είναι χειρότερη από την τρέχουσα!");
        }
        
    }
    
    
}
