package com.shipyard;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
            System.out.println("TESTING");
            System.out.println();
            
            Docks docks = new Docks("Test Bay", 1);
            System.out.println(docks.getName());
            System.out.println();
            for (Ship ship : docks.getShips()) {
                System.out.println(ship.toString());
            }
            
            Container[] containers = new Container[2];
            for (int i = 0; i < containers.length; i++) {
                containers[i] = docks.generateContainers();
            }

            System.out.println(Arrays.toString(containers));
            Ship ship = new Ship("Da Lulz", 50, 25, 5, true, true);
            System.out.println(ship.toString());

           
            for (int i = 0; i < containers.length; i++) {
                try {
                    ship.loadCargo(containers[i]);  
                } catch (ShipFullException e) {
                    System.out.println("Ship is full!");
                }
            }

            System.out.println(Arrays.toString(ship.heavyContents));

            

            

    }
}

