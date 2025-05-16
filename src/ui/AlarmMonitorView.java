// File: ui/AlarmMonitorView.java
package ui;

import models.AlarmEvent;
import models.ApplicationSystem;
import models.monitors.Monitor;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AlarmMonitorView extends JFrame {
    private DefaultListModel<AlarmEvent> listModel = new DefaultListModel<>();
    private JList<AlarmEvent> alarms = new JList<>(listModel);
    private JButton detailsBtn = new JButton("Détails");
    private JButton treatedBtn = new JButton("Traité");

    public AlarmMonitorView() throws Exception {
        super("Moniteur d'alarmes");
        //this.monitors = monitors;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (Monitor m : ApplicationSystem.getInstance().getMonitors()) {
        	// Create a panel for each monitor
            for (AlarmEvent e : m.getAlarmEvents()) {
                listModel.addElement(e);
            }
        }

        alarms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane sp = new JScrollPane(alarms);

        detailsBtn.setEnabled(false);
        treatedBtn.setEnabled(false);

        alarms.addListSelectionListener(e ->
            detailsBtn.setEnabled(alarms.getSelectedIndex() != -1)
        );

        detailsBtn.addActionListener(e -> showDetails());
        treatedBtn.addActionListener(e -> markTreated());

        JPanel btnPanel = new JPanel();
        btnPanel.add(detailsBtn);
        btnPanel.add(treatedBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sp, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocation(600, 100);
    }

    private void showDetails() {
        AlarmEvent e = alarms.getSelectedValue();
        if (e == null) return;

        StringBuilder sb = new StringBuilder();
        sb.append("Type : ")
          .append(e.getSource().getClass().getSimpleName()).append("\n")
          .append("Date : ")
          .append(e.getDatetime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)).append("\n")
          .append("Sévérité : ").append(e.getSeverity()).append("\n")
          .append("Location : ").append(e.getLocation()).append("\n");

        JOptionPane.showMessageDialog(this, sb.toString(),
            "Détails de l'alarme", JOptionPane.INFORMATION_MESSAGE);

        // Enable "Traité" button after showing details
        treatedBtn.setEnabled(true);
    }

    private void markTreated() {
        int idx = alarms.getSelectedIndex();
        if (idx != -1) {
            listModel.remove(idx);
            treatedBtn.setEnabled(false);
        }
    }
}
