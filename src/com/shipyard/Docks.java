package com.shipyard;

import java.io.Serializable;
import java.util.*;

public class Docks implements Iterable<Container>, Serializable {

    private Random random = new Random();
    private String name;
    private int size;
    private List<Ship> ships;
    private List<Container> containers;
    private Ship currentShip;

    public Docks(String name, int size, int heavyContainers, int refrigeratedContainers) {
        this.name = name;
        this.size = size;
        this.ships = createShips(size);
        this.containers = generateContainers(heavyContainers, refrigeratedContainers);
    }

    public Docks() {
        randomDocks();
        this.ships = createShips(size);
        this.containers = generateContainers(random.nextInt(100) + 20, random.nextInt(100) + 20);

    }

    private void randomDocks() {
        FileHandler fh = new FileHandler();
        this.name = fh.readLine("data/docknames.csv", random.nextInt(fh.countLines("data/docknames.csv")));
        this.size = random.nextInt(15) + 5;
    }

    public List<Ship> createShips(int num) {
        List<Ship> ships = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ships.add(new Ship());
        }
        return ships;
    }

    public String getName() {
        return this.name;
    }

    public int getSize() {
        return this.size;
    }

    public List<Ship> getShips() {
        return this.ships;
    }

    public Ship getCurrentShip() {
        return this.currentShip;
    }

    private void updateShips() {
        List<Ship> tempShips = new ArrayList<>();
        for (Ship ship: this.getShips()) {
            if (!ship.equals(currentShip)){
                tempShips.add(ship);
            }
        }
        this.ships = tempShips;
    }

    public List<Container> getContainers() {
        return containers;
    }

    public List<Container> generateContainers(int heavyContainers, int refrigeratedContainers) {
        List<Container> containers = new ArrayList<>();

        for (int i = 0; i < heavyContainers; i++) {
            Container container = new HeavyContainer(random.nextBoolean(), random.nextBoolean());
            containers.add(container);
        }
        for (int i = 0; i < refrigeratedContainers; i++) {
            Container container = new RefrigeratedContainer(random.nextBoolean(), random.nextBoolean());
            containers.add(container);
        }
        return containers;
    }

    private Ship findShipByName(String shipName, List<Ship> ships) throws NullPointerException {
        for (Ship ship: ships) {
            if (shipName.equals(ship.getName())){
                return ship;
            }
        }
        throw new NullPointerException();
    }

    private void setCurrentShip(UI ui) {
        List<String> shipNames = new ArrayList<>();
        for (Ship ship: this.getShips()) {
            shipNames.add(ship.getName());
        }
        Ship chosenShip = null;
        do {
            System.out.println("Type the name of the ship you want to start with: \n");
            System.out.println(shipNames);
            String shipName = ui.getInput();
            try {
                chosenShip = this.findShipByName(shipName, this.getShips());
            } catch (NullPointerException e) {
                System.out.println("No such ship found, please try again.");
                continue;
            }
        } while (chosenShip == null);
        System.out.println("Your choice: ");
        this.currentShip = chosenShip;
        System.out.println(currentShip.toString());
    }

    private void work(Logger logger) {
        Iterator<Container> containerIterator = this.iterator();
        while (containerIterator.hasNext()) {
            Container container = containerIterator.next();
            try {
                this.getCurrentShip().loadCargo(container);
                System.out.println("Successfully loaded " + container.toString() + " onto " + this.getCurrentShip().getName() + ".");
                logger.log("Successfully loaded " + container.toString() + " onto " + this.getCurrentShip().getName() + ".");
                containerIterator.remove();

            } catch (HeavyContentException hce) {
                System.out.println(hce.getMessage());
                System.out.println("Moving on...");
                logger.log(hce.getMessage());
                logger.log("Moving on...");
                continue;
            } catch (RefrigeratedContentException rce) {
                System.out.println(rce.getMessage());
                System.out.println("Moving on...");
                logger.log(rce.getMessage());
                logger.log("Moving on...");
                continue;
            } catch (WrongTypeException wte) {
                System.out.println(container.toString() + ": " + wte.getMessage());
                System.out.println("Moving on...");
                logger.log(container.toString() + ": " + wte.getMessage());
                logger.log("Moving on...");
                continue;
            } catch (ShipFullException sfe) {
                System.out.println(sfe.getMessage());
                logger.log(sfe.getMessage());
                break;
            }
        }
        System.out.println("\nDone loading cargo...");
        System.out.println("\n" + this.getCurrentShip().getCurrentStatus());
        System.out.println("Remaining containers:\n" + this.getContainers());
        updateShips();
    }

    public void newSimulation(Docks docks, Logger logger, UI ui) {
        Menu menu = new Menu("Shipyard Simulation", new String[]{"Show Docks Info", "View Ships", "View Containers", "Start Working", "Save Current Data"}, "Pick an option or press 0 to return to the main menu.");
        String option;
        do {
            menu.displayMenu();
            option = ui.getInput();
            switch (option) {
                case "1":
                    ui.clear();
                    System.out.println(docks.toString());
                    logger.logMessage(docks.toString());
                    ui.promptEnterKey();
                    continue;
                case "2":
                    ui.clear();
                    System.out.println("List of ships currently docking:\n ");
                    logger.logMessage("\nList of ships currently docking: ");
                    for (Ship ship: docks.getShips()) {
                        System.out.println(ship.toString());
                        logger.logMessage(ship.getName());
                    }
                    ui.promptEnterKey();
                    continue;
                case "3":
                    ui.clear();
                    System.out.println("List of containers awaiting pickup: ");
                    for (Container container: docks.getContainers()) {
                        System.out.println(container.toString());
                    }
                    ui.promptEnterKey();
                    continue;
                case "4":
                    ui.clear();
                    docks.setCurrentShip(ui);
                    docks.work(logger);
                    String choice;
                    do {
                        System.out.println("\nThere are still containers left. Press 'C' to continue, or press 0 to go back to the main menu.");
                        choice = ui.getInput();
                        if (choice.equals("0")){
                            continue;
                        }
                        else if (choice.equalsIgnoreCase("c")){
                            docks.setCurrentShip(ui);
                            docks.work(logger);
                        }
                        else {
                            System.out.println("Invalid input!");
                        }
                    } while (!docks.getContainers().isEmpty() && !choice.equals("0"));
                    if (docks.getContainers().isEmpty()) {
                        System.out.println("\nAll containers are loaded on ships now. Well done!");
                        continue;
                    }
                case "5":
                    ui.clear();
                    logger.save(docks);
                    continue;
                case "0":
                    ui.clear();
                    ui.mainMenu(logger, ui);
                default:
                    System.out.println("Invalid selection!");
                    continue;
            }
        } while (!option.equals("0"));
    }

    @Override
    public Iterator<Container> iterator() {
        return containers.iterator();
    }

    @Override
    public String toString() {
        return "Current docks: " + this.getName() + "\nNumber of ships: " + this.getSize() + "\nNumber of containers awaiting pickup: " + this.getContainers().size();
    }
}