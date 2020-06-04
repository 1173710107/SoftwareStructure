/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package circularOrbit;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest {
    

    @Test
    public void Test()
    {
    	Graph<String> test = new ConcreteVerticesGraph();
		test.add("first");
		test.add("second");
		test.add("third");
		test.add("fourth");
		test.add("fourth");
		System.out.println(">>After add four things.");
		assertTrue(test.vertices().contains("third"));
		test.set("first", "second", 1);
		test.set("first", "third", 3);
		test.set("first", "fourth", 4);
		test.set("second", "third", 2);
		test.set("second", "third",1);
		System.out.println(">>After add four edges.");
		assertEquals(3,test.targets("first").size());
		test.remove("first");
		System.out.println(">>remove the first.");
		assertEquals(1,test.sources("third").size());
		test.set("fifth", "sixth", 5);
		test.set("fifth", "sixth", 6);
		System.out.println(">>Add two vertexs while add edge.");
		assertEquals(5,test.vertices().size());
		test.set("second", "third", 0);
		test.set("seventh", "eighth",1);
		System.out.println(">>Remove an edge using set."); 
		assertEquals(1,test.targets("fifth").size());
		assertFalse(test.remove("tenth"));
		System.out.println(test);
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
