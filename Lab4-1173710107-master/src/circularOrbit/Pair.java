package circularOrbit;

public class Pair<A,B> {
    final A element1;
    final B element2;
	 /**
     * ������
     * @param ��ʼ��a.
     * @param �ս��b
     */
	public Pair(A a,B b) {
		this.element1 = a;
		this.element2 = b;
	}
	 /**
     * ��ȡ��һ��Ԫ��
     * @return ���ص�һ��Ԫ��
     */
	public A get1()
	{
		return element1;
	}
	 /**
     * ��ȡ�ڶ���Ԫ��
     * 
     * @return ���صڶ���Ԫ��
     */
	public B get2()
	{
		return element2;
	}
	@Override
	public String toString() {
		return "Pair :element1=" + element1 + ", element2=" + element2;
	}
	/*public static void main(String args[])
	{
		Pair<Integer,String> a = new Pair<Integer,String>(77,"hello");
		System.out.println(a.get1());
		System.out.println(a.get2());
		System.out.println(a);
		
	}*/
}
