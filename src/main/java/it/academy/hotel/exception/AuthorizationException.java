package it.academy.hotel.exception;

import javax.naming.AuthenticationException;

public class AuthorizationException extends AuthenticationException {
    public AuthorizationException (){

    }
    public AuthorizationException(String message){
        super(message);
    }
}
