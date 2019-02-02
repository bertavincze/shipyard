package com.shipyard;

/**
 * A heavy container is a kind of basic container.
 */

public class HeavyContainer extends Container {

    private boolean explosives;
    private boolean toxics;

    public HeavyContainer(boolean explosives, boolean toxics) {
        super(explosives, toxics);
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public boolean getToxics() {
        return this.toxics;
    }

}