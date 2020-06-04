package lab6;

import java.util.ArrayList;
import java.util.List;

public class ladder {
  List<pedal> list = new ArrayList<pedal>();
  private int ID = -1;
  private int direction = -1;
  private int monkeyAmount;

  public ladder(int ID, int pedalnum) {
    this.ID = ID;
    for (int i = 0; i < pedalnum; i++) {
      list.add(new pedal());
    }
    this.direction = 0;
  }

  public List<pedal> getList() {
    return list;
  }

  public void setList(List<pedal> list) {
    this.list = list;
  }

  public int getID() {
    return ID;
  }

  public void setID(int iD) {
    ID = iD;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public int getMonkeyAmount() {
    return monkeyAmount;
  }

  public void setMonkeyAmount(int monkeyAmount) {
    this.monkeyAmount = monkeyAmount;
  }
}
