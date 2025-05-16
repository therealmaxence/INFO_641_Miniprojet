package ui;

import models.ApplicationSystem;
import models.sensors.Sensor;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Random;

public class SimulatorPanel extends JPanel {
    private JPanel sensorListPanel;
    private JTextField loopField;
    private JButton launchButton;
    private JButton refreshButton;

    public SimulatorPanel() throws Exception {
        setLayout(new BorderLayout());

        sensorListPanel = new JPanel();
        sensorListPanel.setLayout(new BoxLayout(sensorListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(sensorListPanel);
        add(scrollPane, BorderLayout.CENTER);
        refreshSensorList();

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottom.add(new JLabel("Simulation loops:"));
        loopField = new JTextField(5);
        bottom.add(loopField);
        launchButton = new JButton("Launch");
        bottom.add(launchButton);
        refreshButton = new JButton("Refresh");
        bottom.add(refreshButton);
        add(bottom, BorderLayout.SOUTH);

        launchButton.addActionListener(e -> {
			try {
				runSimulation();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
        refreshButton.addActionListener(e -> {
			try {
				refreshSensorList();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    }

    private void refreshSensorList() throws Exception {
        sensorListPanel.removeAll();
        
        List<Sensor> sensors = ApplicationSystem.getInstance().getSensors();
        for (Sensor sensor : sensors) {
            JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT));
            row.add(new JLabel(sensor.getName() + " [" + sensor.getClass().getSimpleName() + "] Value: " + sensor.getValue()));
            JButton modify = new JButton("Modify");
            modify.addActionListener(e -> modifySensor(sensor));
            row.add(modify);
            JButton delete = new JButton("Delete");
            delete.addActionListener(e -> {
				try {
					deleteSensor(sensor);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
            row.add(delete);
            sensorListPanel.add(row);
        }
        sensorListPanel.revalidate();
        sensorListPanel.repaint();
    }

    private void modifySensor(Sensor sensor) {
        String val = JOptionPane.showInputDialog(this, "New value for " + sensor.getName() + ":", sensor.getValue());
        try {
            double newVal = Double.parseDouble(val);
            sensor.setValue(newVal);
            ConsolePanel.appendMessage("Modified " + sensor.getName() + " to " + newVal + "\n");
            refreshSensorList();
        } catch (Exception ex) { }
    }

    private void deleteSensor(Sensor sensor) throws Exception {
    	ApplicationSystem.getInstance().removeSensor(sensor);
    	ConsolePanel.appendMessage("Deleted " + sensor.getName() + "\n");
        refreshSensorList();
    }

    private void runSimulation() throws Exception {
        int loops;
        try { loops = Integer.parseInt(loopField.getText().trim()); }
        catch (Exception ex) { return; }
        List<Sensor> sensors = ApplicationSystem.getInstance().getSensors();
        Random rand = new Random();
        for (int i = 1; i <= loops; i++) {
            for (Sensor s : sensors) {
                double var = (rand.nextDouble() - 0.5) * s.getThreshold();
                double newVal = s.getValue() + var;
                s.setValue(newVal);
                ConsolePanel.appendMessage("[Loop " + i + "] " + s.getName() + " = " + newVal + "\n");
            }
        }
        refreshSensorList();
    }
}
