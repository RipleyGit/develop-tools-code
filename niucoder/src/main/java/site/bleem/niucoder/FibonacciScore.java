package site.bleem.niucoder;

import java.util.Scanner;

/**
 * 分子为1的分数称为埃及分数。现输入一个真分数(分子比分母小的分数，叫做真分数)，请将该分数分解为埃及分数。如：8/11 = 1/2+1/5+1/55+1/110。
 * 注：真分数指分子小于分母的分数，分子和分母有可能gcd不为1！
 * 如有多个解，请输出任意一个。
 * 输入：
 * 8/11
 * 2/4
 * 输出：
 * 1/2+1/5+1/55+1/110
 * 1/3+1/6
 */
public class FibonacciScore {
    /**
     * 数学家斐波那契提出的一种求解***分数的贪心算法，准确的算法表述应该是这样的：
     * 设某个真分数的分子为a，分母为b;
     * 把c=(b/a+1)作为分解式中第一个分数的分母；
     * 将a-b%a作为新的a；
     * 将b*c作为新的b；
     * 如果a等于1，则最后一个***分数为1/b,算法结束；
     * 如果a大于1但是a能整除b，则最后一个***分数为1/(b/a),算法结束；
     * 否则重复上面的步骤。
     **/
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String fibonacciScore = fibonacciScore(nextLine);
            System.out.println(fibonacciScore);
        }
        scanner.close();
    }

    public static String fibonacciScore(String score) {
        StringBuffer buffer = new StringBuffer();
        String[] split = score.split("/");
        Integer a = Integer.valueOf(split[0]);
        Integer b = Integer.valueOf(split[1]);
        if (a > b || a < 1 || b < 2) {
            return buffer.toString();
        }
        while (a != 1) {
            int c = b / a + 1;
            a = a - b % a;
            b = b * c;
            buffer.append("1/" + c + "+");
            if (b % a == 0) {
                b = b / a;
                a = 1;
            } else if (b % (a - 1) == 0) { //8/11 = 1/2+1/5+1/55+1/110
                buffer.append("1/" + b / (a - 1) + "+");
                break;
            }
        }
        buffer.append("1/" + b);
        return buffer.toString();
    }
}
