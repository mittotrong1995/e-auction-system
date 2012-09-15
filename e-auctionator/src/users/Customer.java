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
    public void makeOffer(Double bid, Auction auction, system.Auctionator auctionHouse) {
        if (auction instanceof Bid) {
            auctionHouse.placeBid(bid, auction, this);
            //((Bid) auction).receiveOffer(bid);

        } else if (auction instanceof Buyout) {
            //auctionHouse.sell(auction, this);
            System.out.println("Προσοχή: Δεν γίνονται προσφορές σε δημοπρασίες σταθερής τιμής");
        }
    }
    
    public void buy(Auction auction, system.Auctionator auctionHouse) {
        if (auction instanceof Buyout) {
            auctionHouse.sell(auction, this);
            
        } else if (auction instanceof Bid) {
            System.out.println("Προσοχή: Δεν γίνεται να αγοραστούν οι δημοπρασίες με ελεύθερη τιμή");
        }
        
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    
    
    
}
