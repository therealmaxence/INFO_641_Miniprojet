package models;

import models.sensors.Sensor;

import java.time.LocalDateTime;
import java.util.EventObject;

public class AlarmEvent extends EventObject {
    private final LocalDateTime datetime;
    private int severity;

    public AlarmEvent(Sensor source) {
        super(source);
        this.datetime = LocalDateTime.now();
        this.setSeverity(source);
    }

    public void setSeverity(Sensor sensor) {
        this.severity = (int) Math.max(1, Math.min(3,
                Math.ceil((sensor.getValue() - sensor.getThreshold()) * sensor.getLocation().getCriticity() / 2)));
    }

    public Location getLocation() {
        return ((Sensor) getSource()).getLocation();
    }

    public LocalDateTime getDatetime() {
        return datetime;
    }

    public int getSeverity() {
        return severity;
    }
}
