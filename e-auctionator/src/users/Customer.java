package users;

import auctions.Auction;
import auctions.Bid;
import auctions.Buyout;

/**
 * This class represents the type of user that will be a buyer for a certain
 * auction of the system
 *
 * @author Michaella Neirou
 */
public class Customer extends User {

    String shippingAddress;

    public Customer(String username) {
        super(username);
    }

    /**
     * This method allows the customer to place a bid for a certain auction.
     *
     * @param bid The bid price
     * @param auction The auction that the user wants to bid on
     */
    public void makeOffer(Double bid, Auction auction) {
        String msg = "Ο χρήστης " + this.getUsername() + " έκανε προσφορά " + bid 
                + " ευρώ για το " + auction.getTitle();
        System.out.println(msg);
        if (auction instanceof Bid) {
            ((Bid) auction).receiveOffer(bid);

        } else if (auction instanceof Buyout) {
            System.out.println("Προσοχή: Δεν γίνονται προσφορές σε δημοπρασίες σταθερής τιμής");
        }
    }
}
