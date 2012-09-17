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

    /**
     * Creates a new customer with a certain username by calling the costructor
     * of the superclass
     *
     * @param username
     */
    public Customer(String username) {
        super(username);
    }

    /**
     * This method allows the customer to make an offer for a certain auction.
     *
     * @param bid The bid price
     * @param auction The auction that the user wants to bid on
     */
    public void makeOffer(Double bid, Auction auction, system.AuctionHouse auctionHouse) {
        if (auction instanceof Bid) {
            auctionHouse.placeBid(bid, auction, this);
            //((Bid) auction).receiveOffer(bid);

        } else if (auction instanceof Buyout) {
            //auctionHouse.sell(auction, this);
            System.out.println("Προσοχή: Δεν γίνονται προσφορές σε δημοπρασίες σταθερής τιμής");
        }
    }

    /**
     * This method allows a customer to "make an offer" on an auction with a
     * steady price. Since those auctions have a steady price though it's more
     * like if the customer is instantly buying the item and thus winning the
     * auction.
     *
     * @param auction The auction that the user wants to buy
     * @param auctionHouse The auction house that the auction is on
     */
    public void buy(Auction auction, system.AuctionHouse auctionHouse) {
        if (auction instanceof Buyout) {
            auctionHouse.sell(auction, this);

        } else if (auction instanceof Bid) {
            System.out.println("Προσοχή: Δεν γίνεται να αγοραστούν οι δημοπρασίες με ελεύθερη τιμή");
        }

    }

    /**
     * Returns the shipping address of the customer
     *
     * @return A String with the shipping address of the customer
     */
    public String getShippingAddress() {
        return shippingAddress;
    }

    /**
     * Allows us to set the shipping address for a customer
     *
     * @param shippingAddress A String with the address we want to set
     */
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
}
