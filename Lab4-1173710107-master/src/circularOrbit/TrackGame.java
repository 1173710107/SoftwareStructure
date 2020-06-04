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
     * 增加一个新的轨道
     * AF:新建一个轨道，对应于一个跑道
     * RI：true
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
     * 删除掉最外层的轨道
     * AF:删除最外层的一个轨道，对应于体育场上的跑道
     * RI：true
     * immutable
     */
	public void clearTrack()
	{
		this.removeTrack(Tracknum);
		Tracknum--;
	}
	 /**
     * 通过读取的文本信息构建track
     * @param string类型的文本信息
     * AF:将文件内容string进行分析转化成的是具体各个数据类型的数据
     * RI：true
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
		        	 logger.error("game或者track的标签出现了多个，重新输入文件");
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
		        		 logger.error("运动员标签所给参数不是5个，重新输入文件");
		        		 throw new NumOrder(x);
		        	 }
		        	 for(Athlete s:athletes)
		        	 {
		        		 if(m3.group(1).equals(s.getname()))
		        		 {
		        			 logger.error("运动员有两个名字相同的,重新输入文件");
		        			 throw new sameele();
		        		 }
		        	 }
		         athletes.add(new Athlete(m3.group(1),Integer.valueOf(m3.group(2)),m3.group(3),Integer.valueOf(m3.group(4)),Double.valueOf(m3.group(5))));
		         visited = true;
		         }
		         if(!visited)//未匹配到正则表达式
		         {
		        	 String [] line = info.split(",");
		        	// System.out.println(line.length);
		        	 if(line.length==5)//运动员
		        	 {
		        		// System.out.println("1");
		        		 String [] line1 = line[0].split("<");
		        		 if(line1.length==2)
		        		 {
		        			 //System.out.println("2");
		        			 if(line1[0].equals("Athlete ::= "))
		        			 {
		        				// System.out.println("3");
		        				 for(int i = 0; i < line[1].length(); i++)//第二位是纯数字
		        				 {
		        					// System.out.println(line[1].charAt(i));
			        				if (!Character.isDigit(line[1].charAt(i)))
			        				{
			        					int x =1;
			        				//	System.out.println(line[1]);
			        					logger.error("运动员第二个参数不是纯数字，重新输入文件");
			        					throw new NumOrder(x);
			        				}
			        			
		        				 }
		        				 if(line[2].length()==3)//检查国家是否正确
		        				 {
		        					 for(int i = 0; i < line[2].length(); i++) 
		        					 { 
		        					 char c = line[2].charAt(i); 
		        					 if (Character.isUpperCase(c)) 
		        					 {
		        						 
		        					 }
		        					 else
		        					 {
		        						 logger.error("运动员标签国家不是三个大写的字母，重新输入文件");
		        						 throw new SyntaxSpec();
		        					 }
		        					 }
		        				 }
		        				 else
		        				 {
		        					 logger.error("运动员标签国家不是三个字符，重新输入文件");
		        					 throw new SyntaxSpec();
		        				 }
		        				 for(int i = 0; i < line[3].length(); i++)//第四位是纯数字
		        				 {
		        					// System.out.println(line[1].charAt(i));
			        				if (!Character.isDigit(line[3].charAt(i)))
			        				{
			        					int x =1;
			        					System.out.println(line[3]);
			        					logger.error("运动员标签第四个参数不是纯数字，重新输入文件");
			        					throw new NumOrder(x);
			        				}
		        				 }
		        				 String [] sp = line[4].split(">");//检查成绩
		        				 if(sp.length==1)
		        				 {
		        					 String [] sp1 = sp[0].split("\\.");
		        					 if(sp1.length==2&&sp1[1].length()==2)
		        					 {
		        						 //真的找不到哪里错了。。
		        					 }
		        					 else if(sp1.length==2)
		        					 {
		        						for (int i = 0; i < sp1[1].length(); i++)
		        						{
		        							System.out.println(sp1[1].charAt(i));
		        							if (!Character.isDigit(sp1[1].charAt(i)))
		        							{
		        								logger.error("运动员成绩标签不是纯数字，重新输入文件");
		        								throw new SyntaxSpec();
		        							}
		        						}
		        					 }
		        						if(sp1.length==1)//不够两位自动补0
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
		        					        		 logger.error("运动员标签参数不够，重新输入文件");
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
		        					         logger.error("运动员标签成绩小于两位小数，自动补零");
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
		        					        		 logger.error("运动员标签参数错误，重新输入文件");
		        					        		 throw new NumOrder(x);
		        					        	 }
		        					        	 for(Athlete s:athletes)
		        					        	 {
		        					        		 if(m7.group(1).equals(s.getname()))
		        					        		 {
		        					        			 logger.error("运动员标签有相同名字，重新输入文件");
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
		        						 logger.error("运动员标签成绩不符合规则，重新输入文件");
		        						 throw new SyntaxSpec();
		        					 }
		        				 }
		        				 else
		        				 {
		        					 logger.error("运动员标签字符串不符合规则，重新输入文件");
		        					 throw new wrongformat();
		        				 }
		        			 }
		        			 else
		        			 {
		        				// System.out.println(line1[0]);
		        				 logger.error("运动员标签字符串不符合规则，重新输入文件");
		        				 throw new wrongformat();
		        			 }
		        		 }
		        		
		        	 else
		        	 {
		        		 String [] line1 = info.split("::");
		        			 if(line1[0].equals("Game "))//是game类型的
			        		 {
		        				 if(line1.length==2)
		        				 {
		        					 String [] line2 = line1[1].split("= ");
				        			 if(line2.length==1)
				        			 {
				        				 for (int i = 0; i < line2[0].length(); i++)
					        			 {
					        				// System.out.println(line2[0].charAt(i));
					        				 if (!Character.isDigit(line2[0].charAt(i)))//数字地方出现其他字符
					        				 {
					        					 logger.error("运动员标签game出现其他不是数字字符，重新输入文件");
					        					 throw new SyntaxSpec();
					        				 }
					        			 } String newinfo = null;
				        					    int p = Integer.valueOf(line2[0]).intValue();//自动改变跑道length
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
				        				 logger.error("game标签字符串不符合规则，重新输入文件");
				        				 throw new wrongformat();
				        			 }
		        				 }
		        				 else//格式完全不对
				        		 {
		        					 logger.error("game标签字符串不符合规则，重新输入文件");
				        			 throw new wrongformat();
				        		 }
				        		
			        			 
			        			
			        		 }
		        		 else if(line1[0].equals("NumOfTracks "))//是track
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
		        							 logger.error("track标签不是纯数字，重新输入文件");
		        							 throw new SyntaxSpec();
		        						 }
		        							
		        					}
		        				 }
		        				 else
		        				 {
		        					 logger.error("track标签格式不对，重新输入文件");
		        					 throw new SyntaxSpec();
		        				 }
		        			 }
		        			 else
		        			 {
		        				 logger.error("track标签格式不对，重新输入文件");
		        				 throw new SyntaxSpec();
		        			 }
		        			
		        		 }
		        		 else//标签不对
		        		 {
		        			 //System.out.println(info);
		        			 logger.error("文本中存在非法标签，重新输入文件");
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
    * 进行随机的排序
    * AF：对应于对轨道上的运动员进行分配跑道
    * RI：true
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
    * 按照成绩进行排序
    * AF:对应于对跑道上的运动员进行分配跑道
    * RI：true
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
    * 检查比赛人数不超过轨道数
    * 否则抛出异常
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
