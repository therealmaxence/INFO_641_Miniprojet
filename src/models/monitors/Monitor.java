package models.monitors;

import models.AlarmEvent;
import models.ISensorListener;
import models.sensors.Sensor;

import java.util.ArrayList;
import java.util.List;

public abstract class Monitor implements ISensorListener {
    protected List<String> allowedSensorTypes;

    public Monitor(List<String> allowedSensorTypes) {
        this.allowedSensorTypes = allowedSensorTypes;
    }

    public void addAllowedSensorType(String sensorType) {
        allowedSensorTypes.add(sensorType);
    }
    public void removeAllowedSensorType(String sensorType) {
        allowedSensorTypes.remove(sensorType);
    }

    @Override
    public void dangerDetected(AlarmEvent e) {
        System.out.println("Location : (" + e.getLocation() + "), Date : " + e.getDatetime() + ", Severity :" + e.getSeverity());
    }

    @Override
    public boolean isAllowedSensorType(Sensor sensor) throws Exception {
        String sensorType = sensor.getClass().getSimpleName();
        return allowedSensorTypes.contains(sensorType);
    }

    public void listen(Sensor sensor) throws Exception {
        sensor.addListener(this);
    }
}
