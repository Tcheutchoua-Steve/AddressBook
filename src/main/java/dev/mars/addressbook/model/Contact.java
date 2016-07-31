/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.mars.addressbook.model;

import dev.mars.addressbook.util.ContactGender;
import dev.mars.addressbook.util.DateUtil;
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

    public String getContactDob() {
        
        return DateUtil.format(contactDob);
        //return contactDob;
    }

    public void setContactDob(Calendar contactDob) {
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
