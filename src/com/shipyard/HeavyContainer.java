package com.shipyard;

/**
 * A heavy container is a kind of basic container.
 */

public class HeavyContainer extends Container {

    private Container[] contents;
    private boolean explosives;
    private boolean toxics;

    public HeavyContainer(Container[] contents, boolean explosives, boolean toxics) {
        super(contents, explosives, toxics);
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