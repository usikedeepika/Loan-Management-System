package com.customer.exception;

public class MailException extends RuntimeException{

    public MailException(String message) {
        super(message);
    }

}