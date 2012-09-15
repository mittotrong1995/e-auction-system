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

    public Item(String name) {
        this.id = system.World.getNextItemId();
        this.name = name;

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    
}
