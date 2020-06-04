package circularOrbit;

import java.util.ArrayList;

public interface CircularOrbit<L,E> extends Iterable<E>//L�� central object
{
	public void setinfoall(ArrayList<String> info);
	public void Analysis();
	public void initarraylist();
	 /**
     * ����һ�����
     * 
     * @param �����Ĺ��������
     * @param ����Ĵ�С
     * @return ������ӳɹ��Ļ�������true,ʧ�ܷ���false
     * AF:name�ǹ�������ƣ�size�ǹ���Ĵ�С
     * RI:size>0
     * immutable
     */
	boolean addTrack(String name,double size);
	 /**
     * ɾ����index���track
     * 
     * @param index���track��ɾ����
     * * AF:index��Ӧ���ǹ���ı��
     * RI:index>0
     * immutable
     */
	void removeTrack(int index);
	 /**
     * �������ĵ�����
     * 
     * @param ��Ҫ��ӵ����ĵ�����center
     * @return ���ӳɹ��Ļ�������true��ʧ�ܷ���false
     * AF:center��Ӧ����ϵͳ�����ĵ�
     * RI��true
     * mutable
     */
	boolean addCenter(L center);
	 /**
     * ����һ����ͨ������
     * 
     * @param Ҫ���ӵ�����obj
     * @param ����ڵĲ���index
     * @return �����ɹ��Ļ�������true��ʧ�ܵĶ������false
     * AF:obj������ǹ���ϵ����壬index��Ӧ���ǹ���ı��
     * RI:index>0
     * mutable
     */
	boolean addObject(int index,E obj);
	 /**
     * ����һ����ͨ������
     * 
     * @param ��Ҫ��ӵ��Ĺ���Ĳ���index
     * @param ��Ҫ��ӵ�����obj
     * @param ��Ҫ��ӵ���λ��pos
     * @return �����ɹ��Ļ�������true,���򷵻�false
     * AF:obj������ǹ���ϵ����壬index��Ӧ���ǹ���ı��,pos���������ڹ���ϵĽǶ�λ��
     * RI:index>0��pos>0 pos<361
     * mutable
     */
	boolean addObject(int index,E obj,double pos);
	 /**
     * ������������֮��Ĺ�ϵ
     * 
     * @param ����source
     * @param ����target
     * @return �����ɹ��Ļ�������true,���򷵻�false
     * AF:source��target������ǹ���ϵ���������
     * RI:true
     * mutable
     */
	boolean addRelation(E source,E target);
	 /**
     * �ƶ�����object��index��
     * @param ����object 
     * @param �ƶ����Ĳ���index
     * @return �����ɹ��Ļ�������true�����򷵻�fasle
     * AF:object������ǹ���ϵ����壬index��Ӧ���ǹ���ı��
     * RI:index>0
     * mutable
     */
	E transit (E object, int index);
	 /**
     * �ƶ�����object����һ��position x
     * 
     * @param ��Ҫ�ƶ�������object
     * @param �ƶ�����position x
     * @return if �����ɹ��Ļ�������true,���򷵻�false
     * AF:object������ǹ���ϵ����壬x��Ӧ���Ǹ������ڹ���ϵ�λ��
     * RI:x��0 x<361
     * mutable
     */
	boolean move(E object, double x);
	 /**
     * ��ȡ���ߵľ���
     * @param ��ʼ���������source
     * @param Ŀ������target
     * @return ���ؾ���int���ͣ�����޷���ȡ��ȷ���룬����-1
     * AF:source��target������ǹ���ϵ���������
     * RI:true
     * mutable
     */
	int getLogicalDistance(E source,E target);
	 /**
     * ��ȡ���ߵľ���
     * @param ��ʼ���������source
     * @param Ŀ������target
     * @return ���ؾ���int���ͣ�����޷���ȡ��ȷ���룬����-1
     * AF:source��target������ǹ���ϵ���������
     * RI:true
     * mutable
     */
	double getPhysicalDistance(E source,E target);
	 /**
     * ��ȡÿ��������е���Ŀ
     * @return ÿ������ϵ�������Ŀ���ɵ�lsit
     */
	ArrayList<Integer> numOnEachTrack();
	 ArrayList<Pair<E,E>> getPair();
}
