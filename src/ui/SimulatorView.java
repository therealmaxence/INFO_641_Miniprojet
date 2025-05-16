package ui;

import javax.swing.*;
import java.awt.*;

public class SimulatorView extends JFrame {
    public SimulatorView() throws Exception {
        super("Sensor Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        SimulatorPanel simulatorPanel = new SimulatorPanel();
        ConsolePanel consolePanel = new ConsolePanel();
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Simulator", simulatorPanel);
        tabbedPane.addTab("Console", consolePanel);

        add(tabbedPane, BorderLayout.CENTER);
    }
}
