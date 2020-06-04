/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    @Test public void testConcreteEdgesGraph()
    {
        String x1 = new String("one");
        String x2 = new String("two");
        String x3 = new String("three");
        Graph<String> graph = emptyInstance();
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
        map.clear();
        graph.remove(x3);
       assertEquals(true,graph.targets(x2).isEmpty());

    }
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   TODO
    //此处自己先构造一个graph，然后
    // TODO tests for ConcreteEdgesGraph.toString()
    @Test public void testtoString()
    {
    	   Graph<String> graph = emptyInstance();
    	   String x1 = new String("one");
    	   graph.add(x1);
    	   //assertEquals("P1.graph.ConcreteEdgesGraph@"+Integer.graph.toHexString(hashCode(),graph.toString());
    }
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //   TODO
    
    // TODO tests for operations of Edge
    
}
