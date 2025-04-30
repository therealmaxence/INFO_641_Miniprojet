package Model;

import Model.Enum.GasType;

public class GasSensor extends Sensor{
	
	private GasType type;

	public GasSensor(String name, Location location, double threshold, double value, GasType type) {
		super(name, location, threshold, value);
		this.type = type;
	}
}
