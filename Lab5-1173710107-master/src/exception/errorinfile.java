package exception;

import javax.swing.JOptionPane;

public class errorinfile extends RuntimeException {
  public void errorinfile1() {
    JOptionPane.showMessageDialog(null, "程序结束！");
    System.exit(0);
  }
}
