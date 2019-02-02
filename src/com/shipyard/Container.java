package com.shipyard;

/**
 * A normal basic container that is used for most shipping.
 */

public class Container {

    private boolean explosives;
    private boolean toxics;

    public Container(boolean explosives, boolean toxics) {
        this.explosives = explosives;
        this.toxics = toxics;
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public boolean getToxics() {
        return this.toxics;
    }
    
}