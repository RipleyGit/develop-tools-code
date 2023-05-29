package site.bleem.algo.coder;

import org.junit.Assert;
import org.junit.Test;

public class CarRentalTest {

    /**
     示例1:
     输入：
     3 4
     3，2，2，1
     输出：
     3
     示例2:
     输入：
     4 6
     4 3 3 3 1 1
     输出：4
     */
    @Test
    public void test(){
        Assert.assertEquals("3",CarRental.carRental(3, 4,new int[]{3,2,2,1}));
        Assert.assertEquals("4",CarRental.carRental(4, 6,new int[]{4,3,3,3,1,1}));
    }

}