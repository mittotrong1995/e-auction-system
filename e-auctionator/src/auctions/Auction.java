package auctions;

import users.User;

/**
 * This class represents an Auction in the system.
 *
 * @author Michaella Neirou
 */
public class Auction {

    private Integer id;
    private User owner;
    private Item item;
    private String title;
    private String description;
    private Integer duration;
    private Double finalPrice;
    private User winner;
    private boolean active;
}
