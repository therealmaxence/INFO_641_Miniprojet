package models.monitors;

import models.AlarmEvent;
import models.ApplicationSystem;
import models.ISensorListener;
import models.sensors.Sensor;
import ui.AlarmMonitorView;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;


public abstract class Monitor implements ISensorListener {
    protected List<String> allowedSensorTypes;
    protected List<AlarmEvent> alarms = new ArrayList<>();

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
    	try {
			if (this.isAllowedSensorType((Sensor) e.getSource())) {
				alarms.add(e);
				// POPUP for alert
				//JOptionPane.showMessageDialog(new JFrame(),"Alarm"+e);
				AlarmMonitorView.getDataContent().refreshAlarmList();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    @Override
    public boolean isAllowedSensorType(Sensor sensor) throws Exception {
        String sensorType = sensor.getClass().getSimpleName();
        return allowedSensorTypes.contains(sensorType);
    }

    public void listen(Sensor sensor) throws Exception {
        sensor.addListener(this);
    }
    
    @Override 
    public String toString() {
    	return this.getClass().getSimpleName();
    }

	public List<AlarmEvent> getAlarmEvents(){
		return alarms;
	}
}
