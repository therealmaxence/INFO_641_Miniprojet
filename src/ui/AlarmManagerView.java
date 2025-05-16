package ui;

import models.ISensorListener;
import models.monitors.Monitor;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class AlarmManagerView extends JFrame {
    private final LocationPanel locationPanel;
    private final SensorPanel sensorPanel;
    private final PilotPanel pilotPanel;

    public AlarmManagerView(ArrayList<Monitor> listeners) {
        super("Gestionnaire d'alarmes");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocation(200, 100);

        // instantiate panels
        locationPanel = new LocationPanel();
        sensorPanel   = new SensorPanel(locationPanel, listeners);
        pilotPanel    = new PilotPanel(sensorPanel);

        // tabbed layout
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Locations", locationPanel);
        tabs.addTab("Capteurs",  sensorPanel);
        tabs.addTab("Pilotage",  pilotPanel);

        setContentPane(tabs);
    }
}
