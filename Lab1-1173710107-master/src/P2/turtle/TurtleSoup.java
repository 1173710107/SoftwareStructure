/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P2.turtle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TurtleSoup {

    /**
     * Draw a square.
     * 
     * @param turtle the turtle context
     * @param sideLength length of each side
     */
    public static void drawSquare(Turtle turtle, int sideLength) {
    	turtle.draw();
    	turtle.forward(sideLength);
    	turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
        turtle.forward(sideLength);
        turtle.turn(90.00);
       // throw new RuntimeException("implement me!");
    }

    /**
     * Determine inside angles of a regular polygon.
     * 
     * There is a simple formula for calculating the inside angles of a polygon;
     * you should derive it and use it here.
     * 
     * @param sides number of sides, where sides must be > 2
     * @return angle in degrees, where 0 <= angle < 360
     */
    public static double calculateRegularPolygonAngle(int sides) {
    	return 180.00-360.00/sides;
        //throw new RuntimeException("implement me!");
        
    }

    /**
     * Determine number of sides given the size of interior angles of a regular polygon.
     * 
     * There is a simple formula for this; you should derive it and use it here.
     * Make sure you *properly round* the answer before you return it (see java.lang.Math).
     * HINT: it is easier if you think about the exterior angles.
     * 
     * @param angle size of interior angles in degrees, where 0 < angle < 180
     * @return the integer number of sides
     */
    public static int calculatePolygonSidesFromAngle(double angle) {
    	double x = 180.00-angle;
    	double n = 360.00/x;
    	int m = (int)(360/x);
    	if(m-n<-0.5)
    	{
    		m = m+1;
    	}
 
    	return m;
     //   throw new RuntimeException("implement me!");
    }

    /**
     * Given the number of sides, draw a regular polygon.
     * 
     * (0,0) is the lower-left corner of the polygon; use only right-hand turns to draw.
     * 
     * @param turtle the turtle context
     * @param sides number of sides of the polygon to draw
     * @param sideLength length of each side
     */
    public static void drawRegularPolygon(Turtle turtle, int sides, int sideLength) {
    	turtle.draw();
    	double x  =180.00- calculateRegularPolygonAngle(sides);
    	for(int i=0;i<sideLength;i++)
    	{
    		turtle.forward(sideLength);
        	turtle.turn(x);
    	}
       
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given the current direction, current location, and a target location, calculate the Bearing
     * towards the target point.
     * 
     * The return value is the angle input to turn() that would point the turtle in the direction of
     * the target point (targetX,targetY), given that the turtle is already at the point
     * (currentX,currentY) and is facing at angle currentBearing. The angle must be expressed in
     * degrees, where 0 <= angle < 360. 
     *
     * HINT: look at http://en.wikipedia.org/wiki/Atan2 and Java's math libraries
     * 
     * @param currentBearing current direction as clockwise from north
     * @param currentX current location x-coordinate
     * @param currentY current location y-coordinate
     * @param targetX target point x-coordinate
     * @param targetY target point y-coordinate
     * @return adjustment to Bearing (right turn amount) to get to target point,
     *         must be 0 <= angle < 360
     */
    public static double calculateBearingToPoint(double currentBearing, int currentX, int currentY,
                                                 int targetX, int targetY) {
    	double m;
    	if(targetX-currentX==0)
    	{
    		m = 90.00;
    		if(targetY-currentY==0)
    		{
    			return 10000000.00;
    		}
    	}
    	else
    	{
    		m = Math.toDegrees(Math.atan(((double)targetY-currentY)/(targetX-currentX)));
    	}
    	double x1 = 0.00;
    	
    		if(targetX-currentX>0)//m就是相对的角度
    		{
    			 x1 =90-m-currentBearing;
    		}
    		else if(targetX-currentX==0)
    		{
    			if(targetY-currentY>0)
    			{
    				x1 = 360.00-currentBearing;
    			}
    			else if(targetY-currentY==0)
    			{
    				x1 = 1000;
    			}
    			else
    			{
    				x1  =180.00-currentBearing;
    			}
    		}
    		else//m-180是相对角度
    		{
    			x1 = 270.00-currentBearing -m;
    		}
    	if(x1<0.00)
    	{
    		x1 = x1+360.00;
    	}
    	else if(x1>=360.00)
    	{
    		x1 = x1-360.00;
    	}
    	//System.out.println(x1+" "+currentBearing+currentX+ currentY+targetX+ targetY);
    	return x1;
        //throw new RuntimeException("implement me!");
    }

    /**
     * Given a sequence of points, calculate the Bearing adjustments needed to get from each point
     * to the next.
     * 
     * Assumes that the turtle starts at the first point given, facing up (i.e. 0 degrees).
     * For each subsequent point, assumes that the turtle is still facing in the direction it was
     * facing when it moved to the previous point.
     * You should use calculateBearingToPoint() to implement this function.
     * 
     * @param xCoords list of x-coordinates (must be same length as yCoords)
     * @param yCoords list of y-coordinates (must be same length as xCoords)
     * @return list of Bearing adjustments between points, of size 0 if (# of points) == 0,
     *         otherwise of size (# of points) - 1
     */
    public static List<Double> calculateBearings(List<Integer> xCoords, List<Integer> yCoords) {
    	//calculateBearingToPoint(double currentBearing, int currentX, int currentY,int targetX, int targetY) 
    	int i;
    	double x,bear = 0.00;
    	List<Double> list = new ArrayList<>();
    	for(i=0;i<xCoords.size()-1;i++)
    	{
    		x = calculateBearingToPoint(bear,xCoords.get(i),yCoords.get(i),xCoords.get(i+1),yCoords.get(i+1));
    		list.add(x);
    		bear = bear+x;
    		if(bear>360.00)
    		{
    			bear = bear - 360.00;
    		}
    		if(bear<0.00)
    		{
    			bear = bear +360.00;
    		}
    	}
    	return list;
        //throw new RuntimeException("implement me!");
    }
    
    /**
     * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
     * in a set of input points. The gift-wrapping algorithm is one simple approach to this problem, and 
     * there are other algorithms too.
     * 
     * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
     * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
     */
    public static Set<Point> convexHull(Set<Point> points) {
    	Set<Point> answer = new HashSet<Point>();
    	if(points.isEmpty())
    	{
    		return answer;
    	}
    	else if(points.size()<=3)
    	{
    		for(Point p : points)
    		{
    			answer.add(p);
    		}
    		return answer;
    	}
    	double minx=10000.00,y = 10000.00;
    	for(Point s : points)
    	{
    		if(minx>s.x())
			{
				minx = s.x();
				y = s.y();
			}
			else if(minx==s.x())
			{
				if(y>s.y())
				{
					minx = s.x();
					y = s.y();
				}
			}
    	}
    	Point p1 = null;
    	for(Point p:points)
    	{
    		if(p.x()==minx&&p.y()==y)
    		{
    			p1  = p;
    		}
    	}
		
		Point p0 = p1;
		answer.add(p0);//最左边的点
		double bear1=0,minbear = 10000,bear3 = 0.00,dis1 =0,dis2 =0,count =0,num =0;//bear3是每次计算出的角度
		Point endp = p0;
	do
		{
			minbear = 10000.00;
				for(Point p : points)
				{
				bear3 = calculateBearingToPoint(bear1,(int)p1.x(),(int)p1.y(),(int)p.x(),(int)p.y());
				//System.out.println(count+"$"+minbear+" @"+bear3 +" "+bear1+" "+ p1.x() +" "+p1.y()+" "+p.x()+" "+p.y());
				count++;
				if(minbear>bear3)
				{
					endp = p;
					minbear = bear3;
				//	System.out.println(bear3+" "+endp.x()+" "+endp.y() );
				}
				else if(minbear==bear3)
				{
					dis1 = Math.sqrt((p.x()-p1.x())*(p.x()-p1.x()) + (p.y()-p1.y())*(p.y()-p1.y()));
					dis2 =  Math.sqrt((endp.x()-p1.x())*(endp.x()-p1.x()) + (endp.y()-p1.y())*(endp.y()-p1.y()));
					if(dis1>dis2)
					{
						endp = p;
						minbear = bear3;
				//		System.out.println(bear3+" "+endp.x()+" "+endp.y() );
					}
				}
				}
				if(p0.x()!=endp.x()||p0.y()!=endp.y())
				{
					answer.add(endp);
				//	System.out.println("!!!!!"+endp.x()+"&*"+endp.y());
					p1 = endp;	
					bear1 = bear1+minbear;
					num++;
				}
				else
				{
					p1 = endp;	
				}
			
		}while(p0.x()!=p1.x()||p0.y()!=p1.y());
	//System.out.println(p0.x()+"!"+p0.y());
/*	for(Point s: answer)
	{
		System.out.println(s.x()+"$"+s.y());
	}*/
/*	Set<Point> answer1 = new HashSet<Point>();
	Point p11 = new Point(1, 1);
	Point p1010 = new Point(10, 10);
	Point p110 = new Point(1, 10);
	answer1.add(p11);
	answer1.add(p1010);
	answer1.add(p110);*/
		return answer;
    }
    
    /**
     * Draw your personal, custom art.
     * 
     * Many interesting images can be drawn using the simple implementation of a turtle.  For this
     * function, draw something interesting; the complexity can be as little or as much as you want.
     * 
     * @param turtle the turtle context
     */
    public static void drawPersonalArt(Turtle turtle) {
    	turtle.draw();
    	PenColor x = null;
    	turtle.color(x.RED);
    	for(int i=1;i<6;i++)
    	{
    		turtle.forward(100);
    		turtle.turn(144.00);
    	}
    	
        throw new RuntimeException("implement me!");
    }

    /**
     * Main method.
     * 
     * This is the method that runs when you run "java TurtleSoup".
     * 
     * @param args unused
     */
    public static void main(String args[]) {
        DrawableTurtle turtle = new DrawableTurtle();
     //   drawRegularPolygon(turtle,5,30);
      // drawSquare(turtle, 40);
       drawPersonalArt(turtle);
        // draw the window
      //  turtle.draw();
      
	
    }

}
