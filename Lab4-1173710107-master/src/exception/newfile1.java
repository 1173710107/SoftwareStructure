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
				System.out.println("������µ��ı�������·��Ϊtxt/TrackGame1.txt");
				
			       System.out.println("���ı�������ɺ�����yes");
			       Scanner in6 = new Scanner(System.in);
			     //  System.out.println("���ı�������ɺ�����yes");
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
			    	   System.out.println("���벻��yes������");
			    	   System.exit(0);
			       }
			     //  in6.close();
			}
			else if(num==2)
			{
				System.out.println("������µ��ı�������·��Ϊtxt/AtomicStructure1.txt");
				
			       System.out.println("���ı�������ɺ�����yes");
			       Scanner in6 = new Scanner(System.in);
			     //  System.out.println("���ı�������ɺ�����yes");
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
			    	   System.out.println("���벻��yes������");
			    	   System.exit(0);
			       }
			}
			else if(num==3)
			{
				System.out.println("������µ��ı�������·��Ϊtxt/SocialNetworkCircle1.txt");
				
			       System.out.println("���ı�������ɺ�����yes");
			       Scanner in6 = new Scanner(System.in);
			     //  System.out.println("���ı�������ɺ�����yes");
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
			    	   System.out.println("���벻��yes������");
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
