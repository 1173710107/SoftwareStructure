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
     * 增加一个新轨道
     * AF:新建一个轨道，对应于一个电子的轨道
     * RI：true
     * immutable
     */
	public void newTrack()
	{
		trackNum++;
		this.addTrack("track"+trackNum, trackNum);
	}
	 /**
     * 删除掉最外层的轨道
     * AF:删除最外层的一个轨道，对应原子上的轨道
     * RI：true
     * immutable
     */
	public void clearTrack()
	{
		this.removeTrack(trackNum);
		trackNum--;
	}
	 /**
     * 获取现在轨道的数量
     * @return 轨道数量int类型
     * AF：获取原子轨道的个数
     * RI：true
     * immutable
     */
	public int getTrackNumbers()
	{
		return this.trackNum;
	}
	 /**
     * 移动电子
     * 
     * @param 移动电子的source
     * @param 移动电子的target
     * AF:两个数代表两个原子轨道
     * RI：0<source<tracknum   0<target<tracknum
     * mutable
     */
	public void transitElectron(int source,int target)
	{
		if(source>trackNum||target>trackNum||source<=0||target<=0||source==target)//判断参数是否符合规则
		{
			System.out.println("输入不合法!");
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
     * 分析从文件中读取的信息
     * RI：true
     * AF:将文件中的信息保存到对应的数据类型中
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
     * 构建track
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
