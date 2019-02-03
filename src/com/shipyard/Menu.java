package com.shipyard;

public class Menu {

    private String title;
    private String[] options;
    private String exitMessage;

    public Menu(String title, String[] options, String exitMessage) {
        this.title = title;
        this.options = options;
        this.exitMessage = exitMessage;
    }

    public void displayMenu() {
        System.out.println(this.title.toUpperCase() + "\n");
        for (int i = 0; i < this.options.length; i++) {
            System.out.println(i + 1 + ". " + this.options[i]);
        }
        System.out.println("\n" + this.exitMessage);
    }
}
