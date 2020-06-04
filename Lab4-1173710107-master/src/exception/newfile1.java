package exception;

import java.util.ArrayList;
import java.util.Scanner;

import circularOrbit.ConcreteCircularOrbit;

public class newfile1  extends RuntimeException implements newfile {
	@Override
	public void newfile2(ConcreteCircularOrbit m,int num)
	{
		try
		{
			if(num==1)
			{
				System.out.println("请添加新的文本，具体路径为txt/TrackGame1.txt");
				
			       System.out.println("新文本输入完成后输入yes");
			       Scanner in6 = new Scanner(System.in);
			     //  System.out.println("新文本输入完成后输入yes");
			       String s1 = in6.nextLine();
			       if(s1.equals("yes"))
			       {
			    	   ArrayList<String> info = m.readFile("txt/TrackGame1.txt");
			    	   m.initarraylist();
			    	   m.setinfoall(info);
			    	   m.Analysis();
			       }
			       else
			       {
			    	   System.out.println("输入不是yes！！！");
			    	   System.exit(0);
			       }
			     //  in6.close();
			}
			else if(num==2)
			{
				System.out.println("请添加新的文本，具体路径为txt/AtomicStructure1.txt");
				
			       System.out.println("新文本输入完成后输入yes");
			       Scanner in6 = new Scanner(System.in);
			     //  System.out.println("新文本输入完成后输入yes");
			       String s1 = in6.nextLine();
			       if(s1.equals("yes"))
			       {
			    	   ArrayList<String> info = m.readFile("txt/AtomicStructure1.txt");
			    	   m.initarraylist();
			    	   m.setinfoall(info);
			    	   m.Analysis();
			       }
			       else
			       {
			    	   System.out.println("输入不是yes！！！");
			    	   System.exit(0);
			       }
			}
			else if(num==3)
			{
				System.out.println("请添加新的文本，具体路径为txt/SocialNetworkCircle1.txt");
				
			       System.out.println("新文本输入完成后输入yes");
			       Scanner in6 = new Scanner(System.in);
			     //  System.out.println("新文本输入完成后输入yes");
			       String s1 = in6.nextLine();
			       if(s1.equals("yes"))
			       {
			    	   ArrayList<String> info = m.readFile("txt/SocialNetworkCircle1.txt");
			    	   m.initarraylist();
			    	   m.setinfoall(info);
			    	   m.Analysis();
			       }
			       else
			       {
			    	   System.out.println("输入不是yes！！！");
			    	   System.exit(0);
			       }
			}
			else
			{
				throw new notfoundnewfile();
			}
		}
		catch(notfoundnewfile e)
		{
			e.exit();
		}
		
	}
		
	
}
