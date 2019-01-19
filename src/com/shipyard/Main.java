package com.shipyard;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
            System.out.println("TESTING");
            
            Docks docks = new Docks();
            docks.createShips();
            for (Ship ship : docks.getShips()) {
                System.out.println(ship.toString()); 
            }

    }
}

