package site.bleem.algo.od;

import org.junit.Test;

import java.util.Stack;

public class ODTest {

    /**
     * 校验算术表达式中的括号是否均成对匹配。（只需考虑相互之间的括号是否完全匹配，不需考虑表达式的其他合法问题）
     * 如(1+2)(23)是括号完全匹配的，((1+2)(23)和((1+2)23则没有完全匹配
     * 注：算术表达式不一定包含括号 )(
     */
    @Test
    public void test() {
        Boolean match = match("()(");
        System.out.printf(""+match);
    }

    public Boolean match(String str){
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ("(".equals(String.valueOf(charAt))){
                stack.push(charAt);
            }else if (")".equals(String.valueOf(charAt)) && stack.isEmpty()){
                return false;
            }else if (")".equals(String.valueOf(charAt)) && !stack.isEmpty()){
                stack.pop();
            }
        }
        return stack.isEmpty();
//        if (stack.isEmpty()){
//            return false;
//        }
//        return true;

//        int x= 0;
//        int lastJ = 0;
//        for (int i = 0; i < str.length(); i++) {
//            char charAt = str.charAt(i);
//            if ('('==charAt){
//                x++;
//                for (int j = str.length()-1; j >0; j--) {
//                    if (')'==str.charAt(j) && j<lastJ){
//                        lastJ = j;
//                        x--;
//                    }
//                }
//            }
//        }
//        return x == 0;
    }

}
