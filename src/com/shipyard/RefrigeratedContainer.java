package com.shipyard;

/**
 * A refrigerated container which is a kind of heavy container.
 */

public class RefrigeratedContainer extends Container {

    private boolean explosives;
    private boolean toxics;

    public RefrigeratedContainer(boolean explosives, boolean toxics) {
        super(explosives, toxics);
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public boolean getToxics() {
        return this.toxics;
    }

}