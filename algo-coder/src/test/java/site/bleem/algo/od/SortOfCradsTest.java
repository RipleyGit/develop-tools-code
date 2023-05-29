package site.bleem.algo.od;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;


public class SortOfCradsTest {
    @Test
    public void test(){
        //        Assert.assertEquals(new int[]{4,4,4,3,3,2,2,1,1,3},SortOfCrads.executor(new int[]{1,3,3,3,2,1,5}));
//        Assert.assertEquals(new int[]{4,4,4,3,3,2,2,1,1,3},SortOfCrads.executor(new int[]{4,4,2,1,2,1,3,3,3,4}));
        Assert.assertEquals("3 3 3 1 1 5 2", SortOfCrads.executor(Arrays.asList(1,3,3,3,2,1,5)));
        Assert.assertEquals("4 4 4 3 3 2 2 1 1 3",SortOfCrads.executor(Arrays.asList(4,4,2,1,2,1,3,3,3,4)));
    }


    /**
     * @param list
     * @return
     */
    public static int[] executor(int[] array) {
        Arrays.sort(array);

        //分组
        HashMap<Integer, Integer> cardMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            Integer integer = array[i];
            Integer count = 0;
            if (cardMap.containsKey(integer)) {
                count = cardMap.get(integer);
            }
            count++;

            cardMap.put(integer, count);
        }

        ArrayList<Integer> kings = new ArrayList<>();
        ArrayList<Integer> three = new ArrayList<>();
        ArrayList<Integer> two = new ArrayList<>();
        ArrayList<Integer> one = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : cardMap.entrySet()) {
            if (entry.getValue() == 4) {
                kings.add(entry.getKey());
            } else if (entry.getValue() == 3) {
                three.add(entry.getKey());
            } else if (entry.getValue() == 2) {
                two.add(entry.getKey());
            } else {
                one.add(entry.getKey());
            }
        }
        int[] result = new int[array.length];
        int length = 0;
        List<Integer> collect = kings.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            result[length++] = collect.get(i);
            result[length++] = collect.get(i);
            result[length++] = collect.get(i);
            result[length++] = collect.get(i);
        }
        //堆葫芦
        List<Integer> threeSort = three.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < threeSort.size(); i++) {
            result[length++] = threeSort.get(i);
            result[length++] = threeSort.get(i);
            if ((i + 1) % 2 == 0) {
                one.add(threeSort.get(i));
            } else {
                result[length++] = threeSort.get(i);
            }
        }
        //堆葫芦
        List<Integer> twoSort = two.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < twoSort.size(); i++) {
            result[length++] = twoSort.get(i);
            result[length++] = twoSort.get(i);
        }
        //堆葫芦
        List<Integer> oneSort = one.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        for (int i = 0; i < oneSort.size(); i++) {
            result[length++] = oneSort.get(i);
        }

        return result;
    }
}