package com.shipyard;

/**
 * A refrigerated container which is a kind of heavy container.
 */

public class RefrigeratedContainer extends HeavyContainer {

    private Container[] contents;
    private boolean explosives;
    private boolean toxics;

    public RefrigeratedContainer(Container[] contents, boolean explosives, boolean toxics) {
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