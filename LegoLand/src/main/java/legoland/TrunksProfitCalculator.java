package logoland;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class TrunksProfitCalculator {

    private static int LEN = 3;
    private List<Integer> trunks;

    public int calculate() {
        int windowSize = 0;
        int totalLength = trunks.stream().mapToInt(a -> a).sum();
        int lengthIndex = 0;

        int profit = 0;
        int trunkIndex = 0;
        while (lengthIndex <= totalLength && trunkIndex < trunks.size()) {
            var currentTrunk = trunks.get(trunkIndex);
            if (currentTrunk + windowSize > LEN) {
                int sawedBlocks = LEN - windowSize;
                profit += profitOfTrunk(sawedBlocks);
                trunks = rebuildTrunks(trunks, currentTrunk, trunkIndex, sawedBlocks);
                lengthIndex += sawedBlocks;
                windowSize = 0;
            } else {
                profit += profitOfTrunk(currentTrunk);
                windowSize += currentTrunk;
                lengthIndex += currentTrunk;
                if (windowSize == LEN) {
                    windowSize = 0;
                }
            }
            trunkIndex++;
        }

        return profit;
    }

    private List<Integer> rebuildTrunks(List<Integer> trunks, int trunk, int index, int sawedBlocks) {
        int remainedBlocks = trunk - sawedBlocks;
        List<Integer> newTrunks = new ArrayList<>(trunks.subList(0, index));
        newTrunks.add(sawedBlocks);
        newTrunks.add(remainedBlocks);
        newTrunks.addAll(trunks.subList(index + 1, trunks.size()));
        return newTrunks;
    }

    private int profitOfTrunk(Integer trunk) {
        if (trunk == 1) {
            return -1;
        }
        if (trunk == 2) {
            return 3;
        }
        return 1;
    }
}
