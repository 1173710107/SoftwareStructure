package P3;

public interface Game {
	/*
	 * �÷�����Ҫ���ڳ�ʼ����Ϸ������������̣����
	 * @Param ����Ϊ����player�������Լ���Ϸ����num ����num=0ʱ����ʾ��������Ϸ����num=1ʱ����ʾ��Χ����Ϸ
	 */
	public void initgameplayer(String name1,String name2,int num);
	/*
	 * �÷������ڻ�ȡ��λ�ö�Ӧ�����
	 * @Param ��ȡ����λ��position
	 * @return ��λ�õ����
	 */
	public Player1 getPositionplayer(Position1 x);
	/*
	 * �÷������ڻ�ȡ��λ�õ�����
	 * @Param ��ȡ���ӵ�λ��position
	 * @return ��λ�õ�����
	 */
	public Piece1 getPositionPiece(Position1 x);
	/*
	 * �÷������ڻ�ȡ��������������ӵ�����
	 * @Param ���player
	 * @return ��������������ӵ�����
	 */
	public int getnumPiece(int num);
}
