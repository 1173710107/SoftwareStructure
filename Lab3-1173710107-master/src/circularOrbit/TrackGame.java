package circularOrbit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import physicalObject.Athlete;
public class TrackGame extends ConcreteCircularOrbit<Athlete,Athlete>{
	int length = 0;
	int Tracknum = 0;
	ArrayList<Athlete> athletes = new ArrayList<Athlete>();
	 /**
     * 增加一个新的轨道
     * AF:新建一个轨道，对应于一个跑道
     * RI：true
     * immutable
     */
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
   public void Analysis(String info)
     {
         Pattern p1 = Pattern.compile("Game ::= (100|200|400)");
         Matcher m1 = p1.matcher(info);
         m1.find();
         this.length = Integer.valueOf(m1.group(1));
         System.out.println("length = "+this.length);
         Pattern p2 = Pattern.compile("NumOfTracks ::= (4|5|6|7|8|9|10)");
         Matcher m2 = p2.matcher(info);
         m2.find();
         this.Tracknum = Integer.valueOf(m2.group(1));
         System.out.println("NumOfTracks = "+ this.Tracknum);
         for(int i=0;i<=this.Tracknum;i++)
         {
        	 this.addTrack("track"+i, i);
         }
         Pattern p3 = Pattern.compile("Athlete ::= <([a-zA-z]+),(\\d+),([A-Z]{3}),(\\d+),(\\d{1,2}.\\d{2})>");
         Matcher m3 = p3.matcher(info);
         while(m3.find())
         {
         athletes.add(new Athlete(m3.group(1),Integer.valueOf(m3.group(2)),m3.group(3),Integer.valueOf(m3.group(4)),Double.valueOf(m3.group(5))));
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
   @Override
     public String toString() {
 		return tracks.toString();
 	}
     public static void main(String[] args)
     {
    	 TrackGame x = new TrackGame();
         String info = x.readFile("txt/TrackGame.txt");
         System.out.println(info);
         x.Analysis(info);
         x.sortedArrange();
         System.out.println(x);
     }
}
