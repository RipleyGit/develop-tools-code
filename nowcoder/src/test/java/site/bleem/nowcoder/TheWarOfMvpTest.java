package site.bleem.nowcoder;

import org.junit.Assert;
import org.junit.Test;

public class TheWarOfMvpTest {

    /**
     * 输入：
     * 9
     * 5,2,1,5,2,1,5,2,1
     * 输出：
     * 6
     */
    @Test
    public void test(){
        Assert.assertEquals("6",TheWarOfMvp.executor(9, new int[]{5,2,1,5,2,1,5,2,1}));
        Assert.assertEquals("14",TheWarOfMvp.executor(9, new int[]{9,2,1,5,2,1,5,2,1}));
    }
}