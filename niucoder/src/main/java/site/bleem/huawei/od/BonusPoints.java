package site.bleem.huawei.od;

import java.util.Scanner;

public class BonusPoints {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = 0;
        while (sc.hasNextInt() || n == 0 ){
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
