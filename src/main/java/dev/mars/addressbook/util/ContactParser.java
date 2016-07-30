/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dev.mars.addressbook.util;

import dev.mars.addressbook.model.Contact;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author Gil
 */

// Class to parse the CSV file and extract the contacts
public class ContactParser {

    List<Contact> allContacts = new ArrayList<>();

    public enum Headers {

        NAME, GENDER, DATE_OF_BIRTH
    }

    public List<Contact> parseCSV() throws FileNotFoundException, IOException ,GenderFormatException {
        Reader in = new FileReader("resource/csv");
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader(Headers.class).parse(in);
        for (CSVRecord record : records) {
            Contact contact = new Contact();
             contact.setContactName(record.get(Headers.NAME)); 
             
             // while parsing the csv file, remove all the spaces and convert to a specific character case 
             // for effective comparison.
             if((record.get(Headers.GENDER).trim().toLowerCase().toString()).equals("female")){
                 contact.setContactGenger(ContactGender.FEMALE);
             } 
             else if ((record.get(Headers.GENDER).trim().toLowerCase().toString()).equals("male")){
                 contact.setContactGenger(ContactGender.MALE);
             }
             else 
                 throw new GenderFormatException("The Gender in CSV is not correct");
             
            //contact.setContactGenger();
            String name = record.get(Headers.DATE_OF_BIRTH);
        }
        return null;
    }
}
