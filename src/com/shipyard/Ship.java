package com.shipyard;

import java.util.*;

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
    private Map<String, List<Container>> cargo;
    private int heavyContainers;
    private int refrigeratedContainers;
    private List<Container> heavyContents;
    private List<Container> refContents;
    private boolean explosives;
    private int explosiveContainers;
    private boolean toxic;
    private int toxicContainers;
    private Random random = new Random();

    public Ship(String name, int allContainers, int heavyContainers, int refrigeratedContainers,
                boolean explosives, boolean toxic) {
        this.name = name;
        this.allContainers = allContainers;
        this.heavyContainers = heavyContainers;
        this.refrigeratedContainers = refrigeratedContainers;
        this.explosives = explosives;
        this.toxic = toxic;
        this.heavyContents = new ArrayList<>();
        this.refContents = new ArrayList<>();
        this.cargo = new HashMap<>();
    }

    public Ship() {
        this.name = nameShip();
        this.allContainers = random.nextInt(301) + 100;
        this.heavyContainers = allContainers - random.nextInt(91);
        this.refrigeratedContainers = allContainers - heavyContainers;
        this.explosives = setExplosives();
        this.toxic = setToxic();
        this.heavyContents = new ArrayList<>();
        this.refContents = new ArrayList<>();
        this.cargo = new HashMap<>();
    }

    private String nameShip() {
        FileHandler fh = new FileHandler();
        return fh.readLine("data/shipnames.csv", random.nextInt(fh.countLines("data/shipnames.csv")));
    }

    private boolean setExplosives() {
        this.explosives = random.nextBoolean();
        return this.explosives;
    }

    private boolean setToxic() {
        this.toxic = random.nextBoolean();
        return this.toxic;
    }

    public boolean isContainerAllowed(Container container) {
        if (!container.getExplosives() && !container.getToxic()){
            return true;
        }
        else if (container.getExplosives() && container.getToxic() && this.getExplosives() && this.getToxic()){
            return true;
        }
        else if (container.getExplosives() && this.getExplosives() && !container.getToxic() && !this.getToxic()){
            return true;
        }
        else if (container.getExplosives() && this.getExplosives() && !container.getToxic() && this.getToxic()){
            return true;
        }
        else if (container.getToxic() && this.getToxic() && !container.getExplosives() && !this.getExplosives()){
            return true;
        }
        else if (container.getToxic() && this.getToxic() && !container.getExplosives() && this.getExplosives()){
            return true;
        }
        return false;
    }

    private boolean isExplosiveAllowed(Container container) {
        if (container.getExplosives() && getExplosives()) {
            return true;
        }
        return false;
    }

    private boolean isToxicAllowed(Container container) {
        if (container.getToxic() && getToxic()) {
            return true;
        }
        return false;
    }

    private boolean isShipFull() {
        if (isRefContentsFull() && isHeavyContentsFull()) {
            return true;
        }
        return false;
    }

    private boolean isRefContentsFull() {
        while (this.refContents.size() < this.refrigeratedContainers) {
            return false;
        }
        return true;
    }

    private boolean isHeavyContentsFull() {
        while (this.heavyContents.size() < this.heavyContainers) {
            return false;
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public Map<String, List<Container>> getCargo() {
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
        if (this.toxic) {
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

    public boolean getToxic() {
        return this.toxic;
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public void loadCargo(Container container, List<Container> containers) throws ShipFullException, WrongTypeException {
        if (!isShipFull()) {
            if (container instanceof HeavyContainer) {
                if (isContainerAllowed(container) && !isHeavyContentsFull()){
                    heavyContents.add(container);
                    this.cargo.put("Heavy Containers", heavyContents);
                } else if (isHeavyContentsFull()) {
                    throw new HeavyContentException();
                } else if (!isContainerAllowed(container)) {
                    throw new WrongTypeException();
                }

            } else if (container instanceof RefrigeratedContainer) {
                if (isContainerAllowed(container) && !isRefContentsFull()) {
                    refContents.add(container);
                    this.cargo.put("Refrigerated Containers", refContents);
                } else if (isRefContentsFull()) {
                    throw new RefrigeratedContentException();
                }
                } else if (!isContainerAllowed(container)) {
                    throw new WrongTypeException();
                }
        } else if (isShipFull()) {
            throw new ShipFullException();
        }
    }

    public String getCurrentStatus() {
        String currentStatus = "";
        int currentLoad = this.heavyContents.size() + this.refContents.size();
        currentStatus += "Name: " + this.getName() + "\n"
                + "Current load out of total capacity: " + currentLoad + "/" + this.getAllContainers() + "\n"
                + "Current cargo: " + this.getCargo() + "\n";
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
        } else {
            shipInfo += "Can carry explosives: no\n";
        }

        if (this.toxic) {
            shipInfo += "Can carry toxic materials: yes\n";
        } else {
            shipInfo += "Can carry toxic materials: no\n";
        }
        return shipInfo;
    }

}