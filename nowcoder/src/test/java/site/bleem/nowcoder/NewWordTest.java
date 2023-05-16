package site.bleem.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class NewWordTest {
    @Test
    public void test(){
        Assert.assertEquals("2",NewWord.executor("qweebaewqd","qwe"));
//        Assert.assertEquals(new int[]{4,4,4,3,3,2,2,1,1,3},NewWord.executor(new int[]{4,4,2,1,2,1,3,3,3,4}));
    }
}