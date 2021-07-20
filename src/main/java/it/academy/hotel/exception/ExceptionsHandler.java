package it.academy.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(value = {ObjectsNotFoundException.class})
    public ResponseEntity<Object> handleApiRequestException(ObjectsNotFoundException e) {
        HttpStatus badRequest = HttpStatus.NOT_FOUND;
        Exception exception = new Exception(
                e.getMessage(),
                badRequest,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception,badRequest);
    }
    @ExceptionHandler(value = {ContactException.class})
    public ResponseEntity<Object> handleApiRequestException(ContactException e){
        HttpStatus badNumber = HttpStatus.LENGTH_REQUIRED;
        Exception exception = new Exception(
                e.getMessage(),
                badNumber,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception,badNumber);
    }
    @ExceptionHandler(value = {AuthorizationException.class})
    public ResponseEntity<Object> handleApiRequestException(AuthorizationException e){
        HttpStatus auth = HttpStatus.FORBIDDEN;
        Exception exception = new Exception(
                e.getMessage(),
                auth,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(exception,auth);
    }
}
