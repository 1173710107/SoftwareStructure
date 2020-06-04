package circularOrbit;

public class Pair<A,B> {
    final A element1;
    final B element2;
	 /**
     * 构造器
     * @param 起始点a.
     * @param 终结点b
     */
	public Pair(A a,B b) {
		this.element1 = a;
		this.element2 = b;
	}
	 /**
     * 获取第一个元素
     * @return 返回第一个元素
     */
	public A get1()
	{
		return element1;
	}
	 /**
     * 获取第二个元素
     * 
     * @return 返回第二个元素
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
