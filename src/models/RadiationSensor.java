package models;

public class RadiationSensor extends Sensor{
    public RadiationSensor(String name, Location location, double threshold) {
        super(name, location, threshold);
    }

    public RadiationSensor(String name, Location location, double threshold, double value) {
        super(name, location, threshold, value);
    }
}
