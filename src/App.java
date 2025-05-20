import models.ApplicationSystem;
import models.ISensorListener;
import models.monitors.MonitorTypeA;
import models.monitors.MonitorTypeB;
import ui.AlarmManagerView;
import ui.AlarmMonitorView;
import ui.ConsolePanel;
import ui.MonitorView;
import ui.SimulatorView;
import ui.SimulatorPanel;

import java.util.ArrayList;
import models.monitors.Monitor;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
			try {		
				AlarmManagerView manager = new AlarmManagerView();
	            manager.setVisible(true);
	            
	            SimulatorView simulator = new SimulatorView();
	            simulator.setVisible(true);
	            
	            AlarmMonitorView monitors = new AlarmMonitorView();
	            monitors.setVisible(true);
	        } catch (Exception e) {
				e.printStackTrace();
			}
        });
    }
};
