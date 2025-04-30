package models.monitors;

import models.sensors.FireSensor;
import models.sensors.GasSensor;

import java.util.ArrayList;
import java.util.Arrays;

public class MonitorTypeA extends Monitor {
    public MonitorTypeA() {
        super(new ArrayList<>(Arrays.asList(
                FireSensor.class.getSimpleName(),
                GasSensor.class.getSimpleName()
        )));
    }
}
