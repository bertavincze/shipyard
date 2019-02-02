package com.shipyard;

/**
 * A normal basic container that is used for most shipping.
 */

public abstract class Container {

    private boolean explosives;
    private boolean toxic;


    public Container() {
    }

    public boolean getExplosives() {
        return this.explosives;
    }

    public boolean getToxic() {
        return this.toxic;
    }

    @Override
    public String toString() {
        String data = "";
        if (this instanceof HeavyContainer){
            data = "Heavy ";
            if (this.getToxic()){
                data += "toxic ";
            }
            if (this.getExplosives()){
                data += "explosive ";
            }
            data += "container";
            return data;
        }
        else if (this instanceof RefrigeratedContainer){
            data = "Refrigerated ";
            if (this.getToxic()){
                data += "toxic ";
            }
            if (this.getExplosives()){
                data += "explosive ";
            }
            data += "container";
        }
        return data;
    }
}