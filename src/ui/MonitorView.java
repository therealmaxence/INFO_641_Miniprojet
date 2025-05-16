// File: ui/AlarmMonitorView.java
package ui;

import models.AlarmEvent;
import models.ApplicationSystem;
import models.monitors.Monitor;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MonitorView extends JFrame {
    public MonitorView() throws Exception {
        super("Sensor Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JTabbedPane tabbedPane = new JTabbedPane();
        ArrayList<Monitor> monitors = ApplicationSystem.getInstance().getMonitors();
        for (Monitor m : monitors) {
        	tabbedPane.addTab(m.toString(), new MonitorPanel(m));
        	add(tabbedPane, BorderLayout.CENTER);
        }
    }
}
