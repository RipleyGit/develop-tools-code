package site.bleem.niucoder;

import java.util.Scanner;

/**
 * 题目：MVP争夺战
 * 球争霸篮球赛对抗赛中，强⼤的宇宙战队，希望每个⼈都能拿到 MVP 。
 * P的条件是，单场最⾼分得分获得者，可以并列，所以宇宙战队决定在⽐赛中，尽可能让更多的队员上
 * 且让所有有得分的队员得分都相同。
 * ⽐赛过程中的每⼀分钟的得分都只能由某⼀个⼈包揽。
 * 描述：
 * 第⼀⾏为⼀个数字t，表示有得分的分钟数（ 1 <= t <= 50），第⼆⾏为t个数字，代表每⼀分钟的得分
 * 1<= p <= 50）
 * 描述：
 * 有得分的队员都是MVP时最少的MVP得分。
 * 输入：
 * 9
 * 5,2,1,5,2,1,5,2,1
 * 输出：
 * 6
 */
public class TheWarOfMvp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            int[] ints = new int[n];
            for (int i = 0; i < n; i++) {
                ints[1] = scanner.nextInt();
            }
            String result = executor(n, ints);
            System.out.println(result);
        }
        scanner.close();
    }

    /**
     * mvp平均分
     *
     * @return
     */
    public static String executor(int times, int[] scores) {
        int sum = 0;
        int max = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
            if (scores[i] > max) {
                max = scores[i];
            }
        }
        while (sum % max != 0) {
            max++;
        }
        return String.valueOf(max);
    }
}
