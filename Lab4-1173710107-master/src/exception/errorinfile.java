package exception;

import javax.swing.JOptionPane;

public class errorinfile extends RuntimeException{
	public void errorinfile1()
	{
		JOptionPane.showMessageDialog(null, "³ÌÐò½áÊø£¡");
		System.exit(0);
	}
}
