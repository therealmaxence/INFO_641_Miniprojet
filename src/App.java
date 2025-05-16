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
        // Launch both the manager and monitor on the EDT
        SwingUtilities.invokeLater(() -> {
			try {
				AlarmManagerView manager = new AlarmManagerView();
	            manager.setVisible(true);
	            
	            SimulatorView simulator = new SimulatorView();
	            simulator.setVisible(true);
	            
	            MonitorView monitors = new MonitorView();
	            monitors.setVisible(true);
	        } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        });
    }
};
