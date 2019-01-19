package com.shipyard;

/**
 * A normal basic container that is used for most shipping.
 */

public class Container {

    private Container[] contents;
    private boolean explosives;
    private boolean toxics;

    public Container(Container[] contents, boolean explosives, boolean toxics) {
        this.contents = contents;
        this.explosives = explosives;
        this.toxics = toxics;
    }

    public Container[] getContents() {
        return this.contents;
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public boolean getToxics() {
        return this.toxics;
    }
}