/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
    	 //public static Set<String> getMentionedUsers(List<Tweet> tweets
    	//Set [] answer = new Set[tweets.size()];
    	List<Set<String>> answer = new ArrayList<Set<String>>();
    	
    	int i=0,count =0,z=0;
    	//String [] str2 = new String[tweets.size()];
    	for(int k=0;k<tweets.size();k++)
    	{
    		count = 0;
    		Set<String> str2 = new HashSet<String>();
    		for(i=0;i<tweets.size();i++)
        	{
        		String []m  =null;
        		String sp = tweets.get(i).getText();
        		if(sp.indexOf("@")>=0)
        		{
        			m = sp.split("@");
            		if(m.length==1)
            		{
            				if(tweets.get(k).getAuthor().equalsIgnoreCase(m[0]))
            				{
            					str2.add(sp);
            					//count++;
            				}
            		}
            		else
            		{
            				if(tweets.get(k).getAuthor().equalsIgnoreCase(m[1]))
            				{
            					str2.add(sp);
            					//count++;
            				}
            		}
        		}
        	}
    		if(str2.size()!=0)
    		{
    			answer.add(str2);
    		}
    		else
    		{
    			Set<String> str4 = new HashSet<String>();
    			answer.add(str4);
    		}
    	}
    	Map<String, Set<String>> map  = new HashMap<String, Set<String>>();
    	for(i=0;i<tweets.size();i++)
    	{
    		map.put(tweets.get(i).getAuthor(), answer.get(i));
    	
    	}
    	return map;
       // throw new RuntimeException("not implemented");
        
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
    //	int []m  =new  int[followsGraph.size()] ;
    	//int i=0;
    	/*List<String> list = new ArrayList<String>();
    	for ( Set<String> v :followsGraph.values()) {
           m[i] =  v.size();
           i++;
        }*/
    	/* List<Map.Entry<String,Set<String>>> list = new ArrayList<Map.Entry<String,Set<String>>>(followsGraph.list());
         //然后通过比较器来实现排序
         Collections.sort(list,new Comparator<Map.Entry<String,String>>() {
             //升序排序
             public int compare(Entry<String, String> o1,
                     Entry<String, String> o2) {
                 return o1.getValue().compareTo(o2.getValue());
             }
          */   
    	
    	Map<String,Integer> x=new HashMap<String,Integer>();
    	String name = null;
    	int num = 0;
	  	for(Map.Entry<String, Set<String>> y : followsGraph.entrySet())
	  	{
	  		name = y.getKey();
	  		num = y.getValue().size();
	  		x.put(name, num);
	  	}
	  	List<Map.Entry<String, Integer>> list  = new ArrayList<Map.Entry<String, Integer>>(x.entrySet());	
       Collections.sort(list,new Comparator<Map.Entry<String, Integer>>() {
        	public int compare(Map.Entry<String, Integer> o1,Map.Entry<String, Integer> o2){
        		return o2.getValue()-o1.getValue();
        	}
		});
       ArrayList<String> list1 = new ArrayList<String>();
       for(Map.Entry<String, Integer> y : x.entrySet())
       {
    	   list1.add(y.getKey());
       }
       return list1;
       // throw new RuntimeException("not implemented");
    }
    public static Map<String, Set<String>> guessFollowsGraph1( Map<String, Set<String>> followsGraph )
    {
    	 Map<String, Set<String>> answer = new  HashMap<String,Set<String>>();
    	 for(Map.Entry<String, Set<String>> entry : followsGraph.entrySet()){  
    		    //System.out.print("Key = "+entry.getKey()+",value="+entry.getValue()); 
    		 	Set<String> x=  new HashSet<String>();
    		 	for(String p:entry.getValue())
    		 	{
    		 		x.add(p);
    		 		 for(Map.Entry<String, Set<String>> entry1 : followsGraph.entrySet())
    		 		 {
    		 			 if(p.equals(entry1.getKey()))
    		 			 {
    		 				for(String p1:entry1.getValue())
    		 				{
    		 					if(!p1.equals(entry.getKey()))
    		 					x.add(p1);
    		 				}
    		 			 }
    		 		 }
    		 	}
    		 	answer.put(entry.getKey(), x);	
    		}  
    	 return answer;
    	 
    }

}
