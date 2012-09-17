package auctions;

/**
 * This class represents an item that will be auctioned on each auction. One
 * auction can have only one item.
 *
 * @author Michaella Neirou
 */
public class Item {

    private Integer id;
    private String name;

    /**
     * The constructor gives a unique id to it's item with the help of the World
     * class that keeps a list of all the Items that exist in the system.
     *
     * @param name
     */
    public Item(String name) {
        this.id = system.World.getNextItemId();
        this.name = name;

    }

    /**
     * Returns the id of that item.
     *
     * @return An Integer indicating the id of the item.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Returns the name of the item.
     *
     * @return A String indicating the name of the item.
     */
    public String getName() {
        return name;
    }
}
