/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package system;

import auctions.Item;

/**
 *
 * @author Stratos
 */
public class Mail {
    private String message;
    private Item attachment;
    
    public Mail(String msg) {
        this.message = msg;
    }
    
    public void attach(Item item) {
        this.attachment = item;
    }

    public String getMessage() {
        return message;
    }
    
    public boolean hasAttachment() {
        boolean b = false;
        if(this.attachment != null) {
            b = true;
        }
        return b;
    }

    public Item getAttachment() {
        return attachment;
    }
    
    
    
    
}
