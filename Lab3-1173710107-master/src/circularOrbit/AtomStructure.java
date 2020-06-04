package circularOrbit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import track.Track;
import centralObject.Nucleus;
import physicalObject.Electron;
public class AtomStructure extends ConcreteCircularOrbit<Nucleus,Electron>{
	String info;
	String elementName;
	int trackNum=0;
	 /**
     * ����һ���¹��
     * AF:�½�һ���������Ӧ��һ�����ӵĹ��
     * RI��true
     * immutable
     */
	public void newTrack()
	{
		trackNum++;
		this.addTrack("track"+trackNum, trackNum);
	}
	 /**
     * ɾ���������Ĺ��
     * AF:ɾ��������һ���������Ӧԭ���ϵĹ��
     * RI��true
     * immutable
     */
	public void clearTrack()
	{
		this.removeTrack(trackNum);
		trackNum--;
	}
	 /**
     * ��ȡ���ڹ��������
     * @return �������int����
     * AF����ȡԭ�ӹ���ĸ���
     * RI��true
     * immutable
     */
	public int getTrackNumbers()
	{
		return this.trackNum;
	}
	 /**
     * �ƶ�����
     * 
     * @param �ƶ����ӵ�source
     * @param �ƶ����ӵ�target
     * AF:��������������ԭ�ӹ��
     * RI��0<source<tracknum   0<target<tracknum
     * mutable
     */
	public void transitElectron(int source,int target)
	{
		if(source>trackNum||target>trackNum||source<=0||target<=0||source==target)//�жϲ����Ƿ���Ϲ���
		{
			System.out.println("���벻�Ϸ�!");
			return;
		}
		else
		{
			Track<Electron> a = this.tracks.get(source);
			Track<Electron> b = this.tracks.get(target);
			a.remove(new Electron(source));
			b.add(new Electron(target));
		}
		return;
	}
	 /**
     * �������ļ��ж�ȡ����Ϣ
     * RI��true
     * AF:���ļ��е���Ϣ���浽��Ӧ������������
     * immutable
     */
	void analysis()
	{
		Pattern p1 = Pattern.compile("ElementName ::= ([a-zA-Z]{1,2})");
		Matcher m1 = p1.matcher(info);
		m1.find();
		this.elementName = m1.group(1);
		Pattern p2 = Pattern.compile("NumberOfTracks ::= (\\d+)");
		Matcher m2 = p2.matcher(info);
		m2.find();
		this.trackNum = Integer.valueOf(m2.group(1));
		this.autoAddTrack(trackNum);
		Pattern p3 = Pattern.compile("(\\d+)/(\\d+)");
		Matcher m3 = p3.matcher(info);
		while(m3.find())
		{
			int layer = Integer.valueOf(m3.group(1));
			int num = Integer.valueOf(m3.group(2));
			for(int i=1;i<=num;i++)
			{
				this.addObject(layer, new Electron(layer));
			}
		}
	}
	 /**
     * ����track
     */
	public AtomStructure(String path) {
		this.info = this.readFile(path);
		analysis();
	}
    
	@Override
	public String toString() {
		StringBuilder a = new StringBuilder();
		a.append("Element Name:"+ this.elementName);
		a.append("\nTrack Numbers:" + this.trackNum);
		a.append("\n");
		for(Track<Electron> temp:this.tracks)
		{
			a.append(temp.singleLinetoString());
		}
		return a.toString();
	}
	public static void main(String[] args) {
		AtomStructure test= new AtomStructure("txt/AtomicStructure_Medium.txt");
		System.out.println(test);

	}

}
