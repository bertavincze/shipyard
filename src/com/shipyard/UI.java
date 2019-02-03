package com.shipyard;

import java.util.Scanner;

public class UI {

    private Scanner sc = new Scanner(System.in);
    private Menu menu = new Menu("Main Menu", new String[]{"New Simulation",
                                 "Load Previous Simulation"}, "Pick an option or press 0 to exit.");

    public String getInput() {
        String input = sc.nextLine();
        return input;
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
    }

    public void promptEnterKey() {
        System.out.println("Press ENTER to continue...");
        sc.nextLine();
    }

    public void mainMenu(Logger logger, UI ui) {
        String option;
        do {
            this.menu.displayMenu();
            option = ui.getInput();
            switch (option){
                case "1":
                    Docks docks = new Docks();
                    docks.newSimulation(docks, logger, ui);
                    continue;
                case "2":
                    Docks docksLoaded = logger.load();
                    docksLoaded.newSimulation(docksLoaded, logger, ui);
                    continue;
                case "0":
                    System.out.println("Exiting program.");
                    System.exit(0);
                default:
                    System.out.println("Invalid selection!");
                    continue;
            }
        } while (!option.equals("0"));
    }
}
