package ui;

import javax.swing.*;
import java.awt.*;

public class ConsolePanel extends JPanel {
    private static JTextArea consoleArea;

    public ConsolePanel() {
    	consoleArea = new JTextArea();
		consoleArea.setEditable(false);
        setLayout(new BorderLayout());
        add(new JScrollPane(consoleArea), BorderLayout.CENTER);
    }
    
    public static void appendMessage(String message) {
    	consoleArea.append(message);
    }
}