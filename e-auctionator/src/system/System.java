package system;

import auctions.Auction;
import auctions.Item;
import users.Auctioneer;
import users.User;

/**
 * This class represents the whole auction system. It will use all the other
 * classes and provide functions that allows us to make auctions, place bids,
 * create users, etc.
 *
 * @author Michaella Neirou
 */
public class System {

    /**
     * This method creates a new user with a random password.
     *
     * @param name
     * @param type
     * @return
     */
    public User createUser(String name, String type) {
        return null;

    }

    /**
     * This method creates a new auction.
     *
     * @param title
     * @param description
     * @param item
     * @param user
     * @return
     */
    public Auction createAuction(String title, String description, Item item, User user) {
        return null;
    }

    /**
     * This method goes to the next day with the aid of the provided
     * AuctionCalendar class.
     *
     */
    public void advanceDay() {
    }

    /**
     * This method allows to place a bid for a certain auction.
     *
     * @param bid The bid price
     * @param id The id of the auction
     */
    public void placeBid(Double bid, Integer id) {
    }

    /**
     * This method shows us all the active auctions of a user. The active
     * auctions of an auctioneer can't be more than 10. A buyer doesn't have a
     * limit.
     *
     * @param user
     */
    public void printAuctions(User user) {
    }

    /**
     * This method shows the auctions that an auctioneer has won.
     *
     * @param a
     */
    public void printWonAuctions(Auctioneer a) {
    }
}
