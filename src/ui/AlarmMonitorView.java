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
	private static AlarmMonitorView dataContent;
	private DefaultListModel<AlarmEvent> listModel = new DefaultListModel<>();
    private JList<AlarmEvent> alarms = new JList<>(listModel);
    private JButton detailsBtn = new JButton("Détails");
    private JButton treatedBtn = new JButton("Traité");
    private JButton refreshBtn = new JButton("Refresh");

    public AlarmMonitorView() throws Exception {
        super("Moniteur d'alarmes");
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
        refreshBtn.addActionListener(e -> {
			try {
				refreshAlarmList();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

        JPanel btnPanel = new JPanel();
        btnPanel.add(detailsBtn);
        btnPanel.add(treatedBtn);
        btnPanel.add(refreshBtn);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(sp, BorderLayout.CENTER);
        getContentPane().add(btnPanel, BorderLayout.SOUTH);

        setSize(700, 500);
        setLocation(600, 100);
        dataContent = this;
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
    
    public void refreshAlarmList() throws Exception {
    	listModel.clear();
    	for (Monitor m : ApplicationSystem.getInstance().getMonitors()) {
        	// Create a panel for each monitor
	            for (AlarmEvent e : m.getAlarmEvents()) {
	                listModel.addElement(e);
        	}
        }
    }
    
    public static AlarmMonitorView getDataContent() {
    	return dataContent;
    }
}
