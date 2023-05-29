package site.bleem.niucoder;

import java.util.Scanner;

/**
 * 题目：版本号比较
 * 版本号规则：主版本号.次版本号.增量版本号-发布版本号， 主版本号、次版本号是必须的，主版本号、次版本号、增量版本号是数字，
 * 可能存在前导0，发布版本号为字符，可以按字典顺序进行比较，如果两个版本号相等，返回第一个版本号
 * 输入：每个版本号按行输入，输入两个版本号
 * 输出：版本号高的
 * 示例1
 * 输入：
 * 1.05.1
 * 1.5.01
 * 输出：
 * 1.05.1
 * 说明：两个版本号一样，输出第一个
 * 示例2
 * 输入：
 * 1.5.2-A
 * 1.5.2-a
 * 输出：
 * 1.5.2-a
 * 说明：因为 a 为97 大于 A 65
 * 示例3
 * 输入：
 * 1.5
 * 1.5.0
 * 输出：
 * 1.5.0
 * 说明：有增量版本号，输出1.5.0
 */
public class VersionCompare {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String first = scanner.nextLine();
            String last = scanner.nextLine();
            String result = compareVersion(first, last);
            System.out.println(result);
        }
        scanner.close();
    }

    /**
     * 解题思路：
     * 1.先比较主版本号，再比较次版本号；
     * 2.判断是否存在数字类型增量版本号；
     * 3.判断版本号中是否存在- ；
     * 4.比较增量版本号字符型
     *
     * @param first
     * @param last
     * @return
     */
    public static String compareVersion(String first, String last) {
        //按字典顺序进行比较，如果两个版本号相等，返回第一个版本号
        String[] firstSplit = first.split("-");
        String[] lastSplit = last.split("-");
        String[] firstVersions = firstSplit[0].split("\\.");
        String[] lastVersions = lastSplit[0].split("\\.");
        //比较主版本号
        String resultVersion;
        if (Integer.valueOf(firstVersions[0]) > Integer.valueOf(lastVersions[0])) {
            resultVersion = first;
        } else if (Integer.valueOf(firstVersions[1]) < Integer.valueOf(lastVersions[1])) {
            resultVersion = last;
        } else if (Integer.valueOf(firstVersions[1]) > Integer.valueOf(lastVersions[1])) {//比较副版本号
            resultVersion = first;
        } else if (Integer.valueOf(firstVersions[1]) < Integer.valueOf(lastVersions[1])) {//比较副版本号
            resultVersion = last;
        }
        //比较是否有增量版本
        if (firstVersions.length > lastVersions.length) {
            resultVersion = first;//第一个版本号比第二长
        } else if (firstVersions.length < lastVersions.length) {
            resultVersion = last;//第二个版本比第一个长
        } else {
            //增量版本都存在
            if (Integer.valueOf(firstVersions[2]) >= Integer.valueOf(lastVersions[2])) {
                resultVersion = first;//增量版相同以第一个为准
            } else {
                resultVersion = last;
            }
        }
        //比较版本中是否存在-
        if (firstSplit.length > lastSplit.length) {
            resultVersion = first;//第一个版本号比第二长
        } else if (firstSplit.length < lastSplit.length) {
            resultVersion = last;
        } else if (firstSplit.length == 2) {
            //都存在 -
            if (firstSplit[1].compareTo(lastSplit[1]) > 0) {
                resultVersion = first;
            } else {
                resultVersion = last;
            }
        }

        return resultVersion;
    }
}
