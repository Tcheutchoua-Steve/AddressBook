/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dev.micromenltd.addressbook.util;

/**
 *
 * @author Gil
 */

// Exception to be fired if the gender does not match that defined or expected
public class GenderFormatException extends Exception{
    public GenderFormatException (String message){
       super(message);
    }

    public GenderFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public GenderFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
