package edu.ucalgary.ensf409;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class PriceOptimizerTest {
    /**
     * testPriceOptimizer_compatibleValid asserts that the object PriceOptimizer
     * recognizes that all pieces provided are compatible to create a complete
     * furniture item.
     */
    @Test
    public void testPriceOptimizer_compatibleValid() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, false, true},
                {true, false, true, false},
                {false, true, false, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(1);
        assertTrue(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleInvalid asserts that object Price
     * Optimizer correctly recognizes when available pieces cannot create
     * a full furniture item.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, false, true},
                {true, false, false, false},
                {false, true, false, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(1);
        assertFalse(p.compatible(list));
    }


    /**
     * testPriceOptimizer_compataibleInvalidSmall asserts that a Price
     * Optimizer object recognizes the available pieces for one full
     * furniture item based on a very limited size of parts (1).
     */
    @Test
    public void testPriceOptimizer_compatibleInvalidSmall() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, false, true}
        };
        int[] list = {0};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(1);
        assertFalse(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleInvalid2Items() checks to see if
     * a given parts list will return the right value when it is NOT
     * possible to make 2 furniture items (should return false).
     * In this configuration, it is possible to make one furniture
     * item (but not 2) based on 3 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid2Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, false},
                {false, true, false, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(2);
        assertFalse(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleValid2Items() checks to see if
     * a given parts list will return the right value when it IS possible
     * to make 2 furniture items with the given configuration list
     * (should return true).
     * In this configuration, it is possible to make 2 furniture
     * items from 3 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleValid2Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, false},
                {false, true, true, true}
        };
        int[] list = {0,1,2};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(2);
        assertTrue(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleValid3Items() checks to see if
     * a given parts list will return the right value when it IS possible
     * to make 3 furniture items with the given configuration list
     * (should return true).
     * In this configuration, it is possible to make 3 furniture
     * items from 4 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleValid3Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, true, false}
        };
        int[] list = {0,1,2,4};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(3);
        assertTrue(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleInvalid3Items() checks to see if
     * a given parts list will return the right value when it is NOT
     * possible to make 2 furniture items (should return false).
     * In this configuration, it is possible to make 1 or 2 furniture
     * items but not 3 from 4 pieces of furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleInvalid3Items() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, true, false}
        };
        int[] list = {0,1,2,3};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(3);
        assertFalse(p.compatible(list));
    }

    /**
     * testPriceOptimizer_compatibleValid3ItemsSimple() checks to see if
     * a given parts list will return the right value when it IS possible
     * to make 3 furniture items with the given configuration list
     * (should return true) based on a very simple configuration
     * where the 3 specified furniture items are complete and can make
     * 3 sets of new furniture.
     */
    @Test
    public void testPriceOptimizer_compatibleValid3ItemsSimple() {
        String[] id  = {};
        int[] price = {};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, true, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, true, true},
                {true, true, true, false},
                {true, true, true, true},
                {true, true, true, true}
        };
        int[] list = {4,6,7};
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        p.setItemCount(3);
        assertTrue(p.compatible(list));
    }

    /**
     * testPriceOptimizer_testOptimize asserts that the object Price
     * Optimizer can correctly produce an order with a combination of
     * pieces that total to the lowest possible cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, false, true},

        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(1);
        String[] expected = {"C9890", "C0942"};
        String[] expected2 = {"C0942", "C9890"};
        for (int i = 0; i < 2; i++) {
            System.out.println(result[i] + " ");
        }

        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(expected2, result));
    }

    /**
     * testPriceOptimizer_testPriceAccurate will run optimize(1) and
     * check if the price matches the expected price based on the furniture
     * and price configuration.
     */
    @Test
    public void testPriceOptimizer_testOptimizePriceAccurate() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, false, true},

        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(1);
        int priceResult = p.getCurrentCost();
        Assert.assertEquals(150, priceResult);
    }

    /**
     * testPriceOptimizer_testOptimizeImpossible tests that the
     * optimize() method will return null upon invalid values
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimizeImpossible() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        p.optimize(1);
    }

    /**
     * testPriceOptimizer_testOptimize2 provides a second variation of
     * testPriceOptimizer_testOptimize, testing that it is possible to make
     *      * 2 furniture items based on a different configuration of parts.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, true, true, true},
                {true, true, true, true},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748"};
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimize2PriceAccurate will run optimize(2) and
     * check if the price matches the expected price based on the furniture
     * and price configuration.
     */
    @Test
    public void testPriceOptimizer_testOptimize2PriceAccurate() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, true, true, true},
                {true, true, true, true},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        int priceResult = p.getCurrentCost();
        Assert.assertEquals(175, priceResult);
    }

    /**
     * testPriceOptimizer_testOptimize2v1 provides a second variation of
     * testPriceOptimizer_testOptimize2, testing that it is possible to make
     * 2 furniture items based on a different configuration of parts.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimize2v1() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        PriceOptimizer p = new PriceOptimizer(id, parts, price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748", "C9890"};
        for (String s : result) {
            System.out.print(s + " ");
        }
        Assert.assertTrue("Output array incorrect.",
                Arrays.equals(expected, result) ||
                        Arrays.equals(result, expected));
    }

    /**
     * testPriceOptimizer_testOptimizeImpossibleLarge provides test of the
     * optimize method, testing that it will return null when it is
     * impossible to create a particular number of furniture items.
     * In this case, it is possible to make 2 but impossible to make 3
     * with any configuration available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimizeImpossibleLarge() {
        String[] id  = {"1","2","3","4","5","6","7","8"};
        int[] price = {100, 75, 75, 50, 75, 75, 100, 50};
        boolean[][] parts = {
                {true, true, false, true},
                {true, false, false, true},
                {false, true, true, true},
                {true, false, false, false},
                {true, true, false, true},
                {true, true, false, false},
                {true, true, false, true},
                {true, true, true, true}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        assertNull(p.optimize(3));
    }

    /**
     * testPriceOptimizer_testOptimizeImpossibleSmall provides test of the
     * optimize method, testing that it will return null when it is
     * impossible to create a particular number of furniture items.
     * In this case, it is possible to make 2 but impossible to make 3
     * with any configuration available.
     * <p>
     * Asserts that object Price Optimizer produces an order that completes
     * a furniture item with a combination of pieces that has the lowest
     * total cost.
     */
    @Test
    public void testPriceOptimizer_testOptimizeImpossibleSmall() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, false, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(2);
        assertNull(result);
    }

    /**
     *testPriceOptimizer_testConstructor1 assets if the Constructor is working
     * as intended. It sets the getParts, getID and the getPrice methods.
     */
    @Test
    public void testPriceOptimizer_testConstructor1() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        int[] price = {100, 75, 75, 50};
        PriceOptimizer p = new PriceOptimizer (id,parts,price);
        assertTrue("Constructor failed.", Arrays.equals(id, p.getId()) &&
                Arrays.equals(parts, p.getParts()) && Arrays.equals(price, p.getPrice()));
    }


    /**
     * testPriceOptimizer_testSortOnPrice asserts that after the arrays are
     * sorted by price all the linked arrays changed as well. This method
     * primarily tests the constructor and the sortOnPrice method.
     */
    @Test
    public void testPriceOptimizer_testSortOnPrice() {
        String[] id = {"C0942", "C6748", "C8138", "C9890"};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        int[] price = {100, 75, 75, 50};
        String[] sortedID = {"C9890", "C8138","C6748","C0942" };
        boolean [][] sortedParts =  {
                {true, true, true, false},
                {false, true, false, false},
                {false, true, true, true},
                {true, false, false, true}
        };
        int[] sortedPrice = {50, 75, 75, 100};
        PriceOptimizer p = new PriceOptimizer (id,parts,price);
        p.sortOnPrice();

        assertTrue("Sort failed.", Arrays.equals(sortedID, p.getId()) &&
                Arrays.equals(sortedParts[0], p.getParts()[0]) && Arrays.equals(sortedParts[1], p.getParts()[1]) &&
                Arrays.equals(sortedParts[2], p.getParts()[2]) && Arrays.equals(sortedParts[3], p.getParts()[3]) &&
                Arrays.equals(sortedPrice, p.getPrice()));
    }
}

