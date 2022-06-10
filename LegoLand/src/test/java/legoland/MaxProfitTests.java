package legoland;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import logoland.MaxFinder;
import org.junit.Assert;
import org.junit.Test;

public class MaxProfitTests {

    @Test
    public void maxProfitOf1_2_3() {
        List<Integer> trunks = new ArrayList<>();
        trunks.add(1);
        trunks.add(2);
        trunks.add(3);
        var list = new MaxFinder(trunks).find();
        Assert.assertTrue(list.contains(List.of(1, 3, 2)));
        Assert.assertTrue(list.contains(List.of(2, 3, 1)));
    }

    @Test
    public void multiSawmillTest() {

        List<List<Integer>> riverTrunks = new ArrayList<>();
        riverTrunks.add(List.of(1, 2, 1));
        riverTrunks.add(List.of(1, 2));
        riverTrunks.add(List.of(1, 4));
        List<Set<List<Integer>>> result = riverTrunks.stream().map(trunks -> new MaxFinder(trunks).find())
                .collect(Collectors.toList());
        Set<List<Integer>> sawmill1 = new HashSet<>();
        sawmill1.add(List.of(1, 2, 1));
        sawmill1.add(List.of(2, 1, 1));
        Assert.assertTrue(result.contains(sawmill1));

        Set<List<Integer>> sawmill2 = new HashSet<>();
        sawmill2.add(List.of(2, 1));
        sawmill2.add(List.of(1, 2));
        Assert.assertTrue(result.contains(sawmill2));

        Set<List<Integer>> sawmill3 = new HashSet<>();
        sawmill3.add(List.of(1, 4));
        Assert.assertTrue(result.contains(sawmill3));
    }
}
