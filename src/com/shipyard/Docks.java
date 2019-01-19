package com.shipyard;

public class Docks {

    private Ship[] ships;

    public Docks() {
        this.ships = createShips();
    }

    public Ship[] createShips() {
        Ship[] ships = new Ship[10];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship();
        }
        return ships; 
    }

    public Ship[] getShips() {
        return this.ships;
    }

}