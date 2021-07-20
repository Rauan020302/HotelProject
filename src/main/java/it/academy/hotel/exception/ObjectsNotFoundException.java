package it.academy.hotel.exception;

public class ObjectsNotFoundException extends RuntimeException{
    public ObjectsNotFoundException() {

    }
    public ObjectsNotFoundException(String text) {
        super(text);
    }
    public ObjectsNotFoundException(String text,Long id){
        super(text + id);
    }
}
