package circularOrbit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exception.InvalidTag;
import exception.NumOrder;
import exception.SyntaxSpec;
import exception.sameele;
import exception.sametag;
import exception.wrongformat;
import exception.wrongrely;
import physicalObject.Athlete;
import org.apache.log4j.Logger;

import applications.Main;

import org.apache.log4j.BasicConfigurator; 
import org.apache.log4j.Level;
public class TrackGame extends ConcreteCircularOrbit<Athlete,Athlete>{
	Logger logger = Logger.getLogger(TrackGame .class);
   
	int length = 0; 
	int Tracknum = 0;
	ArrayList<Athlete> athletes = new ArrayList<Athlete>();
	ArrayList<String> infoall = new ArrayList<String>();
	public void initarraylist()
	{
		this.athletes.removeAll(athletes);
	} 
	 /**
     * ����һ���µĹ��
     * AF:�½�һ���������Ӧ��һ���ܵ�
     * RI��true
     * immutable
     */
	public void setinfoall(ArrayList<String> info)
	{
		this.infoall = info;
	}
	public void newTrack()
	{
		Tracknum++;
		this.addTrack("track"+Tracknum , Tracknum );
	}
	 /**
     * ɾ���������Ĺ��
     * AF:ɾ��������һ���������Ӧ���������ϵ��ܵ�
     * RI��true
     * immutable
     */
	public void clearTrack()
	{
		this.removeTrack(Tracknum);
		Tracknum--;
	}
	 /**
     * ͨ����ȡ���ı���Ϣ����track
     * @param string���͵��ı���Ϣ
     * AF:���ļ�����string���з���ת���ɵ��Ǿ�������������͵�����
     * RI��true
     * immutable
     */
	public void Analysis()
     {
		 logger.setLevel(Level.INFO);
	   int gamenum = 0;
	   int tracknum = 0;
	   boolean visited  = false;
	   try {
		   for(String info:infoall)
		   {
			   visited = false;
			   Pattern p1 = Pattern.compile("Game ::= (100|200|400)");
		         Matcher m1 = p1.matcher(info);
		         if(m1.find())
		         {
			         this.length = Integer.valueOf(m1.group(1));
			         System.out.println("length = "+this.length);
			         gamenum++;
			         visited = true;
		         }
		         Pattern p2 = Pattern.compile("NumOfTracks ::= (4|5|6|7|8|9|10)");
		         Matcher m2 = p2.matcher(info);
		         if(m2.find())
		         {
		        	 this.Tracknum = Integer.valueOf(m2.group(1));
			         System.out.println("NumOfTracks = "+ this.Tracknum);
			         for(int i=0;i<=this.Tracknum;i++)
			         {
			        	 this.addTrack("track"+i, i);
			         }
			         tracknum++;
			         visited = true;
		         }
		         if(gamenum==2||tracknum==2)
		         {
		        	 int x = 0;
		        	 logger.error("game����track�ı�ǩ�����˶�������������ļ�");
		        	 throw new sametag();
		         }
		         Pattern p3 = Pattern.compile("Athlete ::= <([a-zA-z]+),(\\d+),([A-Z]{3}),(\\d+),(\\d{1,2}.\\d{2})>");
		         Matcher m3 = p3.matcher(info);
		         if(m3.find())
		         {
		        	 if(m3.groupCount()!=5)
		        	 {
		        		 int x =0;
		        		// System.out.println("!!!");
		        		 logger.error("�˶�Ա��ǩ������������5�������������ļ�");
		        		 throw new NumOrder(x);
		        	 }
		        	 for(Athlete s:athletes)
		        	 {
		        		 if(m3.group(1).equals(s.getname()))
		        		 {
		        			 logger.error("�˶�Ա������������ͬ��,���������ļ�");
		        			 throw new sameele();
		        		 }
		        	 }
		         athletes.add(new Athlete(m3.group(1),Integer.valueOf(m3.group(2)),m3.group(3),Integer.valueOf(m3.group(4)),Double.valueOf(m3.group(5))));
		         visited = true;
		         }
		         if(!visited)//δƥ�䵽������ʽ
		         {
		        	 String [] line = info.split(",");
		        	// System.out.println(line.length);
		        	 if(line.length==5)//�˶�Ա
		        	 {
		        		// System.out.println("1");
		        		 String [] line1 = line[0].split("<");
		        		 if(line1.length==2)
		        		 {
		        			 //System.out.println("2");
		        			 if(line1[0].equals("Athlete ::= "))
		        			 {
		        				// System.out.println("3");
		        				 for(int i = 0; i < line[1].length(); i++)//�ڶ�λ�Ǵ�����
		        				 {
		        					// System.out.println(line[1].charAt(i));
			        				if (!Character.isDigit(line[1].charAt(i)))
			        				{
			        					int x =1;
			        				//	System.out.println(line[1]);
			        					logger.error("�˶�Ա�ڶ����������Ǵ����֣����������ļ�");
			        					throw new NumOrder(x);
			        				}
			        			
		        				 }
		        				 if(line[2].length()==3)//�������Ƿ���ȷ
		        				 {
		        					 for(int i = 0; i < line[2].length(); i++) 
		        					 { 
		        					 char c = line[2].charAt(i); 
		        					 if (Character.isUpperCase(c)) 
		        					 {
		        						 
		        					 }
		        					 else
		        					 {
		        						 logger.error("�˶�Ա��ǩ���Ҳ���������д����ĸ�����������ļ�");
		        						 throw new SyntaxSpec();
		        					 }
		        					 }
		        				 }
		        				 else
		        				 {
		        					 logger.error("�˶�Ա��ǩ���Ҳ��������ַ������������ļ�");
		        					 throw new SyntaxSpec();
		        				 }
		        				 for(int i = 0; i < line[3].length(); i++)//����λ�Ǵ�����
		        				 {
		        					// System.out.println(line[1].charAt(i));
			        				if (!Character.isDigit(line[3].charAt(i)))
			        				{
			        					int x =1;
			        					System.out.println(line[3]);
			        					logger.error("�˶�Ա��ǩ���ĸ��������Ǵ����֣����������ļ�");
			        					throw new NumOrder(x);
			        				}
		        				 }
		        				 String [] sp = line[4].split(">");//���ɼ�
		        				 if(sp.length==1)
		        				 {
		        					 String [] sp1 = sp[0].split("\\.");
		        					 if(sp1.length==2&&sp1[1].length()==2)
		        					 {
		        						 //����Ҳ���������ˡ���
		        					 }
		        					 else if(sp1.length==2)
		        					 {
		        						for (int i = 0; i < sp1[1].length(); i++)
		        						{
		        							System.out.println(sp1[1].charAt(i));
		        							if (!Character.isDigit(sp1[1].charAt(i)))
		        							{
		        								logger.error("�˶�Ա�ɼ���ǩ���Ǵ����֣����������ļ�");
		        								throw new SyntaxSpec();
		        							}
		        						}
		        					 }
		        						if(sp1.length==1)//������λ�Զ���0
		        						{
		        							String newinfo1 = line[0]+","+line[1]+","+line[2]+","+line[3]+","+sp1[0]+"."+sp1[1]+"0>";
		        							 Pattern p7 = Pattern.compile("Athlete ::= <([a-zA-z]+),(\\d+),([A-Z]{3}),(\\d+),(\\d{1,2}.\\d{2})>");
		        					         Matcher m7 = p7.matcher(newinfo1);
		        					         if(m7.find())
		        					         {
		        					        	 if(m7.groupCount()!=5)
		        					        	 {
		        					        		 int x =0;
		        					        		// System.out.println("!!!");
		        					        		 logger.error("�˶�Ա��ǩ�������������������ļ�");
		        					        		 throw new NumOrder(x);
		        					        	 }
		        					        	 for(Athlete s:athletes)
		        					        	 {
		        					        		 if(m7.group(1).equals(s.getname()))
		        					        		 {
		        					        			 throw new sametag();
		        					        		 }
		        					        	 }
		        					         athletes.add(new Athlete(m7.group(1),Integer.valueOf(m7.group(2)),m7.group(3),Integer.valueOf(m7.group(4)),Double.valueOf(m7.group(5))));
		        					         visited = true;
		        					         logger.error("�˶�Ա��ǩ�ɼ�С����λС�����Զ�����");
		        					         }
		        						}
		        						else
		        						{
		        							String newinfo1 = line[0]+","+line[1]+","+line[2]+","+line[3]+","+sp1[0]+"."+sp1[1]+"0>";
		        							 Pattern p7 = Pattern.compile("Athlete ::= <([a-zA-z]+),(\\d+),([A-Z]{3}),(\\d+),(\\d{1,2}.\\d{2})>");
		        					         Matcher m7 = p7.matcher(newinfo1);
		        					         if(m7.find())
		        					         {
		        					        	 if(m7.groupCount()!=5)
		        					        	 {
		        					        		 int x =0;
		        					        		// System.out.println("!!!");
		        					        		 logger.error("�˶�Ա��ǩ�����������������ļ�");
		        					        		 throw new NumOrder(x);
		        					        	 }
		        					        	 for(Athlete s:athletes)
		        					        	 {
		        					        		 if(m7.group(1).equals(s.getname()))
		        					        		 {
		        					        			 logger.error("�˶�Ա��ǩ����ͬ���֣����������ļ�");
		        					        			 throw new sameele();
		        					        		 }
		        					        	 }
		        					         athletes.add(new Athlete(m7.group(1),Integer.valueOf(m7.group(2)),m7.group(3),Integer.valueOf(m7.group(4)),Double.valueOf(m7.group(5))));
		        					         visited = true;
		        					         }
		        						}
		        					 }
		        					 else
		        					 {
		        						 logger.error("�˶�Ա��ǩ�ɼ������Ϲ������������ļ�");
		        						 throw new SyntaxSpec();
		        					 }
		        				 }
		        				 else
		        				 {
		        					 logger.error("�˶�Ա��ǩ�ַ��������Ϲ������������ļ�");
		        					 throw new wrongformat();
		        				 }
		        			 }
		        			 else
		        			 {
		        				// System.out.println(line1[0]);
		        				 logger.error("�˶�Ա��ǩ�ַ��������Ϲ������������ļ�");
		        				 throw new wrongformat();
		        			 }
		        		 }
		        		
		        	 else
		        	 {
		        		 String [] line1 = info.split("::");
		        			 if(line1[0].equals("Game "))//��game���͵�
			        		 {
		        				 if(line1.length==2)
		        				 {
		        					 String [] line2 = line1[1].split("= ");
				        			 if(line2.length==1)
				        			 {
				        				 for (int i = 0; i < line2[0].length(); i++)
					        			 {
					        				// System.out.println(line2[0].charAt(i));
					        				 if (!Character.isDigit(line2[0].charAt(i)))//���ֵط����������ַ�
					        				 {
					        					 logger.error("�˶�Ա��ǩgame�����������������ַ������������ļ�");
					        					 throw new SyntaxSpec();
					        				 }
					        			 } String newinfo = null;
				        					    int p = Integer.valueOf(line2[0]).intValue();//�Զ��ı��ܵ�length
				        					    if(p<=100)
				        					    {
				        					    	 newinfo = "Game ::= "+"100";
				        					    }
				        					    else if(p<=200)
				        					    {
				        					    	newinfo = "Game ::= "+"200";
				        					    }
				        					    else if(p<=400)
				        					    {
				        					    	newinfo = "Game ::= "+"400";
				        					    }
				        					    else
				        					    {
				        					    	newinfo = "Game ::= "+"800";
				        					    }
				        					    Pattern p5 = Pattern.compile("Game ::= (100|200|400)");
				        				         Matcher m5 = p1.matcher(newinfo);
				        				         if(m5.find())
				        				         {
				        					         this.length = Integer.valueOf(m5.group(1));
				        					         System.out.println("length = "+this.length);
				        					         gamenum++;
				        					         visited = true;
				        				         }
				        			 }
				        			 else
				        			 {
				        				 logger.error("game��ǩ�ַ��������Ϲ������������ļ�");
				        				 throw new wrongformat();
				        			 }
		        				 }
		        				 else//��ʽ��ȫ����
				        		 {
		        					 logger.error("game��ǩ�ַ��������Ϲ������������ļ�");
				        			 throw new wrongformat();
				        		 }
				        		
			        			 
			        			
			        		 }
		        		 else if(line1[0].equals("NumOfTracks "))//��track
		        		 {
		        			// System.out.println(info);
		        			 if(line1.length==2)
		        			 {
		        				 String [] line3 = line1[1].split("= ");
		        				 if(line3.length==1)
		        				 {
		        					 for (int i = 0; i < line3[0].length(); i++)
		        					{
		        						 if (!Character.isDigit(line3[0].charAt(i)))
		        						 {
		        							 logger.error("track��ǩ���Ǵ����֣����������ļ�");
		        							 throw new SyntaxSpec();
		        						 }
		        							
		        					}
		        				 }
		        				 else
		        				 {
		        					 logger.error("track��ǩ��ʽ���ԣ����������ļ�");
		        					 throw new SyntaxSpec();
		        				 }
		        			 }
		        			 else
		        			 {
		        				 logger.error("track��ǩ��ʽ���ԣ����������ļ�");
		        				 throw new SyntaxSpec();
		        			 }
		        			
		        		 }
		        		 else//��ǩ����
		        		 {
		        			 //System.out.println(info);
		        			 logger.error("�ı��д��ڷǷ���ǩ�����������ļ�");
		        			 throw new InvalidTag();
		        			
		        		 }
		        	 }
		         
		         
		   }
		   }
	   }catch(NumOrder e)
	   {
			  e.newfile2(this,1);
	   }
	   catch(sametag e)
	   {
		  e.newfile2(this,1);
	   }
	   catch(sameele e)
	   {
		   e.newfile2(this,1);
	   }
	   catch(InvalidTag e)
	   {
		   e.newfile2(this,1);
	   }
	   catch(SyntaxSpec e)
	   {
		   e.newfile2(this,1);
	   }
	   catch(wrongformat e)
	   {
		   e.newfile2(this,1);
	   }
	  
       
     }
   /**
    * �������������
    * AF����Ӧ�ڶԹ���ϵ��˶�Ա���з����ܵ�
    * RI��true
    * immutable
    */
   public void shuffleArrange()
   {
	   
	   Collections.shuffle(athletes);
	   int i=1;
	   for(Athlete temp:athletes)
	   {
		   this.addObject(i, temp);
		   i++;
		   if(i>Tracknum)
			   i=1;
	   }
   }
   /**
    * ���ճɼ���������
    * AF:��Ӧ�ڶ��ܵ��ϵ��˶�Ա���з����ܵ�
    * RI��true
    * immutable
    */
   public void sortedArrange()
   {
	  
	   Collections.sort(athletes);
	   int i=1;
	   for(Athlete temp:athletes)
	   {
		   this.addObject(i, temp);
		   i++;
		   if(i>Tracknum)
			   i=1;
	   }
   }
   /**
    * ���������������������
    * �����׳��쳣
    */
   public void checkRep()
   {
	   try
	   {
		   if(athletes.size()>Tracknum)
		   {
			   throw new wrongrely();
		   }
	   }
	  catch(wrongrely e)
	  {
		  e.wrongrely1();;
	  }
   }
   @Override
     public String toString() {
 		return tracks.toString();
 	}
     public static void main(String[] args)
     {
    	 TrackGame x = new TrackGame();
         ArrayList<String> info = x.readFile("txt/TrackGame.txt");
         System.out.println(info);
         x.Analysis();
         x.sortedArrange();
         System.out.println(x);
     }
}
