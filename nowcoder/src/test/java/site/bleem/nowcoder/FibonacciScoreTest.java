package site.bleem.nowcoder;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciScoreTest {

    /**
     * 输入：
     * 8/11
     * 2/4
     * 输出：
     * 1/2+1/5+1/55+1/110
     * 1/3+1/6
     */
    @Test
    public void test(){
        Assert.assertEquals("1/2+1/5+1/55+1/110",FibonacciScore.fibonacciScore("8/11"));
        Assert.assertEquals("1/3+1/6",FibonacciScore.fibonacciScore("2/4"));
    }

}