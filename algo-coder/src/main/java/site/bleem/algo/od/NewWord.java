package site.bleem.algo.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 知识图谱找新词
 */
public class NewWord {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stra = sc.nextLine();
        String strb = sc.nextLine();
        String executor = executor(stra, strb);
        System.out.println(executor);
    }

    public static String executor(String stra, String strb) {
        int count = 0;
        char[] strbc = strb.toCharArray();
        Arrays.sort(strbc);
        int left = 0;
        int right = strb.length() - 1;
        while (right < stra.length()) {
            String str = stra.substring(left, right + 1);
            char[] strba = str.toCharArray();
            Arrays.sort(strba);
            if (executor(strba, strbc)) {
                count++;
            }
            left++;
            right++;
        }
        return String.valueOf(count);
    }

    public static boolean executor(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

}
