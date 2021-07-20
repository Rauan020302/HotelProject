package it.academy.hotel.exception;

public class ContactException extends NumberFormatException{
    public ContactException() {

    }
    public ContactException(String message) {
        super(message);
    }
}
