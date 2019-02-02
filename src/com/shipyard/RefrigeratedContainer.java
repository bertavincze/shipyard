package com.shipyard;

/**
 * A refrigerated container which is a kind of heavy container.
 */

public class RefrigeratedContainer extends Container {

    private boolean explosives;
    private boolean toxic;

    public RefrigeratedContainer(boolean explosives, boolean toxic) {
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