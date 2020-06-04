package lab6;

public class pedal {
  private boolean isempty = true;
  monkey inMonkey = null;

  public boolean isIsempty() {
    return isempty;
  }

  public void setIsempty(boolean isempty) {
    this.isempty = isempty;
  }

  public monkey getInMonkey() {
    return inMonkey;
  }

  public void setInMonkey(monkey inMonkey) {
    this.inMonkey = inMonkey;
  }



  public void setmonkey(monkey inmonkey) {
    if (isempty) {
      this.inMonkey = inmonkey;
    } else {
      System.out.println("踏板上面已经有猴子了！！！");
      System.exit(0);
    }

  }
}
