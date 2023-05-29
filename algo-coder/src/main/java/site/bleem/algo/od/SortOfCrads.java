package site.bleem.algo.od;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 整理扑克牌
 */
public class SortOfCrads {


    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        List<Integer> list = new ArrayList<>();
//        while (scanner.hasNext()) {
//            if (scanner.hasNextInt()) {
//                list.add(scanner.nextInt());
//            } else if ("".equals(scanner.nextLine())) {
//                break;
//            }
//        }
//        String result = executor(list);
//        System.out.println(result);

        Scanner sc = new Scanner(System.in);
        String stra = sc.nextLine();
        String[] split = stra.split(" ");
        List<Integer> splitList = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            Integer integer = Integer.valueOf(split[i]);
            splitList.add(integer);
        }
        String result = executor(splitList);
        System.out.println(result);
    }

    /**
     * 解决思路：
     * 1.先对扑克牌进行初始化，记录每个牌面有多少张牌；
     * 2.进行从大到小排序，比较牌对数量，数量相同比较牌面大小；
     * 3.王炸-》葫芦-》三张-》对子-》单张，优先堆葫芦（葫芦中的牌面尽可能大），再排对子，最后单张倒排；
     * todo：堆出更多的葫芦是否比堆出更大的葫芦，更好
     *
     * @param list
     * @return
     */
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
            } else if (card.count == 3) {
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
            if (i + 1 == three.size()) { // 没有下一个3张了，填入后面的对子
                if (two.size() > 0) {
                    for (int j = 0; j < 2; j++) {
                        result.add(String.valueOf(two.get(0).faces + 1));
                    }
                    two.remove(0);
                }
            } else {
                //判断下一个三张的牌面是否大于对子的牌面
                if (three.get(i + 1).faces > two.get(0).faces) {
                    // 需要拆分，三张拆成 对子（堆入葫芦）+单张
                    one.add(new Card(three.get(i + 1).faces, 1));
                    for (int j = 0; j < 2; j++) {
                        result.add(String.valueOf(three.get(i + 1).faces + 1));
                    }
                    i++;
                } else {
                    //拼入对子形成葫芦
                    for (int j = 0; j < 2; j++) {
                        result.add(String.valueOf(two.get(0).faces + 1));

                    }
                    two.remove(0);
                }
            }
        }
        //排对子
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


    static class Card {
        int faces;
        int count;

        public Card(int faces, int count) {
            this.faces = faces;
            this.count = count;
        }
    }


}
