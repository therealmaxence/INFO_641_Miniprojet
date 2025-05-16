package ui;

import models.Location;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class LocationPanel extends JPanel {
    private final DefaultListModel<Location> locModel = new DefaultListModel<>();
    private final JList<Location> locList = new JList<>(locModel);
    private final JButton addLocBtn = new JButton("Ajouter Location ➕");
    private final JButton delLocBtn = new JButton("Supprimer Location 🗑️");

    public LocationPanel() {
        setLayout(new BorderLayout(5,5));
        locList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        add(new JScrollPane(locList), BorderLayout.CENTER);

        // Button panel
        JPanel btnPanel = new JPanel();
        btnPanel.add(addLocBtn);
        btnPanel.add(delLocBtn);
        add(btnPanel, BorderLayout.SOUTH);

        addLocBtn.addActionListener(this::onAddLocation);
        delLocBtn.addActionListener(this::onDeleteLocation);
        delLocBtn.setEnabled(false);

        locList.addListSelectionListener(e -> {
            delLocBtn.setEnabled(locList.getSelectedIndex() != -1);
        });
    }

    private void onAddLocation(ActionEvent e) {
        JTextField addrField = new JTextField();
        JTextField preciseField = new JTextField();
        JSpinner critSpinner = new JSpinner(new SpinnerNumberModel(5, 0, 10, 1));

        Object[] form = {
            "Adresse:", addrField,
            "Emplacement précis:", preciseField,
            "Criticité (0–10):", critSpinner
        };
        int ok = JOptionPane.showConfirmDialog(
            this, form, "Nouvelle Location", JOptionPane.OK_CANCEL_OPTION
        );
        if (ok == JOptionPane.OK_OPTION) {
            Location loc = new Location(
                addrField.getText(),
                preciseField.getText(),
                (int)((Number)critSpinner.getValue())
            );
            locModel.addElement(loc);
        }
    }

    private void onDeleteLocation(ActionEvent e) {
        int idx = locList.getSelectedIndex();
        if (idx != -1) {
            locModel.remove(idx);
        }
    }

    public DefaultListModel<Location> getLocationModel() {
        return locModel;
    }

    public JList<Location> getLocationList() {
        return locList;
    }
}