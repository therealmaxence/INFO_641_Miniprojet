package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

import models.ApplicationSystem;
import models.Location;

public class AddLocationPanel extends JPanel {
    private JTextField addressField;
    private JTextField preciseLocField;
    private JTextField criticityField;
    private JButton addButton;

    public AddLocationPanel() {
        setLayout(new GridLayout(0, 2, 5, 5));

        // Address field
        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        // Precise location (optional)
        add(new JLabel("Precise Location (optional):"));
        preciseLocField = new JTextField();
        add(preciseLocField);

        // Criticity
        add(new JLabel("Criticity (0-10):"));
        criticityField = new JTextField();
        add(criticityField);

        // Add button
        add(new JLabel()); // empty label for spacing
        addButton = new JButton("Add Location");
        add(addButton);

        // Button logic
        addButton.addActionListener(e -> {
			try {
				AddLocation(e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
    }

    private void AddLocation(ActionEvent e) throws Exception {
        String address = addressField.getText().trim();
        String preciseLoc = preciseLocField.getText().trim();
        String criticityStr = criticityField.getText().trim();

        if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Address is required.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int criticity;
        try {
            criticity = Integer.parseInt(criticityStr);
            if (criticity < 0 || criticity > 10) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Criticity must be an integer between 0 and 10.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Location location = preciseLoc.isEmpty()
                ? new Location(address, criticity)
                : new Location(address, preciseLoc, criticity);

        ApplicationSystem.getInstance().addLocation(location);
        JOptionPane.showMessageDialog(this, "Location added!");
        clearFields();
    }

    private void clearFields() {
        addressField.setText("");
        preciseLocField.setText("");
        criticityField.setText("");
    }
}
