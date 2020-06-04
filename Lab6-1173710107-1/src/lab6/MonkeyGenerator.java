package lab6;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class MonkeyGenerator {
  private static Map<Integer, monkey> monkeys = new ConcurrentHashMap<Integer, monkey>();
  private static Map<Integer, ladder> ladders = new ConcurrentHashMap<Integer, ladder>();

  public static Map<Integer, monkey> getMonkeys() {
    return monkeys;
  }

  public static void setMonkeys(Map<Integer, monkey> monkeys) {
    MonkeyGenerator.monkeys = monkeys;
  }

  public static Map<Integer, ladder> getLadders() {
    return ladders;
  }

  public static void setLadders(Map<Integer, ladder> ladders) {
    MonkeyGenerator.ladders = ladders;
  }

  /**
   * 产生所有的猴子
   * 
   * @param k 每次产生所有猴子的数量
   * @param time 产生猴子的时间
   * @param sum 总共要产生的猴子的总数
   * @param maxSpeed 猴子的最大速度
   * 
   * @return 返回猴子的集合
   */
  public static Map<Integer, monkey> generateMonkeys(int k, int time, int sum, int maxSpeed) {

    int remainNum = sum;
    int number = 1;
    int sleep = 0;
    int ladderNumber = 10;
    int rungNumber = 20;
    for (int i = 1; i <= ladderNumber; i++) {
      ladder ladder = new ladder(i - 1, rungNumber);
      ladders.put(i - 1, ladder);
    }



    while (remainNum >= k) {
      for (int i = 0; i < k; ++i) {
        int direction = (int) (Math.random() + 1.5);// 1/2
        int speed = (int) (Math.random() * maxSpeed);
        monkey monkey = new monkey(number, direction, speed, sleep * time);
        monkeys.put(number, monkey);
        number++;
      }
      sleep++;
      remainNum -= k;
    }

    for (int i = 0; i < remainNum; ++i) {
      int direction = (int) (Math.random() + 1.5);// 1/2
      int speed = (int) (Math.random() * maxSpeed);
      monkey monkey = new monkey(number, direction, speed, sleep * time);
      monkeys.put(number, monkey);
      number++;
    }
    for (Map.Entry<Integer, monkey> monkeyEntry : monkeys.entrySet()) {
      monkey tempMonkey = monkeyEntry.getValue();
      Thread monkeyThread = new Thread(tempMonkey);
      monkeyThread.start();
    }
    int sumNumber = monkeys.size();
    keepfair x = new keepfair(sumNumber);
    Thread timeThread = new Thread(x);
    timeThread.start();
    return monkeys;
  }



}
