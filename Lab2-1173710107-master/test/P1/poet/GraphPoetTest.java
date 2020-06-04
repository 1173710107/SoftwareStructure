/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy
    //   TODO
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    @Test public <L> void testGraphPoetTest() throws IOException
    {
    	File file =new File("test/P1/poet/poet1.txt");
    	String input = "Test the system.";
    	GraphPoet<L> graph = new GraphPoet<L>(file);
    	String answer  =graph.poem(input);
    	//System.out.println(answer);
    	boolean result;
    	if(answer.toLowerCase().equals("Test of the system.".toLowerCase()))
    	{
    		result = true;
    	}
    	else 
    	{
    		result = false;
    	}
    	assertEquals(true,result);
   
    	
    }
    // TODO tests
    
}
