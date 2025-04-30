package models.sensors;

import models.Location;

public class FireSensor extends Sensor {
    public FireSensor(String name, Location location, double threshold) {
        super(name, location, threshold);
    }

    public FireSensor(String name, Location location, double threshold, double value) {
        super(name, location, threshold, value);
    }
}
