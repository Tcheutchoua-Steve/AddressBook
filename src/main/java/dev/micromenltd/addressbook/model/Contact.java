/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.micromenltd.addressbook.model;

import dev.micromenltd.addressbook.util.ContactGender;
import dev.micromenltd.addressbook.util.DateUtil;
import java.util.Calendar;

/**
 *
 * @author Gil
 */

// defines a standard contact to be managed
public class Contact {
    private String contactName ;
    private ContactGender contactGenger ;
    private Calendar contactDob ;

    public Contact() {
    }

    public Contact(String contactName, ContactGender contactGenger, Calendar contactDob) {
        this.contactName = contactName;
        this.contactGenger = contactGenger;
        this.contactDob = contactDob;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public ContactGender getContactGenger() {
        return contactGenger;
    }

    public void setContactGenger(ContactGender contactGenger) {
        this.contactGenger = contactGenger;
    }

    // This will be used by the table view 
    public String getContactDob() {
        
        return DateUtil.format(contactDob);
        //return contactDob;
    }
    
    // Calendar Object that will be used for mathematical operations
    public Calendar getContactDobInCalendar(){
        return this.contactDob;
    }

    public void setContactDob(Calendar contactDob) {
        this.contactDob = contactDob;
    }
    
}
