package models;

import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import models.monitors.Monitor;
import models.monitors.MonitorTypeA;
import models.monitors.MonitorTypeB;
import models.sensors.FireSensor;
import models.sensors.GasSensor;
import models.sensors.RadiationSensor;
import models.sensors.Sensor;
import models.Location;
import models.enums.GasType;

public class ApplicationSystem {
	private static ApplicationSystem instance;
	
    private ArrayList<Monitor> monitors = new ArrayList<>();
    private ArrayList<Sensor> sensors = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();

    public ApplicationSystem() throws Exception {
        // Sample locations
        getLocations().add(new Location("123 Main St", "Room 101", 7));
        getLocations().add(new Location("456 Industrial Ave", "Server Room", 9));
        getLocations().add(new Location("789 Central Blvd", "Floor 3 - Kitchen", 5));
        getLocations().add(new Location("321 Emergency Rd", "Control Center", 10));
        getLocations().add(new Location("654 Tech Park", "Warehouse A", 6));
        getLocations().add(new Location("888 Valley View", 3));

        // Create and add monitors
        monitors.add(new MonitorTypeA());
        monitors.add(new MonitorTypeB());

        // Fire Sensors
        getSensors().add(new FireSensor("FireSensor-1", getLocations().get(0), 0.3));
        getSensors().add(new FireSensor("FireSensor-2", getLocations().get(1), 0.6));
        getSensors().add(new FireSensor("FireSensor-3", getLocations().get(2), 0.1));
        getSensors().add(new FireSensor("FireSensor-4", getLocations().get(3), 0.9));
        getSensors().add(new FireSensor("FireSensor-5", getLocations().get(4), 0.4));
        getSensors().add(new FireSensor("FireSensor-6", getLocations().get(5), 0.2));

        // Gas Sensors
        getSensors().add(new GasSensor("GasSensor-1", getLocations().get(0), 50.0, 42.3, GasType.CO2));
        getSensors().add(new GasSensor("GasSensor-2", getLocations().get(2), 30.0, GasType.CO));
        getSensors().add(new GasSensor("GasSensor-3", getLocations().get(4), 25.0, 18.7, GasType.CH4));
        getSensors().add(new GasSensor("GasSensor-4", getLocations().get(5), 10.0, GasType.SO2));

        // Radiation Sensors
        getSensors().add(new RadiationSensor("RadiationSensor-1", getLocations().get(1), 70.0));
        getSensors().add(new RadiationSensor("RadiationSensor-2", getLocations().get(2), 100.0, 95.0));
        getSensors().add(new RadiationSensor("RadiationSensor-3", getLocations().get(4), 85.0, 60.0));
        
        // Subscribe to event
        for (Monitor m : this.monitors) {
        	for (Sensor s : this.getSensors()) {
        		if (m.isAllowedSensorType(s))
        			m.listen(s);
            }
        }
    }
    
    public static ApplicationSystem getInstance() throws Exception {
	    if (ApplicationSystem.instance == null) {
	    	ApplicationSystem.instance = new ApplicationSystem();
	    }
	    return ApplicationSystem.instance;
    }

	public ArrayList<Location> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<Location> locations) {
		this.locations = locations;
	}

	public ArrayList<Sensor> getSensors() {
		return sensors;
	}
	
	public ArrayList<Monitor> getMonitors() {
		return monitors;
	}

	public void setSensors(ArrayList<Sensor> sensors) {
		this.sensors = sensors;
	}
	
	public void addSensor(Sensor s) {
		sensors.add(s);
	}
	
	public void removeSensor(Sensor s) {
		sensors.remove(s);
	}
	
	public void addLocation(Location l) {
		locations.add(l);
	}
	
	public void removeLocation(Location l) {
		locations.remove(l);
	}

}
