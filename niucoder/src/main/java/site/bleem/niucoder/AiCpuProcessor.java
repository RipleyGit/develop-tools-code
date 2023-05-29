package site.bleem.niucoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 题目：AI处理器组合
    某公司研发了一款高性能AI处理器。每台物理设备具备8颗AI处理器，编号分别为0、1、2、3、4、5、6、7。
 编号0-3的处理器处于同一个链路中，编号4-7的处理器处于另外一个链路中，不通链路中的处理器不能通信，如下图所示。
 现给定服务器可用的处理器编号数组array，以及任务申请的处理器数量num，找出符合下列亲和性调度原则的芯片组合。如果不存在符合要求的组合，则返回空列表。
 亲和性调度原则：
 - 如果申请处理器个数为1，则选择同一链路，剩余可用的处理器数量为1个的最佳，其次是剩余3个的为次佳，然后是剩余2个，最后是剩余4个。
 - 如果申请处理器个数为2，则选择同一链路剩余可用的处理器数量2个的为最佳，其次是剩余4个，最后是剩余3个。
 - 如果申请处理器个数为4，则必须选择同一链路剩余可用的处理器数量为4个。
 - 如果申请处理器个数为8，则申请节点所有8个处理器。
 提示：
 1. 任务申请的处理器数量只能是1、2、4、8
 2. 编号0-3的处理器处于一个链路，编号4-7的处理器处于另外一个链路。
 3. 处理器编号唯一，且不存在相同编号处理器
 输入描述：
 输入包含可用的处理器编号数组array，以及任务申请的处理器数量num两个部分。
 第一行为array，第二行为num。例如：[0, 1, 4, 5, 6, 7] 1
 表示当前编号为0、1、4、5、6、7的处理器可用。任务申请1个处理器。0<= array.length <= 8 0<= array[i] <= 7
 num in [1, 2, 4, 8]
 输出描述：
 输出为组合列表，当array=[0, 1, 4, 5, 6, 7] ，num=1时，输出为[[0], [1]]
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
public class AiCpuProcessor {

    public static void main(String[] args) {
        int[] array = new int[]{0, 1, 4, 5, 6, 7};
        int num = 1;
//        AiCpuProcessor aiProcessor = new AiCpuProcessor();
        System.out.println(AiCpuProcessor.findBestCput(array, num));
    }

    public static List<List<Integer>> findBestCput(int[] array, int num){
        List<Integer> lowCpus = new ArrayList<>();//低4位
        List<Integer> highCpus = new ArrayList<>();//高4位
        for (int i = 0; i < array.length; i++) {
            if (array[i] <4){
                lowCpus.add(array[i]);
            }else {
                highCpus.add(array[i]);
            }
        }
        List<List<Integer>> availableCpus = new ArrayList<>();
        switch (num){
            case 1:{
                //剩余可用的处理器数量为1个的最佳，其次是剩余3个的为次佳，然后是剩余2个，最后是剩余4个。
                if (lowCpus.size() > 1 && lowCpus.size() <4 ){
                    for (int i = 0; i < lowCpus.size(); i++) {
                        availableCpus.add(Arrays.asList(lowCpus.get(i)));
                    }
                }
                if (highCpus.size() > 1 && highCpus.size() <4 ){
                    for (int i = 0; i < highCpus.size(); i++) {
                        availableCpus.add(Arrays.asList(highCpus.get(i)));
                    }
                }
            }break;
            case 2:{
                if (lowCpus.size() >=2){
                    for (int i = 0; i < lowCpus.size(); i++) {
                        for (int j = i+1; j < lowCpus.size(); j++) {
                            List<Integer> aList = new ArrayList<>();
                            aList.add(lowCpus.get(i));
                            aList.add(lowCpus.get(j));
                            availableCpus.add(aList);
                        }
                    }
                }
                if (highCpus.size() >=2){
                    for (int i = 0; i < highCpus.size(); i++) {
                        for (int j = i+1; j < highCpus.size(); j++) {
                            List<Integer> aList = new ArrayList<>();
                            aList.add(highCpus.get(i));
                            aList.add(highCpus.get(j));
                            availableCpus.add(aList);
                        }
                    }
                }
            }break;
            case 4:{
                if (lowCpus.size() == 4){
                    availableCpus.add(Arrays.asList(0,1,2,3));
                }
                if (highCpus.size() == 4){
                    availableCpus.add(Arrays.asList(4,5,6,7));
                }
            }break;
            case 8:{
                if (array.length == 8){
                    availableCpus.add(Arrays.asList(0,1,2,3,4,5,6,7));
                }
            }break;
            default:

        }
        return availableCpus;
    }
}
