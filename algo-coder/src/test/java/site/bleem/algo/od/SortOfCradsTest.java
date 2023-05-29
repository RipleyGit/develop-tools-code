package site.bleem.algo.od;

import org.junit.Assert;
import org.junit.Test;
import site.bleem.algo.od.SortOfCrads;

import java.util.Arrays;


public class SortOfCradsTest {
    @Test
    public void test(){
        //        Assert.assertEquals(new int[]{4,4,4,3,3,2,2,1,1,3},SortOfCrads.executor(new int[]{1,3,3,3,2,1,5}));
//        Assert.assertEquals(new int[]{4,4,4,3,3,2,2,1,1,3},SortOfCrads.executor(new int[]{4,4,2,1,2,1,3,3,3,4}));
        Assert.assertEquals("3 3 3 1 1 5 2", SortOfCrads.executor(Arrays.asList(1,3,3,3,2,1,5)));
        Assert.assertEquals("4 4 4 3 3 2 2 1 1 3",SortOfCrads.executor(Arrays.asList(4,4,2,1,2,1,3,3,3,4)));
    }

}