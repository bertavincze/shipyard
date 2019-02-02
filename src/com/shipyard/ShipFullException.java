package com.shipyard;

/**
 * Exception which occurs in case a ship is full
 * and no more containers may be loaded onto it.
 */

public class ShipFullException extends Exception {

    String message = "This ship is already full.";

    @Override
    public String getMessage() {
        return message;
    }

}