package P3;

public interface Piece {
	/*
	 * �÷�����Ҫ���ڻ�ȡ��ǰ���ӵ�name
	 * @Param ��Ҫ��ȡname������piece��xy
	 * @return ���ظ����ӵ�name ����ΪString
	 */
	public String getpname(int px,int py);
	/*
	 * �÷�����Ҫ���ڻ�ȡ������������ҵ�����
	 * @Param����Ϊ������
	 * @return����ֵΪ�������
	 */
	public String getpfrom(Piece1 piece);
	/*
	 * 
	 * �÷�����Ҫ���������ӵ��������
	 * @Param����fromΪ��ҵ����֣�pieceΪ���õ�����
	 */
	public void setpfrom(String from,Piece1 piece);
	/*
	 * �÷����ǻ�ȡ�����������ϵ�x����
	 * @Param ����piece
	 * @return �����ϵ�����x
	 */
	public int getpx(Piece1 piece);
	/*
	 * �÷����ǻ�ȡ�����������ϵ�y����
	 * @Param ����piece
	 * @return �����ϵ�����y
	 */
	public int getpy(Piece1 piece);
	/*
	 * �÷������ڻ�ȡ���ӵ�״̬��0��ʾδ���ã�1��ʾ�������������ϣ�2��ʾ���Ե�����
	 * @Param 	����piece
	 * @return �����ӵĵ�ǰ״̬int����
	 */
	public int getpstate(int px,int py);
	/*
	 * �÷��������������ӵ�name
	 * @Param ����piece�Լ�name
	 */
	public void setpname(String pname,Piece1 piece);
	/*
	 * �÷����������������������ϵ�x����
	 * @Param ������������xλ��px,����piece
	 */
	public void setpx(int px,Piece1 piece);
	/*
	 * �÷����������������������ϵ�y����
	 * @Param ������������xλ��py,����piece
	 */
	public void setpy(int py,Piece1 piece);
	/*
	 * �÷�������ɾ�������ϵ����ӣ�num��ʾ���������ĸ���ҵģ�num=0��ʾ�����һ������num=1��ʾ����Ҷ�������
	 * @Param ��Ҫɾ��������piece�Լ���ұ�־����num
	 */
	public void remove(Piece1 x,int num);
}
