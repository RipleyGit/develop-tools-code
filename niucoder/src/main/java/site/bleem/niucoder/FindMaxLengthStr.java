package site.bleem.niucoder;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Queue;

/**
 * 题目：寻找符合要求的最长子串
 * 题目描述
 * 给定一个字符串 s ，找出这样一个子串1) 该子串中的任意一个字符最多出现2次2) 该子串不包含指定某个字符请你找出满足该条件的最长子串的长度
 * 输入
 * 第一行为不包含的指定字符，为单个字符[0-9a-ZA-Z第二行为字符串s
 * 输出
 * 一个整数，满足条件的最长子串长度如不存在满足条件的子串，则返回0。
 * 示例1
 * 输入
 * D
 * ABC123
 * 输出：6
 * 示例2
 * 输入
 * D
 * ABACA123D
 * 输出：7
 */
public class FindMaxLengthStr {

    /**
     * 解题思路
     * 1.考察 滑动窗口Q，有没有感觉和新员工座位安排那题非常像
     * 2.新员工座位安排那题有2座位柱子，这个题中有指定的字符来隔开
     * 3.求符合要求的最长子串，老套路移动窗口右边界，在符合要求的情况下每次移动都求一次最长子串长度。
     * 4.当有字符出现次数为3时，移动左边界，并且相应字符的统计个数减1.直到左指针跨过次数为3的字符时停止，右指针继续向后移动5.当遇到指定字符时，清空map，左指针移动到指定字符右边
     * 考点:
     * 滑动窗口
     */
    public static String executor(String flag,String str) {
        Queue<String> queue = new ArrayDeque<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            String valuedOf = String.valueOf(charAt);
            if (flag.equals(valuedOf)){
                return String.valueOf(queue.size());
            }else {
                queue.add(valuedOf);
                if (map.containsKey(valuedOf)){
                    Integer integer = map.get(valuedOf);
                    if (integer == 2){
                        while (!valuedOf.equals(queue.poll())){}
                    }else {
                        integer +=1;
                        map.put(valuedOf,integer);
                    }
                }else {
                    map.put(valuedOf,1);
                }
            }

        }
        return String.valueOf(queue.size());
    }
}
