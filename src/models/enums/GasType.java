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

    /**
     * Constructs a GasType enum constant with the specified label.
     *
     * @param label the human-readable label associated with the gas type
     */
    GasType(String label) {
        this.label = label;
    }

    /**
     * Returns the human-readable label associated with the gas type.
     *
     * @return the label of the gas type
     */
    public String getLabel() {
        return label;
    }

    /**
     * Returns the GasType enum constant corresponding to the specified label.
     *
     * @param label the label of the gas type
     * @return the GasType enum constant, or null if not found
     */
    @Override
    public String toString() {
        return label;
    }
}
