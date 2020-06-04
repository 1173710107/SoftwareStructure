/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package circularOrbit;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;



//import P1.graph.Graph;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest {
    

    @Test public void testConcreteVerticesGraph()
    {
 
        String x1 = new String("one");
        String x2 = new String("two");
        String x3 = new String("three");
        Graph<String> graph = new ConcreteVerticesGraph();
        graph.add(x1);
        graph.add(x2);
        graph.add(x3);
        graph.set(x1, x2, 3);
        graph.set(x2, x3, 3);
        Map<String,Integer> map = new HashMap<String,Integer>();
        map.put(x1, 3);
        assertEquals(map,graph.sources(x2));
        map.clear();
        map.put(x3, 3);
        assertEquals(map,graph.targets(x2));
       
    	   
    	  //graph.
    }
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   TODO
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex
    //   TODO
    
    // TODO tests for operations of Vertex
    
}
