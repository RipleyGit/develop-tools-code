package site.bleem.nowcoder;

import java.util.Arrays;
import java.util.Scanner;

/**
   题目：租车骑绿岛
        部门团建，需要租车，每辆车可以坐两个人，最大载重M，给出部门人数和每个人体重，问：最少要租多少辆车
    输入：
        3 4
        3，2，2，1
    输出：
        3
 */
public class CarRental {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            int m = scanner.nextInt();
            int n = scanner.nextInt();
            int[] ints = new int[n];
            for (int i = 0; i < n; i++) {
                ints[1] = scanner.nextInt();
            }
            String result = carRental(m,n,ints);
            System.out.println(result);
        }
        scanner.close();
    }

    /**
     * 计算需要租多少辆车
     *
     * @param m      车载重
     * @param n      人数
     * @param weight 每人重量
     * @return
     */
    public static String carRental(int m, int n, int[] weight) {
        Arrays.sort(weight);//从小到大排序
        int count=0;
        int index = 0;
        int last = n-1;
        while (index <= last){
            if ((weight[index] + weight[last]) <=m){
                count ++;
                index ++;
                last --;
            }else{
                //重的独自坐一辆
                last -- ;
                count ++;
            }
        }
        return String.valueOf(count);
    }
}
