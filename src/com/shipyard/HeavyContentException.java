package com.shipyard;

public class HeavyContentException extends ShipFullException {

    String message = "This ship cannot carry any more heavy containers.";

    @Override
    public String getMessage() {
        return message;
    }
}
