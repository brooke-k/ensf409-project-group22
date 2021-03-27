package edu.ucalgary.ensf409;

/*
    FurnitureConfigurationData contains two parallel arrays, one with
    the specified furniture ID and one with the specified parts for a
    specific furniture configuration. For instance
     id[0] = D0890, boolean[0] = {F, F, T}, price[0] = 25
 */
public class FurnitureConfigurationData {
    private boolean[][] parts;
    private String[] id;
    private int[] price;


    /**
     * FurnitureConfigurationData(String id, boolean[][] parts) will construct
     * the FurnitureConfigurationData object with the given data.
     * @param id String for id of the specific piece of furniture
     * @param parts 2D boolean array of parts list
     */
    public FurnitureConfigurationData(String[] id, boolean[][] parts, int[] price) {
        this.id = id;
        this.parts = parts;
        this.price = price;
    }

    public boolean[][] getParts() {
        return parts;
    }

    public String[] getId() {
        return id;
    }

    public int[] getPrice() {
        return price;
    }
}
