package models.monitors;

import models.sensors.GasSensor;
import models.sensors.RadiationSensor;

import java.util.ArrayList;
import java.util.Arrays;

public class MonitorTypeB extends Monitor {
    public MonitorTypeB() {
        super(new ArrayList<>(Arrays.asList(
                RadiationSensor.class.getSimpleName(),
                GasSensor.class.getSimpleName()
        )));
    }
}
