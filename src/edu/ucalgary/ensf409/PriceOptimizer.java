package edu.ucalgary.ensf409;

/*
    Find the cheapest combination of prices based on the given
    FurnitureConfigurationData file.
 */
public class PriceOptimizer {
    private FurnitureConfigurationData data;
    private int partCount;

    /**
     * This constructor sets the data value based on the provided
     * Object.
     * @param data data to be set.
     */
    public PriceOptimizer(FurnitureConfigurationData data) {
        this.data = data;
        partCount = data.getParts()[0].length;
    }

    /**
     * optimize() will try to find the optimal configuration of
     * furniture parts for the lowest price based on the
     * current FurnitureConfigurationData.
     */
    public String[] optimize() {
        String[] ids = new String[10];
        return ids;
    }

    /**
     * searchable() will try to find the
     */

}
