/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JOptionPane;

import P1.graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 * @param <L>
 */
public class GraphPoet<L> {
    
    private final Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //   TODO
    // Representation invariant:
    //   TODO
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
    	   try {
	        	BufferedReader br = new BufferedReader(new FileReader(corpus));//读取文件
	        	//System.out.println("文件读入");
	            String line = null;
	            String []sp = null;
	            while((line=br.readLine())!=null) {//按行读取
	            	line = line.toLowerCase();
	            	sp = line.split(" ");
	            	for(int i=0;i<sp.length;i++)
	            	{
	            		graph.add(sp[i]);
	            	}
	            	for(int i=0;i<sp.length-1;i++)
	            	{
	            		graph.set(sp[i], sp[i+1], 2);
	            	}
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
       // throw new RuntimeException("not implemented");
    }
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
    	 String []sp = null;
    	/* for(String s:graph.vertices())
    	 System.out.println(s);*/
    	 ArrayList<String> answer = new ArrayList<String>();
    	 input = input.toLowerCase();
    	 sp = input.split(" ");
    	 for(int i=0;i<sp.length-1;i++)
    	 {
    		 String x = sp[i];
    		 answer.add(x);
    	//	 System.out.println(x);
    		 String next = sp[i+1];
    		 Map<String, Integer> sources = new HashMap<String, Integer>();
    		 sources = graph.targets(x);
    		 Set<String> set = sources.keySet(); //得到所有key的集合
    		 outterLoop : for (String in : set) 
    		 {
    			 Map<String, Integer> sources1 = new HashMap<String, Integer>();
    			 sources1 = graph.targets(in);
    			 Set<String> set1 = sources1.keySet();
    			 for(String in1 :set1)
    			 {
    				/* System.out.println(x+" "+next);
    				 System.out.println(in1+"!!!");*/
    				 if(in1.equals(next))
    				 {
    					 answer.add(in);
    				//	System.out.println(in);
    					 break outterLoop;//跳出循环
    				 }
    			 }
    		 }
    		 
    		 
    	 }
    	 answer.add(sp[sp.length-1]);
    	 String s = answer.get(0)+" " ;
    	 for(int i=1;i<answer.size();i++){
    	 s+=answer.get(i)+" ";
    	 }
    	 s =s.substring(0,s.length()-1);//去掉最后多出来的逗号。
    	 return s;
       // throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    
}
