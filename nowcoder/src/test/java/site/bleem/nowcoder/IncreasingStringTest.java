package site.bleem.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class IncreasingStringTest {

    /**
     示例1
         输入：
             AABBA
         输出：
             1
     示例2
         输入：
             AABAABBBA
         输出：
             2
     */
    @Test
    public void test(){
        Assert.assertEquals(1,IncreasingString.increasingString("AABBA"));
        Assert.assertEquals(2,IncreasingString.increasingString("AABAABBBA"));
    }

}