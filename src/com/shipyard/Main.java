package com.shipyard;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("TESTING");
        System.out.println();

        Docks docks = new Docks("Test Bay", 5, 10, 10);
        System.out.println("Currently docking at: " + docks.getName());
        System.out.println("Number of ships: " + docks.getSize());
        System.out.println("Number of containers awaiting pickup: " + docks.getContainers().size());

        System.out.println();
        System.out.println("Container list:\n");
        for (Container container: docks.getContainers()) {
            System.out.println(container.toString());
        }

        System.out.println();
        System.out.println("Ship list:\n");
        for (int i = 0; i < docks.getShips().length; i++) {
            System.out.println(docks.getShips()[i].toString());

        }
        Ship ship = docks.getShips()[0];

        Iterator<Container> containerIterator = docks.iterator();
        while (containerIterator.hasNext()) {
            Container container = containerIterator.next();
            try {
                ship.loadCargo(container);
                System.out.println("Successfully loaded " + container.toString() + " onto " + ship.getName() + ".");
                containerIterator.remove();

            } catch (HeavyContentException hce) {
                System.out.println(hce.getMessage());
                System.out.println("Moving on...");
                continue;
            } catch (RefrigeratedContentException rce) {
                System.out.println(rce.getMessage());
                System.out.println("Moving on...");
                continue;
            } catch (WrongTypeException wte){
                System.out.println(container.toString() + ": " + wte.getMessage());
                System.out.println("Moving on...");
                continue;
            } catch (ShipFullException sfe) {
                System.out.println(sfe.getMessage());
                break;
            }

        }
        System.out.println();
        System.out.println(ship.getCurrentStatus());
        System.out.println(docks.getContainers());


    }
}

