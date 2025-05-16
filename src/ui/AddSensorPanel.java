package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import models.ApplicationSystem;
import models.Location;
import models.enums.GasType;
import models.sensors.*;

public class AddSensorPanel extends JPanel {
    private JComboBox<String> sensorTypeBox;
    private JTextField nameField, thresholdField, valueField;
    private JComboBox<Location> locationBox;
    private JComboBox<GasType> gasTypeBox;
    private JButton addButton;

    public AddSensorPanel() throws Exception {

        setLayout(new GridLayout(0, 2, 5, 5));

        add(new JLabel("Sensor Type:"));
        sensorTypeBox = new JComboBox<>(new String[]{"FireSensor", "GasSensor", "RadiationSensor"});
        add(sensorTypeBox);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Threshold:"));
        thresholdField = new JTextField();
        add(thresholdField);

        add(new JLabel("Value (optional):"));
        valueField = new JTextField();
        add(valueField);

        add(new JLabel("Location:"));
        locationBox = new JComboBox<>(ApplicationSystem.getInstance().getLocations().toArray(new Location[0]));
        add(locationBox);

        add(new JLabel("Gas Type (if gas):"));
        gasTypeBox = new JComboBox<>(GasType.values());
        add(gasTypeBox);

        addButton = new JButton("Add Sensor");
        add(new JLabel()); // filler
        add(addButton);

        sensorTypeBox.addActionListener(e -> toggleGasTypeVisibility());
        toggleGasTypeVisibility();

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
					addSensor();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            }
        });
    }

    private void toggleGasTypeVisibility() {
        boolean isGas = sensorTypeBox.getSelectedItem().equals("GasSensor");
        gasTypeBox.setEnabled(isGas);
    }

    private void addSensor() throws Exception {
        String type = (String) sensorTypeBox.getSelectedItem();
        String name = nameField.getText();
        Location location = (Location) locationBox.getSelectedItem();
        double threshold;
        Double value = null;

        try {
            threshold = Double.parseDouble(thresholdField.getText());
            if (!valueField.getText().isBlank()) {
                value = Double.parseDouble(valueField.getText());
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid number format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Sensor sensor = null;
        switch (type) {
            case "FireSensor":
                sensor = new FireSensor(name, location, threshold);
                break;
            case "GasSensor":
                GasType gasType = (GasType) gasTypeBox.getSelectedItem();
                sensor = (value == null)
                        ? new GasSensor(name, location, threshold, gasType)
                        : new GasSensor(name, location, threshold, value, gasType);
                break;
            case "RadiationSensor":
                sensor = (value == null)
                        ? new RadiationSensor(name, location, threshold)
                        : new RadiationSensor(name, location, threshold, value);
                break;
        }

        if (sensor != null) {
            ApplicationSystem.getInstance().addSensor(sensor);
            JOptionPane.showMessageDialog(this, "Sensor added!");
            clearFields();
        }
    }

    private void clearFields() {
        nameField.setText("");
        thresholdField.setText("");
        valueField.setText("");
        sensorTypeBox.setSelectedIndex(0);
        locationBox.setSelectedIndex(0);
        gasTypeBox.setSelectedIndex(0);
    }
}
