package com.company.exp;

public class PhoneNotFoundException extends RuntimeException{
    public PhoneNotFoundException(String message) {
        super(message);
    }
}
