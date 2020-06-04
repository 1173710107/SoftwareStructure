package circularOrbit;

/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();

    @Override public boolean add(L vertex) {
    	for (Vertex<L> x:vertices)
    	{
    		if(x.getName().equals(vertex))
    			return false;
    	}
    	vertices.add(new Vertex<L>(vertex));
        return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	int count=0;
    	if(weight!=0)
    	{
    		boolean flag = false;
    		for(Vertex<L> temp:vertices)
    		{
    			if(temp.getName().equals(source))
    			{
    				flag = true;
    				count = temp.addTarget(target, weight);
    				break;
    			}
    		}
    		if(flag == false)
    		{
    			Vertex<L> temp1 = new Vertex<L>(source);
    			temp1.addTarget(target, weight);
    			vertices.add(temp1);
    		}
    	}
    	else
    	{
    		for(Vertex<L> temp:vertices)
    		{
    			if(temp.getName().equals(source))
    			{
    				if(temp.containTarget(target))
    				{
    					count = temp.removeTarget(target);
    				}
    				break;
    			}
    		}
    	}
    	boolean flag2 = false;
    	for(Vertex<L> temp:vertices)
		{
			if(temp.getName().equals(target))
			{
				flag2 = true;
				break;
			}
		}
    	if(flag2 == false)
    	{
    		vertices.add(new Vertex<L>(target));
    	}
    	return count;
    }
    @Override public boolean remove(L vertex) {
  		boolean flag = false;
  		Vertex<L> dispose=null;
		for(Vertex<L> temp:vertices)
		{
			if(temp.getName().equals(vertex))
			{
				flag = true;
				dispose=temp;
				break;
			}
		}
		vertices.remove(dispose);
		return flag;
    }
    public ArrayList<Pair<L,L>> getPair()
    {
    	ArrayList<Pair<L,L>> ret = new ArrayList<Pair<L,L>>();
    	for(Vertex<L> temp:vertices)
    	{
    		for(L templ:temp.getTargetList())
    		{
    			ret.add(new Pair<L,L>(temp.name,templ));
    		}
    	}
    	return ret;
    }
    @Override public Set<L> vertices() {
        Set<L> ret = new HashSet<>();
        for(Vertex<L> temp:vertices)
        {
        	ret.add(temp.getName());
        }
        return ret;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        Map<L,Integer> ret = new HashMap<>();
        for(Vertex<L> temp:vertices)
        {
        	if(temp.containTarget(target))
        	{
        		ret.put(temp.getName(),temp.getTargets().get(target));
        	}
        }
        return ret;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        Map<L,Integer> ret = new HashMap<>();
        for(Vertex<L> temp:vertices)
        {
        	if(temp.getName().equals(source))
        	{
        		return temp.getTargets();
        	}
        }
        return ret;
    }
    
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		for(Vertex<L> temp:vertices)
		{
			ret.append(temp.getName()+":");
	        ret.append(temp.getTargets());
	        ret.append("\n");
		}
		return ret.toString();
	}
    
    // TODO toString()
	public static void main(String[] args)
	{
		ConcreteVerticesGraph<String> test = new ConcreteVerticesGraph<String>();
		test.add("first");
		test.add("second");
		test.add("third");
		test.add("fourth");
		System.out.println(">>After add four things.");
		System.out.println(test);
		test.set("first", "second", 1);
		test.set("first", "third", 3);
		test.set("first", "fourth", 4);
		test.set("second", "third", 2);
		System.out.println(">>After add four edges.");
		System.out.println(test);
		test.remove("first");
		System.out.println(">>remove the first.");
		System.out.println(test);
		test.set("fifth", "sixth", 5);
		System.out.println(">>Add two vertexs while add edge.");
		System.out.println(test);
		test.set("second", "third", 0);
		System.out.println(">>Remove a edge using set.");
		System.out.println(test);
		System.out.println(test.getPair());
	}
}

/**
 * TODO specification
 * Mutable.
 * This class is internal to the rep of ConcreteVerticesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Vertex<L> {
    
     L name;
     HashMap<L,Integer> targets = new HashMap<L,Integer>();
    
    
    public L getName()
    {
    	return name;
    }
    public Set<L> getTargetList()
    {
    	return this.targets.keySet();
    }
    public Map<L,Integer> getTargets()
    {
    	return targets;
    }
    public Vertex(L name)
    {
    	this.name = name;
    }
    public int addTarget(L b,int value)
    {
    	targets.put(b, value);
    	return value;
    }
    public boolean containTarget(L target)
    {
    	return targets.containsKey(target);
    }
   
    public int removeTarget(L target)
    {
    	return targets.remove(target);
    }
	@Override
	public String toString() {
		return "Vertex :name=" + name + ", targets=" + targets;
	}
    
    
}
