package com.shipyard;

/**
 * A heavy container is a kind of basic container.
 */

public class HeavyContainer extends Container {

    private boolean explosives;
    private boolean toxic;

    public HeavyContainer(boolean explosives, boolean toxic) {
        this.explosives = explosives;
        this.toxic = toxic;
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public boolean getToxic() {
        return this.toxic;
    }

}