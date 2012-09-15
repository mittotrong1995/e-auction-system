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
    
    public void receivePurchase(User user) {
        if (this.isActive()) {
            String msg = "Ο χρήστης " + user.getUsername() + " αγοράζει" 
                    + " το " + getTitle();
            System.out.println(msg);
            this.setWinner(user);
            this.setFinalPrice(buyoutPrice);
        } else {
            String msg = "Ο χρήστης " + user.getUsername() + " έκανε προσφορά" 
                    + " για το " + getTitle() + "\n";
            msg += "Η προσφορά είναι εκπρόθεσμη";
            System.out.println(msg);
        }
    }
    
    
}
