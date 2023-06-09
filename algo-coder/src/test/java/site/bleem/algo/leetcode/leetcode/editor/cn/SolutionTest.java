package site.bleem.algo.leetcode.leetcode.editor.cn;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SolutionTest {
//    public int lengthOfLIS(int[] nums) {
//        int max = 1;
//        for (int i = 0; i < nums.length; i++) {
//            int length = lengthOfLIS(nums, i);
//            if (length > max) {
//                max = length;
//            }
//        }
//        return max;
//    }

    public int lengthOfLIS(int[] nums, int index) {
        int num = nums[index];
        int count = 1;
        for (int i = index; i < nums.length; i++) {
            if (num < nums[i]) {
                count++;
                num = nums[i];
            }
        }
        return count;
    }

    public int lengthOfLIS(int[] nums) {
        int ret = 1; //数组记录每个位置前面最小递增子序列元素个数
        int[] dps = new int[nums.length];
        Arrays.fill(dps, 1);
        //动态优化
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dps[i] = Math.max(dps[i], dps[j] + 1);
                }
            }
            ret = Math.max(ret,dps[i]);
        }
        return ret;
    }

    @Test
    public void test() {
        int length = lengthOfLIS(new int[]{0, 1, 0, 3, 2, 3});
        System.out.printf("" + length);
    }


}