package lab6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class keepfair implements Runnable {

  private int sumNumber = -1;
  private double throughtRate;
  private double justification = 0;


  public keepfair(int sumNumber) {
    this.sumNumber = sumNumber;
    setThroughtRate(0);
    setJustification(0);
  }

  public int getSumNumber() {
    return sumNumber;
  }

  public double getThroughtRate() {
    return throughtRate;
  }

  public void setThroughtRate(double throughtRate) {
    this.throughtRate = throughtRate;
  }

  public double getJustification() {
    return justification;
  }

  public void setJustification(double justification) {
    this.justification = justification;
  }

  @Override
  public void run() {

    int time = 1;
    boolean end = true;
    do {
      end = true;
      for (Map.Entry<Integer, monkey> monkeyEntry : MonkeyGenerator.getMonkeys().entrySet()) {
        if (monkeyEntry.getValue().getRungNumber() != 21) {
          end = false;
          break;
        }
      }

      if (!end) {
        time++;
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    } while (!end);

    Logger logger = Logger.getLogger(keepfair.class);
    logger.setLevel(Level.INFO);
    setThroughtRate(getSumNumber() * 1.0 / time);

    logger.info("吞吐率是 " + getThroughtRate() + "n/s");


    List<monkey> monkeysList =
        Collections.synchronizedList(new ArrayList<>(MonkeyGenerator.getMonkeys().values()));
    for (int i = 0; i < sumNumber - 1; ++i) {
      for (int j = i + 1; j < sumNumber; ++j) {
        int sleep1 = monkeysList.get(i).getSleep();
        int age1 = monkeysList.get(i).getAge();
        int sleep2 = monkeysList.get(j).getSleep();
        int age2 = monkeysList.get(j).getAge();

        if ((sleep2 - sleep1) * (sleep2 + age2 - sleep1 - age1) >= 0) {
          justification++;
        } else {
          justification--;
        }
      }
    }
    this.justification = justification * 2.0 / sumNumber / (sumNumber - 1);
    logger.info(" 公平性" + getJustification() + ".");

  }

}
