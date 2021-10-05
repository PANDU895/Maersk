package com.order.engine.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.order.engine.CaleculateOrderAmount;

public class CaleculateOrderAmountTest {
	
	Map<String, Integer> inputMap = new HashMap<>(); 

	@Test  
    public void testFindMax(){  
		Map<String, Integer> inputMap = new HashMap<>();
		inputMap.put("A", 5);
		inputMap.put("B", 5);
		inputMap.put("C", 1);
        assertEquals(370,CaleculateOrderAmount.caleculateTotal(inputMap));  
    }  
}
