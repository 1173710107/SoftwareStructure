package circularOrbit;

import java.util.ArrayList;

public interface CircularOrbit<L,E> extends Iterable<E>//L是 central object
{
	public void setinfoall(ArrayList<String> info);
	public void Analysis();
	public void initarraylist();
	 /**
     * 增加一个轨道
     * 
     * @param 新增的轨道的名称
     * @param 轨道的大小
     * @return 轨道增加成功的话，返回true,失败返回false
     * AF:name是轨道的名称，size是轨道的大小
     * RI:size>0
     * immutable
     */
	boolean addTrack(String name,double size);
	 /**
     * 删除掉index层的track
     * 
     * @param index层的track被删除掉
     * * AF:index对应的是轨道的编号
     * RI:index>0
     * immutable
     */
	void removeTrack(int index);
	 /**
     * 增加中心点物体
     * 
     * @param 需要添加的中心点物体center
     * @return 增加成功的话，返回true，失败返回false
     * AF:center对应整个系统的中心点
     * RI：true
     * mutable
     */
	boolean addCenter(L center);
	 /**
     * 增加一个普通的物体
     * 
     * @param 要增加的物体obj
     * @param 添加在的层数index
     * @return 操作成功的话，返回true，失败的额话，返回false
     * AF:obj代表的是轨道上的物体，index对应的是轨道的编号
     * RI:index>0
     * mutable
     */
	boolean addObject(int index,E obj);
	 /**
     * 增加一个普通的物体
     * 
     * @param 需要添加到的轨道的层数index
     * @param 需要添加的物体obj
     * @param 需要添加到的位置pos
     * @return 操作成功的话，返回true,否则返回false
     * AF:obj代表的是轨道上的物体，index对应的是轨道的编号,pos代表物体在轨道上的角度位置
     * RI:index>0，pos>0 pos<361
     * mutable
     */
	boolean addObject(int index,E obj,double pos);
	 /**
     * 增加两个物体之间的关系
     * 
     * @param 物体source
     * @param 物体target
     * @return 操作成功的话，返回true,否则返回false
     * AF:source，target代表的是轨道上的两个物体
     * RI:true
     * mutable
     */
	boolean addRelation(E source,E target);
	 /**
     * 移动物体object到index层
     * @param 物体object 
     * @param 移动到的层数index
     * @return 操作成功的话，返回true，否则返回fasle
     * AF:object代表的是轨道上的物体，index对应的是轨道的编号
     * RI:index>0
     * mutable
     */
	E transit (E object, int index);
	 /**
     * 移动物体object到另一个position x
     * 
     * @param 需要移动的物体object
     * @param 移动到的position x
     * @return if 操作成功的话，返回true,否则返回false
     * AF:object代表的是轨道上的物体，x对应的是该物体在轨道上的位置
     * RI:x》0 x<361
     * mutable
     */
	boolean move(E object, double x);
	 /**
     * 获取两者的距离
     * @param 开始计算的物体source
     * @param 目标物体target
     * @return 返回距离int类型，如果无法获取正确距离，返回-1
     * AF:source，target代表的是轨道上的两个物体
     * RI:true
     * mutable
     */
	int getLogicalDistance(E source,E target);
	 /**
     * 获取两者的距离
     * @param 开始计算的物体source
     * @param 目标物体target
     * @return 返回距离int类型，如果无法获取正确距离，返回-1
     * AF:source，target代表的是轨道上的两个物体
     * RI:true
     * mutable
     */
	double getPhysicalDistance(E source,E target);
	 /**
     * 获取每条轨道上有的数目
     * @return 每条轨道上的物体数目构成的lsit
     */
	ArrayList<Integer> numOnEachTrack();
	 ArrayList<Pair<E,E>> getPair();
}
