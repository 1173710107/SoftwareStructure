package exception;

import javax.swing.JOptionPane;

public class notfoundnewfile extends RuntimeException {
  void exit() {
    JOptionPane.showMessageDialog(null, "重新输入的文本的参数不符合任何一个应用，程序结束！");
    System.exit(0);
  }
}
