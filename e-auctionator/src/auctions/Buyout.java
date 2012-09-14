package auctions;

import users.User;

/**
 * This class represents an auction of the buyout type which means that it has a
 * set standard price and the fastest buyer wins it instantly by offering that
 * standard price.
 *
 * @author Michaella Neirou
 */
public class Buyout extends Auction {

    Double buyoutPrice;

    public Buyout(String title, Item item, User owner, Double buyoutPrice) {
        super(title, item, owner);
        this.buyoutPrice = buyoutPrice;

    }

    public Double getBuyoutPrice() {
        return buyoutPrice;
    }
    
    
}
