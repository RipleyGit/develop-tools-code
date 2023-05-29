package site.bleem.niucoder;

import org.junit.Assert;
import org.junit.Test;

/**
 * 字符反转测试用例
 */
public class StringReverseTest {
    @Test
    public void functionStringReverseTest(){
        Assert.assertEquals("[yM eman si boB.]结果比较","yM eman si boB.",StringReverse.functionStringReverse("My name is Bob."));
        Assert.assertEquals("[How are you ? i am fine]结果比较","woH era uoy ? i ma enif",StringReverse.functionStringReverse("How are you ? i am fine"));
    }

}