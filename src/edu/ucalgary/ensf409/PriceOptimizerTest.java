package edu.ucalgary.ensf409;
import org.junit.*;

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
        String[] result = p.optimize();
        String[] expected = {"C9890", "C0942"};
        String[] expected2 = {"C0942", "C9890"};
        for(int i = 0; i < 2; i++) {
            System.out.println(result[i] + " ");
        }

        assertTrue("Output array incorrect.", Arrays.equals(expected, result) || Arrays.equals(expected2, result));
    }
}
