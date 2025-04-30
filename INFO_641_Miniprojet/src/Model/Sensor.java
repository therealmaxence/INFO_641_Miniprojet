package Model;

import java.util.ArrayList;
import java.util.List;

public abstract class Sensor {
    
	protected String name;
    protected Location location;
    protected double threshold;
    protected double value;
    protected List<ISensorListener> listeners = new ArrayList<>();
    
	
    public Sensor(String name, Location location, double threshold, double value) {
    	this.name = name;
        this.location = location;
        this.threshold = threshold;
        this.value = value;
    }

    public String getName() {
        return name;
    }
    
    public Location getLocation() {
        return location;
    }

    public double getThreshold() {
        return threshold;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }
    
    public void setValue(double value) {
    	if (value >= getThreshold()) {
    		AlarmEvent e = new AlarmEvent(this);
    		for (ISensorListener listener : this.listeners) {
    			listener.dangerDetected(e);
    		}
    	}
    	this.value = value;
    }
    
    public void addListener(ISensorListener listener) {
    	this.listeners.add(listener);
    }
}
