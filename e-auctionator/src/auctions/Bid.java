package auctions;

import users.User;

/**
 * This class represents an auction of the bid type which means that there is a
 * starting price called bid and each buyer makes offers. The highest offer when
 * the auction expires wins the bid auction. This class extends the Auction
 * class.
 *
 * @author Michaella Neirou
 */
public class Bid extends Auction {

    private final Double startingBid;
    private Double currentBid;
    private User currentHighestBidder;

    /**
     * Calls the constructor of the super class Auction first.
     *
     * @param title The title of the auction.
     * @param item The item we want to auction.
     * @param owner The user creating the auction who is the owner of the item
     * (and the auction).
     * @param bidPrice The initial bid that we want our auction to start with.
     * This value is kept in the final value startingBid and is not altered
     * again.
     */
    public Bid(String title, Item item, User owner, Double bidPrice) {
        super(title, item, owner);
        this.startingBid = bidPrice;
        this.currentBid = bidPrice;
    }

    /**
     * Returns the current bid of the auction which is also the highest bid of
     * the offers
     *
     * @return A Double indicating the current bid
     */
    public Double getCurrentBid() {
        return currentBid;
    }

    /**
     * This method represents the ability of a Bid type to receive offers from
     * users. We could as well just keep this code somewhere else like in the
     * user or in the customer but it makes more sense in terms of Object
     * Oriented theory that each Object contains the whole knowledge that it
     * requires to perform the actions that have to do with itself. The method
     * checks if the current offer is bigger than what it was previously holding
     * and if it is it sets the new higher bid as the current bid.
     *
     * @param bid A Double indicating the bid we want to set as the current bid
     * which is what the user is offering
     * @param user The user that makes the offer
     */
    public void receiveOffer(Double bid, User user) {
        if (this.isActive()) {
            String msg = "Ο χρήστης " + user.getUsername() + " έκανε προσφορά " + bid
                    + " ευρώ για το " + getTitle();
            System.out.println(msg);
            if (bid > this.currentBid) {
                this.currentBid = bid;
                this.currentHighestBidder = user;
                System.out.println("Η προσφορά είναι καλύτερη από την τρέχουσα!");
            } else {
                System.out.println("Η προσφορά είναι χειρότερη από την τρέχουσα!");
            }
        } else {
            String msg = "Ο χρήστης " + user.getUsername() + " έκανε προσφορά "
                    + " για το " + getTitle() + "\n";
            msg += "Η προσφορά είναι εκπρόθεσμη";
            System.out.println(msg);
        }
    }

    /**
     * Returns the user that is the current highest bidder for this auction.
     *
     * @return A User type indicating the highest bidder
     */
    public User getCurrentHighestBidder() {
        return currentHighestBidder;
    }
}
