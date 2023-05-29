package site.bleem.algo.coder;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class AiCpuProcessorTest {

    /**
     示例1
     输入：
     [0, 1, 4, 5, 6, 7]
     1
     输出：
     [[0], [1]]
     说明：根据第一条亲和性调度原则，在剩余两个处理器的链路（0,1,2,3）中选择处理器。由于只有0和1可用，则返回任意一颗处理器即可
     示例2
     输入：
     [0, 1, 4, 5, 6, 7]
     4
     输出：
     [[4, 5, 6, 7]]
     说明：根据第三条亲和性调度原则，必须选择同一链路剩余可用的处理器数量为4个的环。
     */
    @Test
    public void test(){
        Assert.assertEquals(Arrays.asList(Arrays.asList(0),Arrays.asList(1)),AiCpuProcessor.findBestCput(new int[]{0, 1, 4, 5, 6, 7}, 1));
        Assert.assertEquals(Arrays.asList(Arrays.asList(4,5,6,7)),AiCpuProcessor.findBestCput(new int[]{0, 1, 4, 5, 6, 7}, 4));
//        Assert.assertEquals("1/3+1/6",FibonacciScore.fibonacciScore("2/4"));
    }
}