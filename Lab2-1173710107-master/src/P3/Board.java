package P3;

public interface Board {
	/*
	 * �÷����ǻ�ȡxy��Ӧλ�õ����ӵ���Ϣ
	 * @Param ��ȡ���ӵ�λ�õ�x y����
	 * @return ��λ�õ�����
	 */
	public Piece1 getPiece(int x,int y);
	/*
	 * �÷����ǽ����ӷ������̵Ķ�Ӧλ����
	 * @Param ����piece�Լ����õ�λ��x y
	 */
	public void setPiece(int x,int y,Piece1 piece);
	/*
	 * �÷������ڼ�������xyλ�������Ƿ����
	 * @Param ������x y���� 
	 * @return �����λ����������ģ���ô����true�����򷵻�false
	 */
	public boolean isavailable(int x,int y);
	/*
	 * �÷������������Ƿ���������
	 * @Param ��Ҫ��������piece
	 * @return ����������������ϣ�����true�����򷵻�false
	 */
	public boolean isinborad(Piece1 piece);
	/*
	 * �÷������ڻ�ȡ��������������ӵ�����
	 * @Param ���player
	 * @return ��������������ӵ�����
	 */
	public int getplayernum(Player1 player);
}
