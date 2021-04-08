package edu.ucalgary.ensf409;
import org.junit.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.Assert.*;

public class PriceOptimizerTest {
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

    @Test
    public void testPriceOptimizer_testOptimize() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, true, false, true},

        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(1);
        String[] expected = {"C9890", "C0942"};
        String[] expected2 = {"C0942", "C9890"};
        for(int i = 0; i < 2; i++) {
            System.out.println(result[i] + " ");
        }

        assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(expected2, result));
    }

    @Test
    public void testPriceOptimizer_testOptimizeImpossible() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, true, true},
                {true, false, false, false},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(1);
        assertNull(result);
    }

    @Test
    public void testPriceOptimizer_testOptimize2() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, true, true, true},
                {true, true, true, true},
                {false, false, true, false},
                {false, false, false, true}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748"};
        String[] expected2 = {"C6748", "C0942"};
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(result, expected));
    }

    @Test
    public void testPriceOptimizer_testOptimize3() {
        String[] id  = {"C0942", "C6748", "C8138", "C9890"};
        int[] price = {100, 75, 75, 50};
        boolean[][] parts = {
                {true, false, false, true},
                {false, true, true, true},
                {false, true, false, false},
                {true, true, true, false}
        };
        PriceOptimizer p = new PriceOptimizer(id,parts,price);
        String[] result = p.optimize(2);
        String[] expected = {"C0942", "C6748", "C9890"};
        for(int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
        assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(result, expected));
    }

    /**
     *testPriceOptimizer_testConstructor1 assets if the Constructor is working
     * as intended. It sets the getParts, getID and the getPrice methods.
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
     * testPriceOptimizer_testSortOnPrice asserts that after the arrays are
     * sorted by price all the linked arrays changed as well. This method
     * primarily tests the constructor and the sortOnPrice method.
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
}
