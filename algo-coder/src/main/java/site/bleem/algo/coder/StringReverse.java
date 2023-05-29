package site.bleem.algo.coder;

import java.util.Scanner;
import java.util.Stack;

/**
 * 题意：反转单词，每行输入一个字符串，字符串含有单词和字符  .,? ，要求对单词进行反转， 单词间空格>0， 字符 **.,?**  间空格 >= 0, 反转结果输出一行。(靠记忆整理)
 * 示例1：
 * 输入：
 * My name is Bob.
 * 输出：
 * yM eman si boB.
 * 示例2：
 * 输入：
 * How are you ? i am fine
 * 输出：
 * woH era uoy ? i ma enif
 */
public class StringReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String join = functionStringReverse(nextLine);
            System.out.println(join);
        }
        scanner.close();
    }

    public static String functionStringReverse(String nextLine) {
        Stack<Character> stack = new Stack<>();//栈
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < nextLine.length() ; i++) {
            char c = nextLine.charAt(i);
            if (c == ' ' || c =='.' || c == '?'||c == ','){
                while (!stack.isEmpty()){
                    buffer.append(stack.pop());
                }
                buffer.append(c);
            }else {
                stack.push(c);
            }
        }
        while (!stack.isEmpty()){
            buffer.append(stack.pop());
        }
        return buffer.toString();
    }

}
