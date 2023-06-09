package site.bleem.algo.od;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Stack;

/**
 * 华为OD技术2面：手撕代码
 */
public class TheTechnicalInterviewTwo {

    @Test
    public void expressionCheckTest(){
        System.out.println("(1+2)(23) :"+expressionCheck("(1+2)(23)"));
        System.out.println("((1+2)(23) :"+expressionCheck("((1+2)(23)"));
        System.out.println("((1+2)23 :"+expressionCheck("((1+2)23"));
        System.out.println("23234234 :"+expressionCheck("23234234"));
        System.out.println("2343)feqf( :"+expressionCheck("2343)feqf("));
        System.out.println("2343)) :"+expressionCheck("2343))"));
    }

    /**
     * 校验算术表达式中的括号是否均成对匹配。（只需考虑相互之间的括号是否完全匹配，不需考虑表达式的其他合法问题）
     * 如(1+2)(23)是括号完全匹配的，((1+2)(23)和((1+2)23则没有完全匹配
     * 注：算术表达式不一定包含括号 )(
     */
    public Boolean expressionCheck(String str) {
        //算术表达式中不包括括号
        if (!str.contains("(") || !str.contains(")")) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if ("(".equals(String.valueOf(charAt))) {
                stack.push(charAt);
            } else if (")".equals(String.valueOf(charAt)) && stack.isEmpty()) {
                return false;
            } else if (")".equals(String.valueOf(charAt)) && !stack.isEmpty()) {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    //给一整数，计算n阶乘，4 4*3*2*1 给出结果长度；
    @Test
    public void factorialTest(){
        System.out.println("10 :"+factorial(BigDecimal.valueOf(10)).toString().length());
        System.out.println("5000L :"+factorial(BigDecimal.valueOf(5000L)).toString().length());
    }

    /**
     * 给一整数，计算n阶乘，4 4*3*2*1 给出结果长度；长度可能会很长
     * @param intVal
     * @return
     */
    public Long factorial(Long intVal){
        if (intVal == 0L){
            return 1L;
        }
//        Long l = factorial(intVal) * factorial(Long.valueOf(intVal - 1L));

        return intVal * factorial(Long.valueOf(intVal - 1L));
    }
    public BigDecimal factorial(BigDecimal bigDecimal){
        if (bigDecimal.intValue() == 0L){
            return BigDecimal.ONE;
        }
        return bigDecimal.multiply(factorial(bigDecimal.subtract(BigDecimal.ONE)));
//        Long l = factorial(intVal) * factorial(Long.valueOf(intVal - 1L));

//        return intVal * factorial(Long.valueOf(intVal - 1L));
    }

    //排序好的数组，使用二分查找，找出第一个目标值 第一次出现的下标（ke）
}