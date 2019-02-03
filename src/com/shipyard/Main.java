package com.shipyard;

public class Main {

    public static void main(String[] args) {
        Logger logger = new Logger();
        UI ui = new UI();
        ui.mainMenu(logger, ui);
    }
}



