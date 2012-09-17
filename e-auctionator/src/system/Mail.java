package system;

import auctions.Item;

/**
 * This is a simple class that represents a mail in our program. A mail in our
 * world only has a message and an attachment that is an Item from an auction
 *
 * @author Michaella Neirou
 */
public class Mail {

    private String message;
    private Item attachment;

    /**
     * Creates a mail with a message provided in the argument
     *
     * @param msg The message we want our mail to have
     */
    public Mail(String msg) {
        this.message = msg;
    }

    /**
     * This method is used to set the item field of the mail to an item.
     * 
     * @param item The item we want attached.
     */
    public void attach(Item item) {
        this.attachment = item;
    }

    /**
     * This method returns the message of a mail.
     * 
     * @return A String of the message.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Checks to see if the mail an item attached
     * 
     * @return true or false depending if it has an item
     */
    public boolean hasAttachment() {
        boolean b = false;
        if (this.attachment != null) {
            b = true;
        }
        return b;
    }

    /**
     * Returns the item attached to the mail.
     * 
     * @return An item of Item type.
     */
    public Item getAttachment() {
        return attachment;
    }
}
