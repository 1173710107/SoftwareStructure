package P4.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MySocialNetworkTest {
	public static void main(String args[])
	{
		 Map<String, Set<String>> followsGraph = new  HashMap<String,Set<String>>();
		 String a = new String("Bob");
		 String b = new String("alice");
		 String c = new String("tony");
		 String d = new String("curry");
		 String e = new String("green");
		 String f = new String("thompson");
		 String g = new String("couins");
		 Set<String> x1=  new HashSet<String>();
		 Set<String> x2=  new HashSet<String>();
		 Set<String> x3=  new HashSet<String>();
		 Set<String> x4=  new HashSet<String>();
		 Set<String> x5=  new HashSet<String>();
		 Set<String> x6=  new HashSet<String>();
		 Set<String> x7=  new HashSet<String>();  
		 x1.add(b);
		 x1.add(d);
		 x1.add(f);
		 x2.add(a);
		 x2.add(f);
		 x3.add(e);
		 x3.add(g);
		 x4.add(a);
		 x5.add(c);
		 x6.add(a);
		 x6.add(b);
		 x7.add(c);
		 followsGraph.put(a, x1);
		 followsGraph.put(b, x2);
		 followsGraph.put(c, x3);
		 followsGraph.put(d, x4);
		 followsGraph.put(e, x5);
		 followsGraph.put(f, x6);
		 followsGraph.put(g, x7);
		 Map<String, Set<String>> answer = new  HashMap<String,Set<String>>();
		 answer  = SocialNetwork.guessFollowsGraph1(followsGraph);
		 for(Entry<String, Set<String>> entry : answer.entrySet()){  
			    System.out.println(entry.getKey()+"对下面的人有影响：");  
			    for(String p:entry.getValue())
			    {
			    	System.out.println(p);
			    }
			}  
	}
	 
	 
	 
}
