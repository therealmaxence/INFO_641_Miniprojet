package models;

import models.sensors.Sensor;

public interface ISensorListener {
    void dangerDetected(AlarmEvent e);
    boolean isAllowedSensorType(Sensor sensor) throws Exception;
}
