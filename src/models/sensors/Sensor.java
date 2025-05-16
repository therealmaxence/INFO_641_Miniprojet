package models.sensors;

import models.AlarmEvent;
import models.ISensorListener;
import models.Location;

import java.util.List;

public abstract class Sensor {
    protected String name;
    protected Location location;
    protected double threshold;
    protected double value;
    protected List<ISensorListener> listeners = new java.util.ArrayList<>();

    public Sensor(String name, Location location, double threshold) {
        this.name = name;
        this.location = location;
        this.threshold = threshold;
    }

    public Sensor(String name, Location location, double threshold, double value) {
        this(name, location, threshold);
        this.setValue(value);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
        if (value > threshold) {
            AlarmEvent event = new AlarmEvent(this);
            for (ISensorListener listener : listeners) {
                listener.dangerDetected(event);
            }
        }
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
        if (value > threshold) {
            AlarmEvent event = new AlarmEvent(this);
            for (ISensorListener listener : listeners) {
                listener.dangerDetected(event);
            }
        }
    }

    public void addListener(ISensorListener listener) throws Exception {
        if (!listener.isAllowedSensorType(this))
            throw new Exception("Sensor type not allowed in this monitor");
        listeners.add(listener);
    }
    public void removeListener(ISensorListener listener) {
        listeners.remove(listener);
    }
    
    public String toString() {
		return "Sensor : "+name+", Location : "+location+", Value : "+value+", Treshold : "+threshold;
    	
    }
}
