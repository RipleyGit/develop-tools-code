package site.bleem.nowcoder;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class VersionCompareTest {

    /**
     示例1
     输入：
     1.05.1
     1.5.01
     输出：
     1.05.1
     说明：两个版本号一样，输出第一个
     示例2
     输入：
     1.5.2-A
     1.5.2-a
     输出：
     1.5.2-a
     说明：因为 a 为97 大于 A 65
     示例3
     输入：
     1.5
     1.5.0
     输出：
     1.5.0
     说明：有增量版本号，输出1.5.0
     */
    @Test
    public void test(){
        Assert.assertEquals("1.05.1",VersionCompare.compareVersion("1.05.1","1.5.01"));
        Assert.assertEquals("1.5.2-a",VersionCompare.compareVersion("1.5.2-A","1.5.2-a"));
        Assert.assertEquals("1.5.0",VersionCompare.compareVersion("1.5","1.5.0"));

    }

}