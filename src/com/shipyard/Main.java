package com.shipyard;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
            System.out.println("TESTING");
            System.out.println();
            
            Docks docks = new Docks("Test Bay", 20);
            System.out.println(docks.getName());
            System.out.println();
            for (Ship ship : docks.getShips()) {
                System.out.println(ship.toString()); 
            }

    }
}

