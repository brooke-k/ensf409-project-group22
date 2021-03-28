package edu.ucalgary.ensf409;

/*
    Find the cheapest combination of prices based on the given
    FurnitureConfigurationData file.
 */
public class PriceOptimizer {
    private int partCount;
    private boolean[][] parts;
    private String[] id;
    private int[] price;
    private int min = 0;
    private int[] minIndex = new int[3];
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
        this.partCount = parts[0].length;
    }

    /**
     * optimize() will try to find the optimal configuration of
     * furniture parts for the lowest price based on the
     * current FurnitureConfigurationData.
     */
    public String[] optimize() {
        min = 0;
        for(int p : price){
            min += p;
        }
        int[] indexs = new int[parts.length];
        for (int i = 0; i < parts.length; i++) {
            indexs[i] = i;
            System.out.println(indexs[i]);
        }

        for (int i = 1; i <= partCount; i++) {
            combination(indexs, indexs.length, i);
        }
        String[] ids = new String[minIndex.length];

        for (int i = 0; i < ids.length; i++) {
            ids[i] = id[minIndex[i]];
        }
        return ids;
    }


    private void recursion(int[] arr, int[] data, int start, int end, int index, int r) {

        if (index == r) {
            if(compatible(data)){
                for(int i: data){
                    System.out.print(i+" ");
                }
                System.out.println();
                if(getPrice(data) < min){
                    minIndex = new int[data.length];
                    minIndex = data;
                }
            }
            return;
        }
        for (int i = start; i <= end && end - i + 1 >= r - index; i++) {
            data[index] = arr[i];
            recursion(arr, data, i + 1, end, index + 1, r);
        }

    }

    private void combination(int arr[], int n, int r) {
        int[] data = new int[r];
        recursion(arr, data, 0, n - 1, 0, r);
    }


    /**
     * Compatible checks if a specified list of indices for the boolean
     * array is compatible (will have all parts fulfilled)
     * For instance if the list of specified furniture items has parts[][]
     * as follows,
     * T F F T
     * F T T F
     * it would return true, since it can be fit together to
     * make one complete set of furniture.
     * @param list int[] of the indices to be checked together.
     * @return Boolean, true if compatible, false otherwise.
     */
    private boolean compatible(int[] list){
        boolean[] fulfilledParts = new boolean[partCount];
        boolean ok = true;
        for(int i = 0; i < list.length; i++) {
            int indexNum = list[i];
            for(int j = 0; j < partCount; j++) {
                if(parts[indexNum][j]) {
                    fulfilledParts[j] = true;
                }
            }
        }
        // Make sure whole array is true
        for(int i = 0; i < partCount; i++) {
            if(!fulfilledParts[i]) {
                return false;
            }
        }
        return true;
    }


    private int getPrice(int[] index){
        int sum = 0;
        for (int i = 0; i < index.length; i++) {
            sum += price[index[i]];
        }
        return sum;
    }
    /**
     * parallelSort() will sort the arrays, keeping data in the same order
     */

}
