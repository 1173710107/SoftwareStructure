package lab6;

import java.util.Map;

public interface ladderchoice {
  /**
   * 选择方案
   * 
   * @param monkey 选择的猴子
   * @param monkeys 猴子集合
   * @param ladders 梯子集合
   * @return
   */
  public int chooseLadder(monkey monkey, Map<Integer, monkey> monkeys,
      Map<Integer, ladder> ladders);
}
