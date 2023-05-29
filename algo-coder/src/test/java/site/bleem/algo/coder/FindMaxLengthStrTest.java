package site.bleem.algo.coder;

import org.junit.Assert;
import org.junit.Test;

public class FindMaxLengthStrTest {
    /**
     *  示例1
     *     输入
     *         ABC123
     *     输出：6
     *  示例2
     *     输入
     *         ABACA123D
     *     输出：7
     */
    @Test
    public void test(){
        Assert.assertEquals("6",FindMaxLengthStr.executor("D","ABC123"));
        Assert.assertEquals("7",FindMaxLengthStr.executor("D","ABACA123D"));
    }

}