package Model;

import java.time.LocalDateTime;
import java.util.EventObject;

public class AlarmEvent extends EventObject {
    
    private final LocalDateTime timestamp;
    private int severity;

    public AlarmEvent(Sensor s) {
        super(s);
        this.timestamp = LocalDateTime.now();
        this.setSeverity(s);
    }

    private void setSeverity(Sensor s) {
    	this.severity = (int) Math.max(1, Math.min(3, 
    		    Math.ceil((s.getValue() - s.getThreshold()) * s.getLocation().getCriticity() / 2)));
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getSeverity() {
        return severity;
    }
}
