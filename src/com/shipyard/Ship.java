package com.shipyard;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

/**
* Each ship has capacity that may be different. The capacity has the following parts: 
* - Maximum number of toxic and explosive containers (optional for a ship)
* - Maximum number of refrigerated containers (optional for a ship)
* - Maximum number of heavy containers
* - Maximum number of all containers including basic containers
*/

public class Ship {

    private String name;
    private int allContainers;
    private Map<String, Container[]> cargo;
    private int heavyContainers;
    private int refrigeratedContainers;
    Container[] heavyContents;
    Container[] refContents;
    private boolean explosives;
    private int explosiveContainers;
    private boolean toxics;
    private int toxicContainers;
    private Random random = new Random();

    public Ship(String name, int allContainers, int heavyContainers, int refrigeratedContainers,
                boolean explosives, boolean toxics) {
        this.name = name;
        this.allContainers = allContainers;
        this.heavyContainers = heavyContainers;
        this.refrigeratedContainers = refrigeratedContainers;
        this.explosives = explosives;
        this.toxics = toxics;
        this.heavyContents = new HeavyContainer[heavyContainers];
        this.refContents = new RefrigeratedContainer[refrigeratedContainers];
    }
    
    public Ship() {
        this.name = nameShip();
        this.allContainers = random.nextInt(301) + 100;
        this.heavyContainers = allContainers - random.nextInt(91);
        this.refrigeratedContainers = allContainers - heavyContainers;
        this.explosives = setExplosives();
        this.toxics = setToxics();
        this.heavyContents = new HeavyContainer[heavyContainers];
        this.refContents = new RefrigeratedContainer[refrigeratedContainers];
    }

    private String nameShip() {
        FileHandler fh = new FileHandler();
        return fh.readLine("data/shipnames.csv", random.nextInt(fh.countLines("data/shipnames.csv")));
    }

    private boolean setExplosives() {
        this.explosives = random.nextBoolean();
        return this.explosives;
    }

    private boolean setToxics() {
        this.toxics = random.nextBoolean();
        return this.toxics;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, Container[]> getCargo() {
        return this.cargo;
    }

    public int getAllContainers() {
        return this.allContainers;
    }

    public int getHeavyContainers() {
        return this.heavyContainers;
    }

    public int getRefrigeratedContainers() {
        return this.refrigeratedContainers;
    }

    public int getToxicContainers() {
        if (this.toxics) {
            return this.toxicContainers;
        } else {
            return 0;
        }
    }

    public int getExplosiveContainers() {
        if (this.explosives) {
            return this.explosiveContainers;
        } else {
            return 0;
        }
    }

    public boolean getToxics() {
        return this.toxics;
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public void loadCargo(Container container) throws ShipFullException {
        while (Arrays.asList(this.heavyContents).contains(null)) {
            if (container instanceof HeavyContainer) {
                int j = 0;
                for (int i = 0; i < this.heavyContents.length; i++) {
                    if (this.heavyContents[i] == null) {
                        this.heavyContents[j] = container;
                    }
                    else {
                        j++;
                    }
                }
                
            }
            else if (container instanceof RefrigeratedContainer) {
                int j = 0;
                for (int i = 0; i < this.refContents.length; i++) {
                    if (this.refContents[i] == null) {
                        this.refContents[j] = container;
                    }
                    else {
                        j++;
                    }
                }
            }
        this.cargo = Map.of("Heavy Containers", heavyContents, "Refrigerated Containers", refContents);
        }
        if (!Arrays.asList(this.heavyContents).contains(null)) {
            throw new ShipFullException();
        }
    }

    public String getCurrentStatus() {
        String currentStatus = "";
        currentStatus += "Name: " + this.getName() + "\n";
        return currentStatus;
    }

    @Override
    public String toString() {
        String shipInfo = "";
        shipInfo += "Name: " + this.getName() + "\n"
        + "Total capacity: " + this.getAllContainers() + "\n"
        + "Heavy container capacity: " + this.getHeavyContainers() + "\n"
        + "Refrigerated container capacity: " + this.getRefrigeratedContainers() + "\n";
        if (this.explosives) {
            shipInfo += "Can carry explosives: yes\n";
        }
        else {
            shipInfo += "Can carry explosives: no\n";
        }
        
        if (this.toxics) {
            shipInfo += "Can carry toxic materials: yes\n";
        }
        else {
            shipInfo += "Can carry toxic materials: no\n";
        }
        return shipInfo;
    }

}