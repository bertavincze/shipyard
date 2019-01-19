package com.shipyard;

public class Docks {

    private String name;
    private int size;
    private Ship[] ships;

    public Docks(String name, int size) {
        this.name = name;
        this.size = size; 
        this.ships = createShips(size);
    }

    public Ship[] createShips(int num) {
        Ship[] ships = new Ship[num];
        for (int i = 0; i < ships.length; i++) {
            ships[i] = new Ship();
        }
        return ships; 
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }
    
    public Ship[] getShips() {
        return this.ships;
    }

}