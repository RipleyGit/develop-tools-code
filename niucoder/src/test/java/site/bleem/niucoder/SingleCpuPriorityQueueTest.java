package site.bleem.niucoder;


import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleCpuPriorityQueueTest {
    /**
     示例1
     输入:
     1 3 5 1
     2 1 5 10
     3 2 7 12
     4 3 2 20
     5 4 9 21
     6 4 2 22
     输出:
     1 6
     3 19
     5 30
     6 32
     4 33
     2 35
     */
    @Test
    public void test(){
        int[] i1 = new int[]{1,3,5,1};
        int[] i2 = new int[]{2,1,5,10};
        int[] i3 = new int[]{3,2,7,12};
        int[] i4 = new int[]{4,3,2,20};
        int[] i5 = new int[]{5,4,9,21};
        int[] i6 = new int[]{6,4,2,22};
        int[][] inputs = new int[6][4];
        inputs[0] = i1;
        inputs[1] = i2;
        inputs[2] = i3;
        inputs[3] = i4;
        inputs[4] = i5;
        inputs[5] = i6;
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1,6));
        list.add(Arrays.asList(3,19));
        list.add(Arrays.asList(5,30));
        list.add(Arrays.asList(6,32));
        list.add(Arrays.asList(4,33));
        list.add(Arrays.asList(2,35));
        Assert.assertEquals(list,SingleCpuPriorityQueue.executor(inputs));
    }
}