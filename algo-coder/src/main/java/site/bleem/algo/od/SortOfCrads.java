package site.bleem.algo.od;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 整理扑克牌
 */
public class SortOfCrads {


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        while (scanner.hasNext()) {
            if (scanner.hasNextInt()){
                list.add(scanner.nextInt());
            }else if ("".equals(scanner.nextLine())){
                break;
            }
        }
        String result = executor(list);
        System.out.println(result);
//        int[] array = new int[list.size()];
//        for (int i = 0; i < list.size(); i++) {
//            array[i] = list.get(i);
//        }
//        int[] executor = executor(array);
//        for (int i = 0; i < executor.length; i++) {
//            if (i != 0) {
//                System.out.print(" ");
//            }
//            System.out.print(executor[i]);
//        }
    }

    public static String executor(List<Integer> list) {
        Card[] temp = new Card[13];
        //牌初始化
        for (int i = 0; i < list.size(); i++) {
            int num = list.get(i);
            Card card = temp[num - 1];
            if (card == null) {
                card = new Card(num - 1, 1);
                temp[num - 1] = card;
            } else {
                card.count++;
            }
        }
        //从大到小排序
        Arrays.sort(temp, (o1, o2) -> {
            if (o1 == null) {
                return -1;
            }
            if (o2 == null) {
                return 1;
            }
            if (o2.count == o1.count) {
                return o2.faces - o1.faces;
            }
            return o2.count - o1.count;
        });
        ArrayList<String> result = new ArrayList<>();
        ArrayList<Card> kings = new ArrayList<>();
        ArrayList<Card> three = new ArrayList<>();
        ArrayList<Card> two = new ArrayList<>();
        ArrayList<Card> one = new ArrayList<>();
        //分组
        for (Card card : temp) {
            if (card == null) continue;
            if (card.count == 4) {
                kings.add(card);
            }else if (card.count == 3) {
                three.add(card);
            } else if (card.count == 2) {
                two.add(card);
            } else if (card.count == 1) {
                one.add(card);
            }
        }
        //倒排王炸
        kings.sort((o1, o2) -> {
            if (o2.count == o1.count) {
                return o2.faces - o1.faces;
            }
            return o2.count - o1.count;
        });
        for (int i = 0; i < kings.size(); i++) {
            for (int j = 0; j < 4; j++) {
                result.add(String.valueOf(kings.get(i).faces + 1));
            }
        }

        for (int i = 0; i < three.size(); i++) {
            for (int j = 0; j < 3; j++) {
                result.add(String.valueOf(three.get(i).faces + 1));
            }
            if (i + 1 == three.size()) { // 没有3张了，填入
                if (two.size() > 0) {
                    for (int j = 0; j < 2; j++) {
                        result.add(String.valueOf(two.get(0).faces + 1));
                    }
                    two.remove(0);
                }
            } else {
                if (three.get(i + 1).faces > two.get(0).faces) {
                    // 需要拆分
                    one.add(new Card(three.get(i + 1).faces, 1));
                    for (int j = 0; j < 2; j++) {
                        result.add(String.valueOf(three.get(i + 1).faces + 1));
                    }
                    i++;
                } else {
                    for (int j = 0; j < 2; j++) {
                        result.add(String.valueOf(two.get(0).faces + 1));

                    }
                    two.remove(0);
                }
            }
        }
        for (Card card : two) {
            for (int j = 0; j < 2; j++) {
                result.add(String.valueOf(card.faces + 1));
            }
        }
        one.sort((o1, o2) -> {
            if (o2.count == o1.count) {
                return o2.faces - o1.faces;
            }
            return o2.count - o1.count;
        });
        for (Card card : one) {
            result.add(String.valueOf(card.faces + 1));
        }
        return String.join(" ", result);
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


    static class Card {
        int faces;
        int count;

        public Card(int faces, int count) {
            this.faces = faces;
            this.count = count;
        }
    }


}
