package P3;

import java.util.Set;

public interface Player {
	/*
	 * �÷������ڻ�ȡ��ҵ�name,����num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 * @Param ��ұ�־����num
	 * @return ���name,String����
	 */
	public String getplayername(int num);
	/*
	 * �÷������ڻ�ȡ����ҵ����ӵļ��ϣ� ����num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 * @Param ��ұ�־����num
	 * @return ����ҵ����ӵļ���set
	 */
	public Set<Piece1> getplayerPieces(int num);
	/*
	 * �÷������ڻ�ȡ����ҵĲ�����ʷ��¼������num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 * @Param ��ұ�־����num
	 * @return ����ҵĲ�����ʷStirngBuilder
	 */
	public StringBuilder getplayerhistory(int num);
	/*
	 * �÷�������������ӵ������
	 * @Param �������piece�Լ�num����num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 * @return �����ӳɹ�����true,���򷵻�false
	 */
	public boolean addPiece(Piece1 x,int num);
	/*
	 * �÷��������жϸ�����Ƿ��и����ӣ�����num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 * @Param ����piece�Լ���ұ�־����num
	 * @return �������Ұ��������ӣ�����true�����򷵻�false
	 */
	public boolean iscontainPiece(int num,Piece1 x);
	/*
	 * �÷������������������
	 * @Param �������name�Լ�num,����num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 */
	public void setplayername(String name,int num);
	/*
	 * �÷�����Ҫ���ڼ�¼��ҵĲ���
	 * @Param step��ʾ��ҵĲ������裬�Լ�num������num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 */
	public void addhistory(int num,String step);
	/*
	 * �÷������ڻ�ȡ��ң�����num����ұ�־num=0��ʾ�����һ��num=1��ʾ����Ҷ�
	 * @Param ��ұ�־num
	 */
	public Player1 getplayer(int num);
}
