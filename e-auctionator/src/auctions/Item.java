package auctions;

/**
 * This class represents an item that will be auctioned on each auction. One
 * auction can have only one item.
 *
 * @author Michaella Neirou
 */
public class Item {

    Integer id;
    String name;

    public Item(String name) {
        this.id = system.Auctionator.getNextItemId();
        this.name = name;

    }
}
