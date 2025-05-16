package ui;

import models.sensors.Sensor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;



public class PilotPanel extends JPanel {
    private final SensorPanel sensorPanel;
    private final JTextField valueField = new JTextField(8);
    private final JTextField threshField = new JTextField(8);
    private final JButton setValueBtn = new JButton("✅");
    private final JButton setThreshBtn = new JButton("✅");

    
    public PilotPanel(SensorPanel sensorPanel) {
        this.sensorPanel = sensorPanel;
        setLayout(new BorderLayout(5,5));

        sensorPanel.getSensorList().addListSelectionListener(e -> {
            Sensor s = sensorPanel.getSensorList().getSelectedValue();
            if (s != null) {
                valueField.setText(Double.toString(s.getValue()));
                threshField.setText(Double.toString(s.getValue()));
                setValueBtn.setEnabled(true);
            } else {
                valueField.setText("");
                threshField.setText("");
                setValueBtn.setEnabled(false);
            }
        });

        JPanel ctrl = new JPanel();
        
        // Value field
        ctrl.add(new JLabel("Valeur mesurée:"));
        ctrl.add(valueField);
        setValueBtn.setEnabled(false);
        setValueBtn.addActionListener(this::onSetValue);
        ctrl.add(setValueBtn);
        
        // Threshold field
        ctrl.add(new JLabel("Seuil limite:"));
        ctrl.add(threshField);
        setThreshBtn.setEnabled(false);
        setThreshBtn.addActionListener(this::onSetValue);
        ctrl.add(setThreshBtn);

        add(ctrl);
    }

    private void onSetValue(ActionEvent e) {
        Sensor s = sensorPanel.getSensorList().getSelectedValue();
        if (s != null) {
            double v = Double.parseDouble(valueField.getText());
            s.setValue(v);
        }
    }
}