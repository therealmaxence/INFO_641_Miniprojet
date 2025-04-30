package Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Monitor implements ISensorListener {
	@Override
	public void dangerDetected(AlarmEvent e) {
		Sensor s = (Sensor) e.getSource();
		System.out.println("Location : " + s.getLocation().getAdress() + ", Date : " + e.getTimestamp() + ", Severity :" + e.getSeverity());
	}
}