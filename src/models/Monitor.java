package models;

public class Monitor implements ISensorListener {
    @Override
    public void dangerDetected(AlarmEvent e) {
        System.out.println("Location : (" + e.getLocation() + "), Date : " + e.getDatetime() + ", Severity :" + e.getSeverity());
    }
}
