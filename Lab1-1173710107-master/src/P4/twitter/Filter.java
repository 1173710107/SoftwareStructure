/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param username
     *            Twitter username, required to be a valid Twitter username as
     *            defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     *         in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
    	List<Tweet> x = new ArrayList<Tweet>();
    	// List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
    	for(int i=0;i<tweets.size();i++)
    	{
    		if(tweets.get(i).getAuthor().equals(username))
    		{
    			x.add(tweets.get(i));
    		}
    	}
    	return x;
        //throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that were sent during a particular timespan.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param timespan
     *            timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     *         in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
    	List<Tweet> x = new ArrayList<Tweet>();
    	for(int i=0;i<tweets.size();i++)
    	{
    		//同时满足两个条件
    		if(tweets.get(i).getTimestamp().isAfter(timespan.getStart())&&tweets.get(i).getTimestamp().isBefore(timespan.getEnd()))
    		{
    			x.add(tweets.get(i));
    		}
    	}
    	return x;
        //throw new RuntimeException("not implemented");
    }

    /**
     * Find tweets that contain certain words.
     * 
     * @param tweets
     *            a list of tweets with distinct ids, not modified by this method.
     * @param words
     *            a list of words to search for in the tweets. 
     *            A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when 
     *         represented as a sequence of nonempty words bounded by space characters 
     *         and the ends of the string) includes *at least one* of the words 
     *         found in the words list. Word comparison is not case-sensitive,
     *         so "Obama" is the same as "obama".  The returned tweets are in the
     *         same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
    	String x = null;
    	String []sp = null;
    	List<Tweet> y = new ArrayList<Tweet>();
    	int j =0,k=0;
    	for(int i=0;i<tweets.size();i++)
    	{
    		x = tweets.get(i).getText();
    		sp = x.split(" ");
    		for(j=0;j<sp.length;j++)
    		{
    			for(k=0;k<words.size();k++)
    			{
    				if(sp[j].equalsIgnoreCase(words.get(k)))
        			{
        				y.add(tweets.get(i));
        			}
    			}
    		}
    	}
    	return y;
        //throw new RuntimeException("not implemented");
    }

}
