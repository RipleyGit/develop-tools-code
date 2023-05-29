package site.bleem.huawei.od;

import org.junit.Assert;
import org.junit.Test;
import site.bleem.huawei.od.BonusPoints;

public class BonusPointsTest {
    @Test
    public void test(){
        Assert.assertEquals("8 10 3", BonusPoints.executor(3,new int[]{2,10,3}));
//        Assert.assertEquals(new int[]{4,4,4,3,3,2,2,1,1,3},NewWord.executor(new int[]{4,4,2,1,2,1,3,3,3,4}));
    }
}