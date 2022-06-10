package logoland;

import java.util.*;
import lombok.AllArgsConstructor;
import org.apache.commons.collections4.iterators.PermutationIterator;

@AllArgsConstructor
public class MaxFinder {

    private final List<Integer> trunks;

    public Set<List<Integer>> find() {

        PermutationIterator<Integer> permIterator = new PermutationIterator<>(trunks);

        Map<Integer, Set<List<Integer>>> map = new TreeMap<>((o1, o2) -> o2 - o1);
        permIterator.forEachRemaining(perm -> {
            int profit = new TrunksProfitCalculator(perm).calculate();
            if (!map.containsKey(profit)) {
                Set<List<Integer>> list = new HashSet<>();
                map.put(profit, list);
            }
            map.get(profit).add(perm);
        });
        int maxProfit = map.keySet().stream().mapToInt(a -> a).max().getAsInt();
        return map.get(maxProfit);
    }
}
