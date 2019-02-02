package com.shipyard;

public class RefrigeratedContentException extends ShipFullException{

    String message = "This ship cannot carry any more refrigerated containers.";

    @Override
    public String getMessage() {
        return message;
    }
}
