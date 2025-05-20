package models;

import models.sensors.Sensor;

import java.time.LocalDateTime;
import java.util.EventObject;

public class AlarmEvent extends EventObject {
    private final LocalDateTime datetime;
    private int severity;

    private boolean detailsViewed = false;
    private boolean treated = false;

    public AlarmEvent(Sensor source) {
        super(source);
        this.datetime = LocalDateTime.now();
        this.setSeverity(source);
    }

    public void setSeverity(Sensor sensor) {
        this.severity = (int) Math.max(1, Math.min(3,
                Math.ceil((sensor.getValue() - sensor.getThreshold())
                          * sensor.getLocation().getCriticity() / 2)));
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

    public boolean isDetailsViewed() {
        return detailsViewed;
    }

    public void setDetailsViewed(boolean detailsViewed) {
        this.detailsViewed = detailsViewed;
    }

    public boolean isTreated() {
        return treated;
    }

    public void setTreated(boolean treated) {
        this.treated = treated;
    }

    @Override
    public String toString() {
        String base = String.format("[SensorType: %s] [Time: %s] @ %s (severity: %d)",
            ((Sensor)getSource()).getName(),
            datetime.toLocalTime(),
            getLocation().getAdress(),
            severity);
        return treated ? base + " (traité)" : base;
    }
}
