/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   TODO
    //Vertex<L>具体包括一个顶点，该顶点为soucre的targets和weight的map,以及该顶点为target的source和weight的map
    // Representation invariant:
    //   TODOweight需要为>0
    // Safety from rep exposure:
    //   TODOtrue
 
    
    // TODO checkRep1
    private void checkRep(L example)
	{
		if(example.toString()==null)
		{
			System.out.println("参数为空");
			System.exit(0);
		}
	}
	private void checkRep1(Map<L, Integer> example)
	{
		if(example==null)
		{
			System.out.println("参数为空");
			System.exit(0);
		}
	}
    
    @Override public boolean add(L vertex) {
    	checkRep(vertex);
    	Vertex<L> s = new Vertex<L>(vertex, null, null);
    	vertices.add(s);
    	return true;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public int set(L source, L target, int weight) {
    	checkRep(source);
    	checkRep(target);
    	int flag = 0,flag1 =0;
    	Vertex<L> s = null,s1 = null;
    	for(Vertex<L> p:vertices)
    	{
    		if(p.getVertices().equals(source))
    		{
    			s = p;
    			if(p.getTargets()==null)
    			{
    				if(!s.equals(null))
    	    		{
    					flag =1;
    					Map<L,Integer> target2 = new HashMap<L,Integer>();
    					target2.put(target, weight);
    	    			s.setTargets(target2);;
    	    		}
    			}
    			else
    			{
    				for(Entry<L, Integer> v:p.getTargets().entrySet())
        			{
        				if(v.getKey().equals(target))
        				{
        					flag =1;
        					v.setValue(weight);
        				}
        			}
    			}
    		}
    		if(p.getVertices().equals(target))
    		{
    			s1 = p;
    			if(p.getSources()==null)
    			{
    				if(!(s1==null))
    	    		{
    					flag1 = 1;
    					Map<L,Integer> source2 = new HashMap<L,Integer>();
    					source2.put(source, weight);
    	    			s1.setSources(source2);
    	    		}
    			}
    			else
    			{
    				for(Entry<L, Integer> x:p.getSources().entrySet())
        			{
        				if(x.getKey().equals(target))
        				{
        					flag1 =1;
        					x.setValue(weight);
        				}
        			}
    			}
    		}
    	}
    	if(flag!=1)
    	{
    		if(!s.equals(null))
    		{
    			s.getTargets().put(target, weight);
    		}
    	}
    	if(flag1!=1)
    	{
    		if(!s1.equals(null))
    		{
    			s1.getTargets().put(source, weight);
    		}
    	}
    	
    	return 0;
       // throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
    	checkRep(vertex);
    	L answer = null;
    	for(Vertex<L> p:vertices)
    	{
    		if(p.getVertices().equals(vertex))
    		{
    			answer = p.getVertices();
    		}
    		Map<L,Integer> map =p.getSources(); 
    		if(map!=null)
    		{
    			for (L in : map.keySet())
        		{
        			if(in.equals(vertex))
        			{
        				map.remove(vertex);
        			}
        		}
    		}
    	
    		 map =p.getTargets() ;
    			if(map!=null)
    			{
    				for (L in : map.keySet())
    	    		{
    	    			if(in.equals(vertex))
    	    			{
    	    				map.remove(vertex);
    	    			}
    	    		}
    			}
     	}
    	if(answer!=null)
    	vertices.remove(answer);
    	return true;
        //throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
    	Set<L> x = new HashSet<L>();
    	for(Vertex<L> p:vertices)
    	{
    		x.add(p.getVertices());
    	}
    	return x;
      //  throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	checkRep(target);
    	for(Vertex<L> p:vertices)
    	{
    		if(p.getVertices().equals(target))
    		{
    			return p.getSources();
    		}
    	}
        throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> targets(L source) {
    	checkRep(source);
    	for(Vertex<L> p:vertices)
    	{
    		if(p.getVertices().equals(source))
    		{
    			return p.getTargets();
    		}
    	}
        throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    public String toString() {
    	String result = null;
    	for(int i = 0; i < vertices.size(); i++) {
	   		if(result != null) {
	   			result += vertices.get(i).toString();
	   		}
	   		else {
	   			result = vertices.get(i).toString();
	   		}
	   		result += "\n";
	   	}
    	return result;
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
    
    // TODO fields private
    // Abstraction function:
    //   TODOvertex包括一个L的顶点以及两个map,map分别是该点作为终结点的边的起始点和权重集合以及该点作为起始点的边的终结点和权重的集合
    // Representation invariant:
    //   TODOtrue
    // Safety from rep exposure:
    //   TODO使用private
 
    
    // TODO checkRep1
	private void checkRep(L example)
	{
		if(example.toString()==null)
		{
			System.out.println("参数为空");
			System.exit(0);
		}
	}
	private void checkRep1(Map<L, Integer> example)
	{
		if(example==null)
		{
			System.out.println("参数为空");
			System.exit(0);
		}
	}

    // TODO toString()
    public String toString() {
    	String result = vertice.toString()+" has sources";
    	for(L answer:sources.keySet())
    	{
    		result = result+" "+answer.toString();
    		}
    	result = result +"\n"+" has targets:";
    	for(L answer:targets.keySet())
    	{
    		result = result +" "+answer.toString();
    	}
    	return result;
    }

	private L vertice;
	private Map<L,Integer> sources = new HashMap<L,Integer>();
	private Map<L,Integer> targets = new HashMap<L,Integer>();
	public Vertex(L vertice,Map<L,Integer> sources, Map<L,Integer> targets)
	{
		this.sources = sources;
		this.vertice = vertice;
		this.targets = targets;
	}
	public L getVertices() {
		return vertice;
	}
	public void setVertices(L vertice) {
		checkRep(vertice);
		this.vertice = vertice;
	}
	public Map<L, Integer> getSources() {
		return sources;
	}
	public void setSources(Map<L, Integer> sources) {
		checkRep1(sources);
		this.sources = sources;
	}
	public Map<L, Integer> getTargets() {
		return targets;
	}
	public void setTargets(Map<L, Integer> targets) {
		checkRep1(targets);
		this.targets = targets;
	}
	
    
}
