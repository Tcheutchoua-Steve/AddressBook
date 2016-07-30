/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.mars.addressbook.model;

import dev.mars.addressbook.util.ContactGender;
import java.util.Date;

/**
 *
 * @author Gil
 */

// defines a standard contact to be managed
public class Contact {
    private String contactName ;
    private ContactGender contactGenger ;
    private Date contactDob ;

    public Contact() {
    }

    public Contact(String contactName, ContactGender contactGenger, Date contactDob) {
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

    public Date getContactDob() {
        return contactDob;
    }

    public void setContactDob(Date contactDob) {
        this.contactDob = contactDob;
    }

    @Override
    public String toString() {
        //return super.toString(); //To change body of generated methods, choose Tools | Templates.
        return "Name : " + getContactName()+ "\n" +
                "Gender" + getContactGenger() + "\n" + 
                "Date of Birth"  ;
    }
    
    
    
}
