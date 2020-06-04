/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.time.Instant;
/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
    	Instant maxtime = tweets.get(0).getTimestamp(),mintime = tweets.get(1).getTimestamp();
    	for(int i=0;i<tweets.size();i++)
    	{
    		if( tweets.get(i).getTimestamp().isBefore(mintime) )
    		{
    			mintime = tweets.get(i).getTimestamp();//保留最早时间
    		}
    		if(tweets.get(i).getTimestamp().isAfter(maxtime))
    		{
    			maxtime  = tweets.get(i).getTimestamp();//保留最晚时间
    		}
    	}
    	Timespan x  = new Timespan(mintime,maxtime);//存储最早最晚时间
    	
    	return x;
       // throw new RuntimeException("not implemented");
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static boolean isNumeric(String str){
		for(int i=str.length();--i>=0;){
		int chr=str.charAt(i);
		if(!((chr>65&&chr<90)||(chr>97&&chr<122)||(chr==95)||(chr==45)))//根据用户名规则过滤字符串
		return false;
		}
		return true;
	}
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
    	Set<String> answer = new HashSet<String>();
    	int k=0;
    	for(int i=0;i<tweets.size();i++)
    	{
    		String line = tweets.get(i).getText();
    		String [] sp = line.split(" ");
    		for(int n=0;n<sp.length;n++)
    		{
    			if(sp[n].indexOf("@")>=0)
        		{
        			String [] m = sp[n].split("@");
            		if(m.length==1)
            		{
            		//	for(k=0;k<tweets.size();k++)
            			//{
            				if( isNumeric(m[0]))
            				{
            					//if(tweets.get(k).getAuthor().equalsIgnoreCase(m[0]))
                				//{
                					answer.add(m[0]);	
                				//}
            				}
            			//}
            		}
            		else
            		{
            			//for(k=0;k<tweets.size();k++)
            			//{
            			for(k=1;k<m.length;k++)
            			{
            				if(isNumeric(m[k]))
            				{
            					answer.add(m[k]);	
            				}
            			}
            				
            		}
        		}
        		
        	}
    		}
    	
    	return answer;
        //throw new RuntimeException("not implemented");
    }

}
