package models;

public class Location {
    private String adress;
    private String preciseLoc;
    private int criticity; // Max criticity is 10

    public Location(String adress, String preciseLoc, int criticity) {
        this.adress = adress;
        this.preciseLoc = preciseLoc;
        this.setCriticity(criticity);
    }

    public Location(String adress, int criticity) {
        this(adress, null, criticity);
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getPreciseLoc() {
        return preciseLoc;
    }

    public void setPreciseLoc(String preciseLoc) {
        this.preciseLoc = preciseLoc;
    }

    public int getCriticity() {
        return criticity;
    }

    public void setCriticity(int criticity) {
        if (criticity < 0 || criticity > 10) {
            throw new IllegalArgumentException("Criticity must be between 0 and 10");
        }
        this.criticity = criticity;
    }

    @Override
    public String toString() {
        return "Adress: " + adress + ", Precise Location: " + preciseLoc + ", Criticity: " + criticity;
    }
}
