package models.enums;

public enum GasType {
    H("Hydrogène"),
    He("Hélium"),
    CO("Monoxyde de carbone"),
    CO2("Dioxyde de carbone"),
    CH4("Méthane"),
    NH3("Ammoniac"),
    NO2("Dioxyde d'azote"),
    SO2("Dioxyde de soufre"),
    O3("Ozone"),
    H2S("Sulfure d'hydrogène");

    private final String label;

    GasType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return label;
    }
}
