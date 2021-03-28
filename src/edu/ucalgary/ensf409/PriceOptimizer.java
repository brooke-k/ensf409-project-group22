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
        }
        for (int i = 1; i < partCount; i++) {
            combination(indexs, i);
        }

        String[] ids = new String[partCount];
        return ids;
    }

    private void recursion(int[] array, int[] data, int start, int end, int index, int r){

        if(index == r){
            if(compatible(data)){
                //WOO
            }
        }

        for (int i = start; i <= end && (end + 1 - i) >= (r - index); i++) {
            data[index] = array[i];
            recursion(array, data, i+1, end, index+1, r);
        }

    }

    private void combination(int[] array, int comb){
        int[] data = new int[comb];
        recursion(array, data, 0, data.length-1, 0, comb);
    }


    /**
     * @param index
     * @return
     */
    private boolean compatible(int[] index){
        boolean[] comp = new boolean[partCount];
        //do later
        return false;
    }

    /**
     * parallelSort() will sort the arrays, keeping data in the same order
     */

}
