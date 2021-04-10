package edu.ucalgary.ensf409;
/*
    PriceOptimizer contains three parallel arrays, one with
    the specified furniture ID and one with the specified parts for a
    specific furniture configuration. For instance
     id[0] = D0890, boolean[0] = {F, F, T}, price[0] = 25
     And provides methods for optimization of the price.
 */
public class PriceOptimizer {
    private int partCount;
    private boolean[][] parts;
    private String[] id;
    private int[] price;
    private int[] minIndex = null;
    private int currentCost = 0;
    private int itemCount;
    /**
     * PriceOptimizer will construct
     * the PriceOptimizer object with the given data.
     * @param id String for id of the specific piece of furniture
     * @param parts 2D boolean array of parts list
     */
    public PriceOptimizer(String[] id, boolean[][] parts, int[] price) {
        this.id = id;
        this.parts = parts;
        this.price = price;
        if(parts != null) {
            this.partCount = parts[0].length;
        }
    }

    /**
     * Getter method for the available furniture parts
     * of a certain type
     * @return boolean 2D array of available furniture parts
     */
    public boolean[][] getParts() {
        return parts;
    }

    /**
     * Getter method for the ID of furniture of
     * a certain type
     * @return String array of furniture ID
     */
    public String[] getId() {
        return id;
    }

    /**
     * Getter method for the price of furniture of
     * a certain type
     * @return int array of furniture price
     */
    public int[] getPrice() {
        return price;
    }

    /**
     * getCurrentCost returns the minimum price in the object
     * @return minimum price as an integer
     */
    public int getCurrentCost() {
        return currentCost;
    }

    /**
     * optimize() will try to find the optimal configuration of
     * furniture parts for the lowest price based on the
     * current FurnitureConfigurationData.
     */
    public String[] optimize(int count) {
        itemCount = count;
        // Optimize
        currentCost = 0;
        for(int p : price){
            currentCost += p;
        }

        int[] indexs = new int[parts.length];
        for (int i = 0; i < parts.length; i++) { //index stores the index of all the parts
            indexs[i] = i;
        }

        for (int i = 1; i <= parts.length; i++) {
            int[] data = new int[i];        //makes an array to store the compatible data
            recursion(indexs, data, 0, indexs.length-1, 0, i); //calls recursion
        }

        String[] ids = null;            //sets returning string as null incase there wasnt a match
        if(minIndex != null){
            ids = new String[minIndex.length];
            for (int i = 0; i < ids.length; i++) {
                ids[i] = id[minIndex[i]];
            }

        }

        return ids;
    }

    /**
     * The main recursive function of price optimizer, searches through all the
     * combination to find which set of parts are the cheapest.
     *
     * @param arr Stores the indexs of all the parts from inventory
     * @param data Holds the array to check with compatible and getPrice method
     *             to check if the current combination is the cheapest.
     * @param start Holds the starting index of the recursion.
     * @param end Hold the end index for the current combination loop, AKA
     *            the size of the parts list.
     * @param index Holds the current starting index of the recursion as it,
     *              iterates trough all the possible combination.
     * @param r R sets the combination length for all the possibilities,
     *          it starts at 1 and goes until inventory length - 1.
     */
    private void recursion(int[] arr, int[] data, int start,
                           int end, int index, int r) {

        if (index == r) {
            if(compatible(data)){
                if(getPrice(data) < currentCost){
                    currentCost = getPrice(data);
                    minIndex = new int[data.length];
                    System.arraycopy(data, 0, minIndex, 0, data.length);
                }
            }
            return;
        }
        //
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            recursion(arr, data, i + 1, end, index + 1, r);
        }
    }

    /**
     * Compatible checks if a specified list of indices for the boolean
     * array is compatible (will have all parts fulfilled) for the given number
     * of parts specified in itemCount
     * For instance if the list of specified furniture indices of parts[][]
     * as follows,
     * T F F T
     * F T T F
     * it would return true, since it can be fit together to
     * make one complete set of furniture.
     * @param list int[] of the indices to be checked together.
     * @return Boolean, true if compatible, false otherwise.
     */
    public boolean compatible(int[] list) {
        int[] fulfilledParts = new int[partCount];
        for (int indexNum : list) {
            for (int j = 0; j < partCount; j++) {
                if (parts[indexNum][j]) {
                    fulfilledParts[j]++;
                }
            }
        }
        // Make sure whole array is true
        for (int i = 0; i < partCount; i++) {
            if(fulfilledParts[i] < itemCount){
                return false;
            }
        }
        return true;
    }

    /**
     * getPrice will return the int of a given series of indices
     * in the list
     * @param index Indices to be checked
     * @return The price of the given series of indices combined
     */
    private int getPrice(int[] index){
        int sum = 0;
        for (int j : index) {
            sum += price[j];
        }
        return sum;
    }

    /**
     * sortOnPrice() will sort the arrays based on price,
     * keeping data in the same order
     * for all the arrays using bubble sort.
     */
    public void sortOnPrice() {
        for(int i = 0; i < price.length; i++) {
            for(int j = i; j < price.length; j++) {
                if(price[i] > price[j]) {
                    // Sort price
                    int temp = price[i];
                    price[i] = price[j];
                    price[j] = temp;
                    // Sort parts
                    boolean[] tempBool = parts[i];
                    parts[i] = parts[j];
                    parts[j] = tempBool;
                    // Sort id
                    String tempID = id[i];
                    id[i] = id[j];
                    id[j] = tempID;
                }
            }
        }
    }

    /**
     * copyOf makes a direct copy of a boolean array.
     * @return boolean[][]
     */
    private boolean[][] copyOf(boolean[][] array){
        boolean[][] newArray = new boolean[array.length][array[0].length];
        for (int i = 0; i < array.length; i++) {
            System.arraycopy(array[i], 0, newArray[i], 0, array[0].length);
        }
        return newArray;
    }

    /**
     * setter for itemCount
     * @param itemCount for the variable to be set in itemCount
     */
    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
