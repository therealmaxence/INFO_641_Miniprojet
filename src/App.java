import models.ISensorListener;
import models.monitors.MonitorTypeA;
import models.monitors.MonitorTypeB;
import ui.AlarmManagerView;
import ui.AlarmMonitorView;
import java.util.ArrayList;
import models.monitors.Monitor;
import javax.swing.SwingUtilities;

public class App {
    public static void main(String[] args) {
    	
        MonitorTypeA mA = new MonitorTypeA();
        MonitorTypeB mB = new MonitorTypeB();
        
        ArrayList<Monitor> monitors = new ArrayList<>();
        
        monitors.add(mA);
        monitors.add(mB);


        // Launch both the manager and monitor on the EDT
        SwingUtilities.invokeLater(() -> {
            AlarmManagerView manager = new AlarmManagerView(monitors);
            manager.setVisible(true);
            
            AlarmMonitorView monitorView = new AlarmMonitorView(monitors);
            monitorView.setVisible(true);
        });
    }
};
