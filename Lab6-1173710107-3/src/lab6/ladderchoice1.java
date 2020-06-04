package lab6;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class ladderchoice1 implements ladderchoice {
  @Override
  public int chooseLadder(monkey monkey, Map<Integer, monkey> monkeys,
      Map<Integer, ladder> ladders) {

    synchronized (monkeys) {
      List<ladder> nullLadders = Collections.synchronizedList(new ArrayList<ladder>());
      int direction = monkey.getDirection();
      List<ladder> directionLadders = Collections.synchronizedList(new ArrayList<ladder>());

      for (Map.Entry<Integer, ladder> laddersEntry : ladders.entrySet()) {
        ladder tempLadder = laddersEntry.getValue();
        if (tempLadder.getDirection() == 0) {
          nullLadders.add(tempLadder);
        } else if (tempLadder.getDirection() == direction) {
          List<pedal> rungs = tempLadder.getList();
          if (rungs.get(0).isIsempty()) {
            directionLadders.add(tempLadder);
          }
        }
      }

      if (nullLadders.size() == 0) {
        int size = directionLadders.size();
        if (size == 0) {
          return 0;
        } else {
          int randomNumber = (int) Math.random() * (size - 1);
          ladder ladder = directionLadders.get(randomNumber);
          List<pedal> rungs = ladder.getList();
          rungs.get(0).setInMonkey(monkey);
          rungs.get(0).setIsempty(false);
          ladder.setList(rungs);
          int monkeyAmount = ladder.getMonkeyAmount() + 1;
          ladder.setMonkeyAmount(monkeyAmount);
          ladders.put(ladder.getID(), ladder);
          return ladder.getID();
        }
      } else {
        int size = nullLadders.size();
        int randomNumber = (int) Math.random() * (size - 1);
        ladder lad = nullLadders.get(randomNumber);
        lad.setDirection(direction);
        List<pedal> rungs = lad.getList();
        rungs.get(0).setInMonkey(monkey);
        rungs.get(0).setIsempty(false);
        lad.setList(rungs);
        int monkeyAmount = lad.getMonkeyAmount() + 1;
        lad.setMonkeyAmount(monkeyAmount);
        ladders.put(lad.getID(), lad);
        return lad.getID();
      }
    }
  }


}
