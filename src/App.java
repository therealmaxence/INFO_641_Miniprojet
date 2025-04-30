import models.*;
import models.enums.GasType;
import models.monitors.Monitor;
import models.monitors.MonitorTypeA;
import models.monitors.MonitorTypeB;
import models.sensors.FireSensor;
import models.sensors.GasSensor;
import models.sensors.RadiationSensor;
import models.sensors.Sensor;

public class App {
    public static void main(String[] args) throws Exception {
        // Moniteurs
        Monitor mA = new MonitorTypeA();
        Monitor mB = new MonitorTypeB();

        // Lieux
        Location polytech = new Location("10 route du Pain", "Salle C210", 3);
        Location cantine = new Location("15 route du Pain", "frigo",10);

        // Capteurs
        Sensor s1 = new FireSensor("Capteur de feu", polytech, 80, 70);
        Sensor s2 = new GasSensor("Capteur de gaz", polytech, 30, 10, GasType.CO2);
        Sensor s3 = new RadiationSensor("Capteur de radiation", cantine, 46, 40);

        // Attribution des capteurs aux moniteurs
        mA.listen(s1);
        mA.listen(s2);
        mB.listen(s2);
        mB.listen(s3);

        // Déclenchement de l'évènement
        s2.setValue(31);
        s3.setValue(100);
    }
}