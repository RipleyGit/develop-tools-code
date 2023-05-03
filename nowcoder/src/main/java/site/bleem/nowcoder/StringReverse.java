package site.bleem.nowcoder;

import java.util.ArrayList;
import java.util.Scanner;

/**
 题意：反转单词，每行输入一个字符串，字符串含有单词和字符  **.,?**  ，要求对单词进行反转， 单词间空格>0， 字符 **.,?**  间空格 >= 0, 反转结果输出一行。(靠记忆整理)
    示例1：
    输入：
            My name is Bob.
    输出：
            yM eman si boB.
    示例2：
    输入：
            How are you ? i am fine
    输出：
            woH era uoy ? i ma enif
 */
public class StringReverse {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String nextLine = scanner.nextLine();
            String[] split = nextLine.split(" ");
            ArrayList<String> strings = new ArrayList<>();
            for (int i = 0; i < split.length; i++) {
                String s = split[i];
                char[] chars = new char[s.length()];
                for (int j = s.length(); j >0 ; j--) {
                    chars[s.length() -j]=s.charAt(j-1);
                }
                strings.add(String.valueOf(chars));
            }
            System.out.println(String.join(" ",strings));
        }
    }
    
}
