package ui;

import models.ApplicationSystem;
import models.ISensorListener;
import models.Location;
import models.enums.GasType;
import models.monitors.Monitor;
import models.sensors.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;


public class SensorPanel extends JPanel {
    private final DefaultListModel<Sensor> sensorModel = new DefaultListModel<>();
    private final JList<Sensor> sensorList = new JList<>(sensorModel);
    private final JButton addSensorBtn = new JButton("Ajouter Capteur ➕");
    private final JButton delSensorBtn = new JButton("Supprimer Capteur 🗑️");
    private final LocationPanel locationPanel;
    private ArrayList<Monitor> monitors;

    public SensorPanel(LocationPanel locPanel) {
        this.locationPanel = locPanel;
        try {
			this.monitors = ApplicationSystem.getInstance().getMonitors();
		} catch (Exception e) {
			e.printStackTrace();
		}

        setLayout(new BorderLayout(5,5));
        sensorList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(sensorList), BorderLayout.CENTER);

        // Button panel
        JPanel btnPanel = new JPanel();
        btnPanel.add(addSensorBtn);
        btnPanel.add(delSensorBtn);
        add(btnPanel, BorderLayout.SOUTH);

        addSensorBtn.addActionListener(this::onAddSensor);
        delSensorBtn.addActionListener(this::onDeleteSensor);
        delSensorBtn.setEnabled(false);

        sensorList.addListSelectionListener(e -> {
            delSensorBtn.setEnabled(sensorList.getSelectedIndex() != -1);
        });
    }

    private void onAddSensor(ActionEvent e) {
        JComboBox<String> typeBox = new JComboBox<>(new String[]{"Feu","Gaz","Radiation"});
        JTextField nameField = new JTextField();
        JTextField threshField = new JTextField("0");
        JComboBox<GasType> gasBox = new JComboBox<>(GasType.values());
        JComboBox<Location> locBox = new JComboBox<>();
        
        // populate location choices
        DefaultListModel<Location> locModel = locationPanel.getLocationModel();
        for (int i=0; i<locModel.size(); i++) {
            locBox.addItem(locModel.get(i));
        }

        gasBox.setVisible(false);
        typeBox.addActionListener(ev -> gasBox.setVisible("Gaz".equals(typeBox.getSelectedItem())));

        Object[] form = {
            "Type:", typeBox,
            "Nom:", nameField,
            "Seuil:", threshField,
            "Type de gaz:", gasBox,
            "Location:", locBox
        };
        int ok = JOptionPane.showConfirmDialog(
            this, form, "Nouveau Capteur", JOptionPane.OK_CANCEL_OPTION
        );
        if (ok == JOptionPane.OK_OPTION) {
            String type = (String) typeBox.getSelectedItem();
            String name = nameField.getText();
            double threshold = Double.parseDouble(threshField.getText());
            Location loc = (Location) locBox.getSelectedItem();

            Sensor s;
            switch(type) {
                case "Feu":
                    s = new FireSensor(name, loc, threshold);
                    for (Monitor m : monitors) {
                    	try {
							if (m.isAllowedSensorType(s)) {m.listen(s);}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
                    }
                    sensorModel.addElement(s);
                    break;
                case "Gaz":
                    GasType gt = (GasType) gasBox.getSelectedItem();
                    s = new GasSensor(name, loc, threshold, gt);
                    for (Monitor m : monitors) {
                    	try {
							if (m.isAllowedSensorType(s)) {m.listen(s);}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
                    }
                    sensorModel.addElement(s);
                    break;
                case "Radiation":
                	s = new RadiationSensor(name, loc, threshold);
                    for (Monitor m : monitors) {
                    	try {
							if (m.isAllowedSensorType(s)) {m.listen(s);}
						} catch (Exception e1) {
							e1.printStackTrace();
						}
                    }
                    sensorModel.addElement(s);
                default:
                	new IllegalArgumentException();
            }
        }
    }


    private void onDeleteSensor(ActionEvent e) {
        int idx = sensorList.getSelectedIndex();
        if (idx != -1) {
            sensorModel.remove(idx);
        }
    }

    public DefaultListModel<Sensor> getSensorModel() {
        return sensorModel;
    }

    public JList<Sensor> getSensorList() {
        return sensorList;
    }
}