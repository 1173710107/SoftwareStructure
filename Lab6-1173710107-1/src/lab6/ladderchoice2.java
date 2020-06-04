package lab6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ladderchoice2 implements ladderchoice {
  @Override
  public int chooseLadder(monkey monkey, Map<Integer, monkey> monkeys,
      Map<Integer, ladder> ladders) {
    synchronized (monkeys) {
      int direction = monkey.getDirection();

      int maxspeed = 0;
      int ladderNumber = 0;

      List<ladder> nullLadders = Collections.synchronizedList(new ArrayList<ladder>());

      for (Map.Entry<Integer, ladder> ladderEntry : ladders.entrySet()) {
        ladder tempLadder = ladderEntry.getValue();
        if (tempLadder.getDirection() == direction) {
          List<pedal> rungs = tempLadder.getList();
          if (rungs.get(0).isIsempty()) {
            for (int i = 2; i <= 20; ++i) {
              if (!rungs.get(i - 1).isIsempty()) {
                monkey tempMonkey = rungs.get(i - 1).inMonkey;
                int realspeed = tempMonkey.getRealspeed();
                if (maxspeed < realspeed) {
                  maxspeed = realspeed;
                  ladderNumber = tempLadder.getID();
                }
                break;
              }
            }
          }
        } else if (tempLadder.getDirection() == 0) {
          nullLadders.add(tempLadder);
        }
      }

      if (ladderNumber != 0) {
        ladder ladder = ladders.get(ladderNumber);
        List<pedal> rungs = ladder.getList();
        rungs.get(0).setInMonkey(monkey);
        rungs.get(0).setIsempty(false);
        ladder.setList(rungs);
        int monkeyAmount = ladder.getMonkeyAmount() + 1;
        ladder.setMonkeyAmount(monkeyAmount);
        ladders.put(ladderNumber, ladder);
      } else {
        int size = nullLadders.size();
        if (size != 0) {
          int randomNumber = (int) Math.random() * (size - 1);
          ladder ladder = nullLadders.get(randomNumber);
          ladderNumber = ladder.getID();
          ladder.setDirection(direction);
          List<pedal> rungs = ladder.getList();
          rungs.get(0).setInMonkey(monkey);
          rungs.get(0).setIsempty(false);
          ladder.setList(rungs);
          int monkeyAmount = ladder.getMonkeyAmount() + 1;
          ladder.setMonkeyAmount(monkeyAmount);
          ladders.put(ladder.getID(), ladder);
        }
      }

      return ladderNumber;
    }


  }
}
