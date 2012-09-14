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

    Double bidPrice;

    public Bid(String title, Item item, User owner, Double bidPrice) {
        super(title, item, owner);
        this.bidPrice = bidPrice;

    }

    public Double getBidPrice() {
        return bidPrice;
    }
    
    
}
