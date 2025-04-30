package models;

import models.enums.GasType;

public class GasSensor extends Sensor{
    private GasType type;

    public GasSensor(String name, Location location, double threshold, double value, GasType type) {
        super(name, location, threshold, value);
        this.type = type;
    }

    public GasSensor(String name, Location location, double threshold, GasType type) {
        super(name, location, threshold);
        this.type = type;
    }

    public GasType getType() {
        return type;
    }

    public void setType(GasType type) {
        this.type = type;
    }
}
