package com.shipyard;

public class WrongTypeException extends Exception {

    String message = "This type of container is not allowed on this ship.";

    @Override
    public String getMessage() {
        return message;
    }
}
