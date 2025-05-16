package ui;

import models.ApplicationSystem;
import models.ISensorListener;
import models.monitors.Monitor;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class AlarmManagerView extends JFrame {
    private final AddLocationPanel locationPanel;
    private final AddSensorPanel sensorPanel;

    public AlarmManagerView() throws Exception {
        super("Configuration");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocation(200, 100);

        // instantiate panels
        locationPanel = new AddLocationPanel();
        sensorPanel   = new AddSensorPanel();

        // tabbed layout
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("Locations", locationPanel);
        tabs.addTab("Capteurs",  sensorPanel);

        setContentPane(tabs);
    }
}
