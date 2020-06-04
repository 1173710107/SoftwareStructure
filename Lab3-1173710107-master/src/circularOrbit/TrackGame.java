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
     * ����һ���µĹ��
     * AF:�½�һ���������Ӧ��һ���ܵ�
     * RI��true
     * immutable
     */
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
