package auctions;

import users.User;

/**
 * This class represents an auction of the buyout type which means that it has a
 * set, standard price and the fastest buyer wins it instantly by offering that
 * standard price. This class extends the Auction class.
 *
 * @author Michaella Neirou
 */
public class Buyout extends Auction {

    Double buyoutPrice;

    /**
     * Calls the constructor of the super class Auction first.
     *
     * @param title The title of the auction.
     * @param item The item we want to auction.
     * @param owner The user creating the auction who is the owner of the item
     * (and the auction).
     * @param buyoutPrice The buyout price is a set price that the user needs to
     * pay in order to win the auction. Buyout auctions stay in the auction
     * house for a certain duration and the faster user that pays this price
     * wins it.
     */
    public Buyout(String title, Item item, User owner, Double buyoutPrice) {
        super(title, item, owner);
        this.buyoutPrice = buyoutPrice;

    }

    /**
     * This method returns the standard buyout price of this auction.
     *
     * @return A Double that indicates the buyout price of this auction.
     */
    public Double getBuyoutPrice() {
        return buyoutPrice;
    }

    /**
     * This method represents the ability of a Buyout type to receive purchases
     * from users. We could as well just keep this code somewhere else like in
     * the user or in the customer but it makes more sense in terms of Object
     * Oriented theory that each Object contains the whole knowledge that it
     * requires to perform the actions that have to do with itself. The method
     * checks if the auction is active and then it instantly sets the user as
     * the winner or else it prints a message that the auction has
     * expired.
     *
     * @param user The user that wants to buy the auction.
     */
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
