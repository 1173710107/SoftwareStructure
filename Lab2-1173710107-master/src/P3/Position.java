package P3;

public interface Position {
	/*
	 * �÷������ڻ�ȡ�����xֵ
	 * @Param λ������position
	 * @return x����
	 */
	public int x(Position1 position);
	/*
	 * �÷������ڻ�ȡ�����yֵ
	 * @Param λ������position
	 * @return y����
	 */
	public int y(Position1 position);
	/*
	 * �÷��������ж����������Ƿ����
	 * @Param ��������x,y
	 * @return ����������ͬ����true,��ͬ����false
	 */
	public boolean equals(Position1 x,Position1 y);
	
}
