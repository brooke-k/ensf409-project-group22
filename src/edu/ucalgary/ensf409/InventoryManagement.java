package edu.ucalgary.ensf409;

public class InventoryManagement {

    private String[][] inventory;

    public InventoryManagement(String[] itemID){
        inventory = new String[itemID.length][2];
        for (int i = 0; i < itemID.length; i++) {
            inventory[i][0] = itemID[0];
        }
    }

    public int getStock(String id){
        for (String[] strings : inventory) {
            if (strings[0].equals(id)) {
                return Integer.parseInt(strings[1]);
            }
        }
        return 0;
    }

    public void setStock(String id, int count){
        for (String[] strings : inventory) {
            if (strings[0].equals(id)) {
                strings[1] = String.valueOf(count);
            }
        }
    }

    public boolean itemExists(String id){
        for(String[] strings : inventory){
            if(strings[0].equals(id)){
                return true;
            }
        }
        return false;
    }
}
