package lab6;

import java.util.List;
import java.util.Map;

public class go {


  /**
   * 猴子在踏板上面行走
   * 
   * @param monkey 猴子
   * @param monkeys 猴子的集合
   * @param ladders 梯子的集合
   * 
   * @return 下一个要跳的踏板
   */
  public int walk(monkey monkey, Map<Integer, monkey> monkeys, Map<Integer, ladder> ladders) {
    synchronized (monkeys) {
      int ladderNumber = monkey.getLadderNumber();
      int rungNumber = monkey.getRungNumber();
      int speed = monkey.getSpeed();
      ladder ladder = ladders.get(ladderNumber);
      List<pedal> rungs = ladder.getList();
      int maxRungNumber = Math.min(20, rungNumber + speed);
      System.out.println("speed" + speed + "");
      int nextRungNumber = rungNumber + speed;
      for (int i = rungNumber + 1; i <= maxRungNumber; ++i) {
        if (!rungs.get(i - 1).isIsempty()) {
          nextRungNumber = i - 1;
          break;
        }
      }

      if (nextRungNumber > 20) {
        nextRungNumber = 21;
        rungs.get(rungNumber - 1).setIsempty(true);
        int monkeyAmount = ladder.getMonkeyAmount() - 1;
        ladder.setList(rungs);
        ladder.setMonkeyAmount(monkeyAmount);
        if (monkeyAmount == 0) {
          ladder.setDirection(0);
        }
      } else {
        rungs.get(rungNumber - 1).setIsempty(true);
        rungs.get(nextRungNumber - 1).setInMonkey(monkey);
        rungs.get(nextRungNumber - 1).setIsempty(false);
        ladder.setList(rungs);
      }

      ladders.put(ladderNumber, ladder);

      System.out.println("下一个要走的踏板" + nextRungNumber);
      return nextRungNumber;
    }
  }



}
