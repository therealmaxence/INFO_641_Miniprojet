import models.*;
import models.enums.GasType;

public class App {
    public static void main(String[] args) {
        // Moniteurs
        Monitor mA = new Monitor();
        Monitor mB = new Monitor();

        // Lieux
        Location polytech = new Location("10 route du Pain", "Salle C210", 3);
        Location cantine = new Location("15 route du Pain", "frigo",10);

        // Capteurs
        Sensor s1 = new FireSensor("Capteur de feu", polytech, 80, 70);
        Sensor s2 = new GasSensor("Capteur de gaz", polytech, 30, 10, GasType.CO2);
        Sensor s3 = new RadiationSensor("Capteur de radiation", cantine, 46, 40);

        // Attribution des capteurs aux moniteurs
        s1.addListener(mA); // MA -> Fire
        s2.addListener(mA); // MA -> Gas

        s2.addListener(mB); // MB -> Gas
        s3.addListener(mB); // MB -> Radiation

        // Déclenchement de l'évènement
        s2.setValue(31);
        s3.setValue(100);
    }
}