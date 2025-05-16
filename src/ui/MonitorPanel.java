package ui;

import models.monitors.Monitor;
import models.AlarmEvent;
import models.ApplicationSystem;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.List;


public class MonitorPanel extends JPanel {
    private Monitor monitor;
    private DefaultListModel<AlarmEvent> model = new DefaultListModel<>();
    private JList<AlarmEvent> alarmList = new JList<>(model);
    private JButton detailsBtn = new JButton("Détails");
    private JButton treatedBtn = new JButton("Traité");
    private JDialog detailsDialog;

    public MonitorPanel(Monitor monitor) {
        this.monitor = monitor;
        setLayout(new BorderLayout());

        // Populate events: only unhandled
        for (AlarmEvent ev : monitor.getAlarmEvents()) {
            if (!ev.isTreated()) model.addElement(ev);
        }

        alarmList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scroll = new JScrollPane(alarmList);
        add(scroll, BorderLayout.CENTER);

        // Button panel
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        detailsBtn.setEnabled(false);
        treatedBtn.setEnabled(false);
        btnPanel.add(detailsBtn);
        btnPanel.add(treatedBtn);
        add(btnPanel, BorderLayout.SOUTH);

        // List selection enables details
        alarmList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                boolean sel = alarmList.getSelectedIndex() != -1;
                detailsBtn.setEnabled(sel);
            }
        });

        // Action listeners
        detailsBtn.addActionListener(e -> showDetails());
        treatedBtn.addActionListener(e -> markTreated());
    }

    private void showDetails() {
        AlarmEvent ev = alarmList.getSelectedValue();
        if (ev == null) return;

        // Build details message
        String msg = String.format(
            "Type : %s\nDate : %s\nSévérité : %d\nLocation : %s",
            ev.getSource().getClass().getSimpleName(),
            ev.getDatetime().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            ev.getSeverity(),
            ev.getLocation().toString()
        );

        // Create or reuse dialog
        if (detailsDialog != null && detailsDialog.isShowing()) {
            detailsDialog.dispose();
        }
        detailsDialog = new JDialog(SwingUtilities.getWindowAncestor(this), "Détails de l'alarme", Dialog.ModalityType.APPLICATION_MODAL);
        JTextArea text = new JTextArea(msg);
        text.setEditable(false);
        detailsDialog.getContentPane().add(new JScrollPane(text));
        detailsDialog.setSize(300,200);
        detailsDialog.setLocationRelativeTo(this);
        detailsDialog.setVisible(true);

        // Mark as details viewed
        ev.setDetailsViewed(true);
        treatedBtn.setEnabled(true);
    }

    private void markTreated() {
        AlarmEvent ev = alarmList.getSelectedValue();
        if (ev != null && ev.isDetailsViewed()) {
            ev.setTreated(true);
            model.removeElement(ev);
            treatedBtn.setEnabled(false);
            detailsBtn.setEnabled(false);
        }
    }
}
