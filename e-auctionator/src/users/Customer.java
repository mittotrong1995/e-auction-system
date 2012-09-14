package users;

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
}
