package site.bleem.algo.od;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 知识图谱找新词
 */
public class GraphNewWordBuilder {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String stra = sc.nextLine();
        String strb = sc.nextLine();
        String executor = executor(stra, strb);
        System.out.println(executor);
    }

    /**
     * 解题思路：
     * 1.对world词进行排序；
     * 2.以world词长度为窗口对内容进行滑动遍历截取，对截取内容进行从排序对比
     * todo：若重复新词属于新词，则添加map对比
     *
     * @param stra
     * @param strb
     * @return
     */
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

    /**
     * 比较是否是新词（由于两个都是从小到大排序过，每个位置上的字符应该是相等的）
     * todo 重复新词是否属于新词？
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean executor(char[] a, char[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

}
