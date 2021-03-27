package edu.ucalgary.ensf409;

public class InventoryManagement {

    private String[][] inventory;

    /**
     *  Takes in an array of itemID and creates a 2d inventory array accordingly.
     * @param itemID Array of ID's of all items
     */
    public InventoryManagement(String[] itemID){
        inventory = new String[itemID.length][2];
        for (int i = 0; i < itemID.length; i++) {
            inventory[i][0] = itemID[0];
        }
    }

    /**
     * Returns the stocks for a particular id.
     * @param id The id of the item
     * @return amount of stock in hand
     */
    public int getStock(String id){
        for (String[] strings : inventory) {
            if (strings[0].equals(id)) {
                return Integer.parseInt(strings[1]);
            }
        }
        return 0;
    }

    /**
     * Updates the stock for an item.
     * @param id The selected id to change the stock of.
     * @param count The new stock count for the item.
     */
    public void setStock(String id, int count){
        for (String[] strings : inventory) {
            if (strings[0].equals(id)) {
                strings[1] = String.valueOf(count);
            }
        }
    }

    /**
     * Checks through the array if the item exists.
     * We shouldn't need to use this method.
     * @param id The ID of the item which need to be checked.
     * @return True of false, depending on if the item exists.
     */
    public boolean itemExists(String id){
        for(String[] strings : inventory){
            if(strings[0].equals(id)){
                return true;
            }
        }
        return false;
    }
}
