package exception;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class SyntaxSpec extends newfile1{//Ԫ�ض����﷨�淶��һ��
	public SyntaxSpec()
	{
		JOptionPane pane = new JOptionPane("�ı�����Ԫ�غͶ����﷨�淶��һ�£�����������һ���ı���", 0);
		JDialog dialog = pane.createDialog("");
		dialog.setAlwaysOnTop(true);
		dialog.setVisible(true);
		dialog.dispose();
		//JOptionPane.showMessageDialog(null, "�ı�����Ԫ�غͶ����﷨�淶��һ�£�����������һ���ı���");
	}
}
