package lab6;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


public class monkey extends Thread {


  private int number;
  private int direction;
  private int speed;
  private int sleep;
  private int ladderNumber;
  private int rungNumber;
  private int realspeed;
  private int age;


  /**
   * 构造器
   * 
   * @param number 猴子的号码.
   * @param direction 猴子的方向.
   * @param velocity 猴子的最大速度.
   * @param sleep 猴子等待时间.
   */
  public monkey(int number, int direction, int speed, int sleep) {
    this.number = number;
    if (direction == 1) {
      this.direction = 1;
    } else {
      this.direction = 0;
    }
    this.speed = speed;
    this.sleep = sleep;
    setLadderNumber(0);
    setRungNumber(0);
    setSpeed(speed);
    setAge(0);
  }

  public int getAge() {
    return age;
  }

  public void setAge(int age) {
    this.age = age;
  }

  public int getNumber() {
    return number;
  }

  public void setNumber(int number) {
    this.number = number;
  }

  public int getDirection() {
    return direction;
  }

  public void setDirection(int direction) {
    this.direction = direction;
  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int speed) {
    this.speed = speed;
  }

  public int getSleep() {
    return sleep;
  }

  public void setSleep(int sleep) {
    this.sleep = sleep;
  }

  public int getLadderNumber() {
    return ladderNumber;
  }

  public void setLadderNumber(int ladderNumber) {
    this.ladderNumber = ladderNumber;
  }

  public int getRungNumber() {
    return rungNumber;
  }

  public void setRungNumber(int rungNumber) {
    this.rungNumber = rungNumber;
  }

  public int getRealspeed() {
    return realspeed;
  }

  public void setRealspeed(int realspeed) {
    this.realspeed = realspeed;
  }


  private int findLadder() {
    int method = (int) (Math.random() + 1.5);
    ladderchoice strategy;
    if (method == 1) {
      strategy = new ladderchoice1();
    } else {
      strategy = new ladderchoice2();
    }
    return strategy.chooseLadder(this, MonkeyGenerator.getMonkeys(), MonkeyGenerator.getLadders());
  }

  private int moveOnLadder() {
    go y = new go();
    return y.walk(this, MonkeyGenerator.getMonkeys(), MonkeyGenerator.getLadders());
  }

  @Override
  public void run() {
    try {
      Logger logger = Logger.getLogger(monkey.class);
      logger.setLevel(Level.INFO);

      Thread.sleep(1000 * sleep);
      setSleep(0);

      logger.info("产生一只猴子， number: " + number + ", ");
      if (direction == 1) {
        logger.info("Direction: L->R");
      } else {
        logger.info("Direction: R->L");
      }
      logger.info(", speed: " + speed);


      int ladderNumber = 0;
      do {

        ladderNumber = findLadder();
        logger.info("猴子 " + number + " 在等待在 ");
        if (direction == 1) {
          logger.info("左边");
        } else {
          logger.info("右边");
        }
        if (age <= 1) {
          logger.info(age + " 秒过去出生后");
        } else {
          logger.info(age + " 秒过去出生后");
        }


        if (ladderNumber == 0) {
          age++;
          Thread.sleep(1000);
        } else {
          setLadderNumber(ladderNumber);
          setRungNumber(1);
          age++;
          Thread.sleep(1000);
        }
      } while (ladderNumber == 0);

      int nextRungNumber = 1;
      do {
        nextRungNumber = 0;
        int rn = getRungNumber();
        if (getDirection() == -1) {
          rn = 21 - getRungNumber();
        }
        if (direction == 1) {
          logger.info(
              "猴子     " + number + " 在 第" + rn + " 个踏板上面" + ladderNumber + "号梯子上面移动" + "方向左到右");
        } else {
          logger.info(
              "猴子     " + number + " 在 第" + rn + " 个踏板上面" + ladderNumber + "号梯子上面移动" + "方向右到左");
        }
        /*
         * logger.info(", and it has been " + age); if (age <= 1) {
         * logger.info(" second since he was born."); } else {
         * logger.info(" seconds since he was born."); }
         */


        nextRungNumber = moveOnLadder();
        if (nextRungNumber > 20) {
          setRealspeed(speed);
          setRungNumber(nextRungNumber);
        } else {
          int realspeed = nextRungNumber - getRungNumber();
          setRealspeed(realspeed);
          setRungNumber(nextRungNumber);
        }

        age++;
        Thread.sleep(1000);
      } while (nextRungNumber <= 20);

      if (direction == 1) {
        logger.info("猴子 " + number + " 到达 " + "right");
      } else {
        logger.info("猴子 " + number + " 到达 " + "left");
      }
      // logger.info(" riverbank successfully, and the process took up " + age + " seconds.");

    } catch (InterruptedException e1) {
      e1.printStackTrace();
    }
  }



}
