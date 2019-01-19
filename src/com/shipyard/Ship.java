package com.shipyard;

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
    private Container[][] cargo;
    private int heavyContainers;
    private int refrigeratedContainers;
    private boolean explosives;
    private int explosiveContainers;
    private boolean toxics;
    private int toxicContainers;
    private Random random = new Random();

    public Ship() {
        this.name = nameShip();
        this.allContainers = random.nextInt(301) + 100;
        this.heavyContainers = allContainers - random.nextInt(91);
        this.refrigeratedContainers = allContainers - heavyContainers;
        this.explosives = setExplosives();
        this.toxics = setToxics();
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

    public Container[][] getCargo() {
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

    public String getCurrentStatus() {
        String currentStatus = "";
        currentStatus += "Name: " + this.getName() + "\n"
        + "Current load: " + this.getCargo().length + " containers\n";
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