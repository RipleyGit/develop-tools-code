package site.bleem.nowcoder;

import org.junit.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class HuaWeiOD2023Test {

    /*
    给定字符串A=“xxcdefg”、B=“cdefghi”和正整数V=5，A的长度与B的长度相等，请计算A中满足如下条件的最大连续子串的长度：
            1、该连续子串在A和B中的位置和长度均相等。
            2、该连续子串|A[i]-B[i]|之和小于等于V。其中|A[i]-B[i]|表示两个字母ASCII码之差的绝对值。
    输入描述：
    第一行为字符串A，仅包含小写字母，长度是1到1000
    第二行为字符串B，仅包含小写字母，长度是1到1000
    第三行为正整数V，V取值在0到10000之间，包括10000。
    输出描述：
    字符串最大连续子串的长度，要求该子串|A[i]-B[i]|之和小于等于V
    示例1
    输入
        xxcdefg
        cdefghi
        5
    输出
        2
    说明
        字符串A为xxcdefg，字符串B为cdefghi，V=5.它的最大连续子串可以是cd->ef,de->fg,ef->gh,fg->hi，所以最大连续子串是2.
    */
    @Test
    public void stringContinueLen(){
        //Scanner scanner = new Scanner(System.in);
        String A = "xxcdefg";//scanner.nextLine();
        String B = "cdefghi";//scanner.nextLine();
        int V = 5;//scanner.nextInt();
        int len = A.length();
        int ans = 0;
        int[][][] dp = new int[len][len][2];
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                // 判断子串在A和B中的位置和长度是否相等
                if (A.substring(i, j + 1).equals(B.substring(i, j + 1))) {
                    int len1 = j - i + 1;
                    // 判断是否满足条件2
                    if (Math.abs(len1 - len) * 26 - getDiff(A, B, i, j) <= V) {
                        // 如果该子串满足条件2，则进行状态转移
                        dp[i][j][0] = dp[i + 1][j - 1][0] + 2;
                        dp[i][j][1] = dp[i + 1][j - 1][1] + getDiff(A, B, i, j);
                        // 更新答案
                        if (dp[i][j][1] <= V) {
                            ans = Math.max(ans, len1);
                        }
                    }
                }
            }
        }
        System.out.println(ans);
    }

    private static int getDiff(String A, String B, int i, int j) {
        int diff = 0;
        for (int k = i; k <= j; k++) {
            diff += Math.abs(A.charAt(k) - B.charAt(k));
        }
        return diff;
    }

    /**
     题目：
        部门团建，需要租车，每辆车可以坐两个人，最大载重M，给出部门人数和每个人体重，问：最少要租多少辆车
     输入：
        3 4
        3，2，2，1
     输出：
        3
     */
    @Test
    public void rentalCar(){
        int m= 3;
        int n=4;
        int[] weights = new int[]{3,2,2,1};
//        int[] weights = new int[n];
//        for (int i = 0; i < n; i++) {
//            weights[i] = scanner.nextInt();
//        }
        Arrays.sort(weights); // 按体重从小到大排序
        int i = 0, j = n - 1, count = 0; // i和j分别指向体重最轻和最重的人
        while (i <= j) {
            if (i == j) { // 只有一个人
                count++;
                break;
            }
            if (weights[i] + weights[j] <= m) { // 满足两个人体重之和小于等于最大载重
                i++;
                j--;
                count++;
            } else { // 最重的人单独坐一辆车
                j--;
                count++;
            }
        }
        System.out.println(count); // 输出所需最少租车辆数
    }

    /**
     题目：
        给你一个字符串数组（每个字符串均由小写字母组成）和一个字符规律（由小写字母和 . 和 * 组成），识别数组中哪些字符串可以匹配到字符规律上。
     ‘.’匹配任意单个字符，‘*’匹配零个或多个前面的那一个元素，所谓匹配，是要涵盖整个字符串的，而不是部分字符串。
     输入描述
     第一行为空格分隔的多个字符串，单个字符串长度从1到100，字符串个数从1到100
     第二行为字符规律，1<=字符规律长度<=50
     不需要考虑异常场景
     输出描述：
     匹配的字符串在数组中的下标(从0开始)，多个匹配时下标升序并用英文逗号分隔，若均不匹配输出-1.
     示例1
     输入
     ab aab
     .*
     输出
     0,1
     说明
     ab中a匹配. b匹配* 可以完全匹配；aab中a匹配. ab匹配* 可以完全匹配；输出对应字符串数组下标0,1
     示例2
     输入
     ab aab
     a.b
     输出
     1
     说明
     aab中第一个a匹配a，第二个a匹配. b匹配b可以全匹配；输出对应的字符串数组下标1
     */
    @Test
    public void charMatch(){
        String charStr ="ab aab";
        String pattern =".*";
        String[] strs = charStr.split(" ");
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            if (strs[i].matches(pattern)) { // 使用正则表达式匹配字符串
                indexes.add(i);
            }
        }
        if (indexes.isEmpty()) { // 均不匹配
            System.out.println(-1);
        } else { // 输出匹配的字符串在数组中的下标
            List<String> collect = indexes.stream().filter(f -> f != null).map(m -> String.valueOf(m)).collect(Collectors.toList());
            String join = String.join(",", collect);
            System.out.print(join);
        }
    }

    /**
     找到它是个小游戏，你需要在一个矩阵中找到给定的单词，假设给定单词HELLOWORLD，在矩阵中只要能找到H->E->L->L->O->W->O->R->L->D连成的单词，就算通过。
     注意区分英文字母大小写，并且你只能上下左右行走，不能走回头路。
     输入描述：
     输入第一行包含两个整数n、m(0<n,m<21),分别表示n行m列的矩阵，第二行时长度不超过100的单词W（在整个矩阵中给定单词只会出现一次），从第三行到到第n+2行时只包含大小写英文字母的长度为m的字符串矩阵。
     输出描述：
     如果能在矩阵中连成给定的单词，则输出给定单词首字母在矩阵中的位置（第几行 第几列），否则输出“NO”
     示例1
     输入
     5 5
     HELLOWORLD
     CPUCY
     EKLQH
     CHELL
     LROWO
     DGRBC
     输出
     3 2
     示例2
     输入
     Helloworld
     CPUCh
     wolle
     orldO
     EKLQo
     PGRBC
     输出
     NO
     说明
     区分大小写
     */
    @Test
    public void findIt(){
        Scanner scanner = new Scanner(System.in);
        int n = 5;//scanner.nextInt();
        int m = 5;//scanner.nextInt();
        String word = "HELLOWORLD";//scanner.next();
        char[][] matrix =  new char[n][m];
//        new char[5][5]{
//                {"C","P","U","C","Y"},
//                {"E","K","L","Q","H"},
//                {"C","H","E","L","L"},
//                {"L","R","O","W","O"},
//                {"D","G","R","B","C"}
//        };
        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }
        boolean found = false;
        int row = 0, col = 0;
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < m && !found; j++) {
                if (matrix[i][j] == word.charAt(0)) {
                    if (findWord(matrix, i, j, word)) {
                        found = true;
                        row = i + 1;
                        col = j + 1;
                    }
                }
            }
        }
        if (found) {
            System.out.println(row + " " + col);
        } else {
            System.out.println("NO");
        }
    }

    /**
     *
     * @param matrix
     * @param row
     * @param col
     * @param word
     * @return
     */
    private static boolean findWord(char[][] matrix, int row, int col, String word) {
        // 已经找到所有字母，返回true
        if (word.length() == 0) {
            return true;
        }
        // 当前位置超出矩阵范围或者不是要找的字母，返回false
        if (row < 0 || row >= matrix.length || col < 0 || col >= matrix[0].length || matrix[row][col] != word.charAt(0)) {
            return false;
        }
        // 当前位置标记为已经访问过
        char tmp = matrix[row][col];
        matrix[row][col] = '*';
        // 在上下左右四个方向查找下一个字母
        boolean result = findWord(matrix, row - 1, col, word.substring(1)) || findWord(matrix, row + 1, col, word.substring(1))
                || findWord(matrix, row, col - 1, word.substring(1)) || findWord(matrix, row, col + 1, word.substring(1));
        // 恢复当前位置的状态
        matrix[row][col] = tmp;
        return result;
    }

}
