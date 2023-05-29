package site.bleem.algo.od;

import java.util.Scanner;

/**
 * 分奖金
 */
public class BonusAllocation {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (sc.hasNextInt() || n == 0) {
            n = sc.nextInt();
            break;
        }
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = sc.nextInt();
        }
        String executor = executor(n, ints);
        System.out.println(executor);
    }


    /**
     * 解题思路：使用双层for循环，找出后面第一个比自己大的，存在就根据公式（max-me）*(max_index-me_index)计算,没有就是自己
     *
     * @param n
     * @param ints
     * @return
     */
    public static String executor(int n, int[] ints) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (ints[i] < ints[j]) {
                    ints[i] = (ints[j] - ints[i]) * (j - i);
                    break;
                }
            }
        }

        String res = "";
        for (int i : ints) {
            res += i + " ";
        }
        String substring = res.substring(0, res.length() - 1);
        return substring;
    }


}
