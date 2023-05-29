package site.bleem.algo.coder;

import java.util.Arrays;
import java.util.Scanner;

/**
   题目：递增字符串
   字符串中只存在A或者B两种字符，求最少修改次数即可实现非递减字符串，可同时修改A或者B，非递减字符串，
   及后面的字符不会小于前面的字符（ASCII码值，这里只有A、B两种字符，只有B后面不出现A即为非递减字符串）
   示例1
   输入：
   AABBA
   输出：
   1
   说明：因为修改最后一个A为B即可，AABBB 为非递减字符串， 修改BB为AA也可以，但是修改次数为2，非最少次数。
   示例2
   输入：
   AABAABBBA
   输出：
   2
   说明：因为修改最后第三个B为A即可，最后一个A为B 形成AAAAABBBB为递减字符串,修改次数为2，为最少次数。
 */
public class IncreasingString {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            int result = increasingString(nextLine);
            System.out.println(result);
        }
        scanner.close();
    }

    /**
       AABAABBBA 不可用
       使用动态规划算法，从第二位开始分割，计算，左边为A，右边为B 计算每次分割所需修改次数，取最小值
       @param nextLine
       @return
     */
    public static int increasingString(String nextLine) {
        int[] ints = new int[nextLine.length() - 1];
        //从1开始分析 左边全为A，右边全为B
        for (int mod = 1; mod < nextLine.length(); mod++) {
            String half1 = nextLine.substring(0, mod + 1);//前半段
            String half2 = nextLine.substring(mod + 1);//后半段
            int b2a = 0;
            for (int i = 0; i < half1.length(); i++) {
                char c = half1.charAt(i);
                if (c == 'B') {
                    b2a++;
                }
            }
            int a2b = 0;
            for (int i = 0; i < half2.length(); i++) {
                char c = half2.charAt(i);
                if (c == 'A') {
                    a2b++;
                }
            }
            ints[mod - 1] = b2a + a2b;
        }
        Arrays.sort(ints);
        return ints[0];
    }
}
