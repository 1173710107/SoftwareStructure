package exception;

import javax.swing.JOptionPane;

public class notfoundnewfile extends RuntimeException{
	void exit()
	{
		JOptionPane.showMessageDialog(null, "����������ı��Ĳ����������κ�һ��Ӧ�ã����������");
		System.exit(0);
	}
}
