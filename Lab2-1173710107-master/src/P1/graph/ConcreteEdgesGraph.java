/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

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
public class ConcreteEdgesGraph<L> implements Graph<L> {
    
    private final Set<L> vertices = new HashSet<>();
    private final List<Edge<L>> edges = new ArrayList<>();
    
    // Abstraction function:
    //   TODOһ��EDGE�������һ����ʼ����ս�㣬����Ȩ�أ�vertices��һ�������hashset�ļ��ϣ�edges��һ����edge<L>��arraylist
    
    // Representation invariant:
    //   TODOtrue
    // Safety from rep exposure:
    //   TODOinmutable
    
    
    // TODO checkRep
    private void checkRep(L example)
    {
    	if(example.toString()==null)
    	{
    		System.out.println("����Ϊ�գ�");
    		System.exit(0);
    	}
    	
    }
    private void checkRepnum(int x)
	{
		if(x<0)
		{
			System.out.println("Ȩ��С��0");
			System.exit(0);
		}
	}
    @Override public boolean add(L vertex) {
    	checkRep(vertex);
    		vertices.add(vertex);
        	return true;
    }
    
    @Override public int set(L source, L target, int weight) {
    	checkRep(source);
    	checkRep(target);
    	checkRepnum(weight);
    		int flag =0;
        	for(Edge<L> p:edges)
        	{
        		if(p.getStart().equals(source))
        		{
        			if(p.getEnd().equals(target))
        			{
        				flag =1;//�ҵ���Ӧ�ı�
        				p.setWeight(weight);
        			}
        		}
        	}
        	if(flag==1)
        	{
        		return 0;//�޸����
        	}
        	else
        	{
        		Edge<L> x = new Edge<L>(source, target, weight);
        		edges.add(x);
        		return 0;//δ�ҵ������
        	}
    	
       // throw new RuntimeException("not implemented");
    }
    
    @Override public boolean remove(L vertex) {
    	checkRep(vertex);
    		vertices.remove(vertex);
        	for(int i=0;i<edges.size();i++)
        	{
        		if(edges.get(i).getStart().equals(vertex)||edges.get(i).getEnd().equals(vertex))
        		{
        			edges.remove(i);
        		}
        	}
        	return true;
            //throw new RuntimeException("not implemented");
    }
    
    @Override public Set<L> vertices() {
    	
    	return vertices;
    //    throw new RuntimeException("not implemented");
    }
    
    @Override public Map<L, Integer> sources(L target) {
    	checkRep(target);
    		Map<L,Integer> map=new HashMap<L,Integer>();
        	for(Edge<L> p:edges)
        	{
        		if(p.getEnd().equals(target))
        		{
        			map.put(p.getStart(), p.getWeight());
        		}
        	}
        	return map;
    	}
   
    
    @Override public Map<L, Integer> targets(L source) {
    	checkRep(source);
    	Map<L,Integer> map=new HashMap<L,Integer>();
    	for(Edge<L> p:edges)
    	{
    		if(p.getStart().equals(source))
    		{
    			map.put(p.getEnd(), p.getWeight());
    		}
    	}
    	return map;
     //   throw new RuntimeException("not implemented");
    }
    
    // TODO toString()
    @Override
        public String toString() {
        	String result = null;
        	for(L v : vertices) {
        		if(result == null) {
        			result = v + " ";
        		}
        		else {
        			result += v + " ";
        		}
        	}
        	for(int i = 0; i < edges.size(); i++) {
        		result += "\n" + edges.get(i).toString();
        	}
        	return result;
        }

    }
    


/**
 * TODO specification
 * Immutable.
 * This class is internal to the rep of ConcreteEdgesGraph.
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge<L> {
    
    // TODOfields
    //private
    // Abstraction function:
    //   TODO��ʾ�����һ���ߣ������ߵ���ʼ�㣬�ս���Ȩ��
    // Representation invariant:
    //   TODOtrue
    // Safety from rep exposure:
    //   TODOʹ��private
    
    
    // TODO checkRep
	private void checkRep(L example)
	{
		if(example.toString()==null)
		{
			System.out.println("����Ϊ��");
			System.exit(0);
		}
	}
	
    
    
    // TODO toString()
    public String toString() {
    	String result = null;
    	result = start+ " to "+end+" with "+weight;
    	return result;
    }

	private L start;
	private L end;
	private int weight; 
	public Edge (L start,L end,int weight) {
		checkRep(start);
		checkRep(end);
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
	public L getStart() {
		return start;
	}
	public void setStart(L start) {
		checkRep(start);
		this.start = start;
	}
	public L getEnd() {
		return  end;
	}
	public void setEnd(L end) {
		checkRep(end);
		this.end = end;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
    
}
