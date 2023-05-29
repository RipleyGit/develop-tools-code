package site.bleem.niucoder;

import java.util.*;
import java.util.stream.Collectors;

/**
 题目：单核CPU任务调度
    现在有一个CPU和一些任务需要处理，已提前获知每个任务的任务ID、优先级、所需执行时间和到达时间。CPU同时只能运行一个任务，
 请编写一个任务调度程序，采用“可抢占优先权调度”调度算法进行任务调度，规则如下：
    如果一个任务到采时，CPU是空闲的，则CPU可以运行该任务首到任条执行完毕。但是如果运行中有一个更高优先级的任务到来，则CPU必须暂停当前任务去运行这个优先级更高的任务;
    如果一个任务到来时，CPU正在运行一个比它优先级更高的任务时，新到达的任务必须等待;
    当CPU空闲时，如果还有任务在等待，CPU会从这些任务中选择一个优先级最高的任务执行，相同优先级的任务选择到达时间最早的任务。
 输入描述:
    输入有若干行，每一行有四个数字(均小于108)，分别为任务ID，任务优先级，执行时间和到达时间。
    每个任务的任务ID不同优先级数字越大优先级越高，并且相同优先级的任务不会同时到达。
    输入的任务已按照到达时间从小到达排列，并且保证在任何时间，处于等待的任务不超过10000个。
 输出描述:
    按照任务执行结束的顺序，输出每个任务的任务ID和对应的结束时间
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
public class SingleCpuPriorityQueue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<int[]> list = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String nextLine = scanner.nextLine();
            if ("".equals(nextLine)){
                break;
            }
            String[] split = nextLine.split(" ");
            int[] task = new int[4];
            for (int i = 0; i < 4; i++) {
                task[i] = Integer.valueOf(split[i]);
            }
            list.add(task);
        }
        int[][] ints = new int[list.size()][4];
        for (int i = 0; i < list.size(); i++) {
            ints[i] = list.get(i);
        }
        List<List<Integer>> executor = executor(ints);
        List<String> collect = executor.stream().map(li -> String.join(" ", li.stream().map(m -> String.valueOf(m)).collect(Collectors.toList()))).collect(Collectors.toList());
        System.out.println(String.join("\n",collect));
        scanner.close();
    }
    /**
     * 调度协调
     * 解题思路:
     * 关键点: 使用优先级队列 PriorityQueueQ来放置任务
     * 1.任务1进来，因为当前队列为空，所以直接加入队列，记录任务开始时间为1;队列中: 任务1 (1 3 5 1)
     * 2.任务2进来，求出两任务的时间间隙10-1=9，取队列中最上层任务1，所需时间为5，说明时间间隙中可以完成任务，输出第个任务1 6;更新任务开始时间为10，将任务2放入队列;
     * 队列中: 任务2 (2 1 5 10)
     * 3,任务3进来，求出两任务的时间间院12-10=2，取队列中最上层任务2，所需时间为5，说明时间间隙中不能完成任务、因为有2个时间的间隙，所有需要更新任务2的所需时间5-2=3;更新任务开始时间为12，将任务3放入队列;队列中: 任务3 (3 27 12) 、任务2 (2 1 3 10) (任务3的优先级高于任务2)
     * 4.任务4进来，求出两任务的时间间隙20-12=8、取队列中最上层任务3，所需时间为7，说明时间间原中能完成任务3，输出319;而20-19=1，时间剩余1，再取队列最上层任务2，而任务2需要3个时间，所有不能完成，更新所需时间3-1=2;更新任务开始时间为20，将任务2和任务4放入队列;队列中: 任务4 (4 3 2 20)、
     * 任务2 (2 1 2 1) (任务4的优先级高于任务2)
     * 5.任务5进来，求出两任务的时间间隙21-20=1，取队列中最上层任务4，所需时间为2，所有不能完成，更新所需时间2-1=1;更
     * 新任务开始时间为21;将任务5和任务4放入队列;队列中: 任务5 (5 4 9 21) 、任务4 (4 3 120) 、任务2 (2 12 10)
     * 6.任务6进来，求出两任务的时间间隙22-21=1，取队列中最上层任务5，所需时间为9，所有不能完成，更新所需时间9-1=8;更新任务开始时间为22;将任务6和任务5放入队列;
     * 队列中: 任务5 (548 21) 、任务6 (6 4222) 、任务4 (43 120) 、任务2(2 12 10)
     * 7.遍历队列中的任务
     *  1.任务5: 22+8=30、输出5 30
     *  2.任务6: 30+2=32、输出6 32
     *  3.任务4: 32+1=33、输出4 33
     *  4.任务2: 33+2=35、输出2 35
     * @param inputs
     * @return
     */
    public static List<List<Integer>> executor(int[][] inputs){
        List<List<Integer>> result = new ArrayList<>();
        ArrayList<Task> tasks = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            Task task = new Task(inputs[i][0], inputs[i][1], inputs[i][2], inputs[i][3]);
            tasks.add(task);
        }
        PriorityQueue<Task> queue = new PriorityQueue<>();
        int current=0;//开始时间
        for (Task task : tasks){
            int interval = task.start -current;//任务间隔时间
            while (!queue.isEmpty() && interval >0){//队列不为空，并且间隔时间>0
                if (interval >= queue.peek().need){//本次任务可以执行完成
                    Task poll = queue.poll();
                    interval -= poll.need; //间隔时间 - 任务运行时间，interval > 0 可以继续运行任务
                    current += poll.need;//更新当前时间
                    result.add(Arrays.asList(poll.id,current));
                }else {//本次任务不能执行完成
                    Task peek = queue.peek();//任务不出队列
                    peek.need -=interval;//间隔期间运行任务
                    current +=interval;//更新时间
                    break;//已经消耗完本次间隔时间，直接跳出
                }
            }
            if (current < task.start) current = task.start;
            queue.add(task);
        }
        while (!queue.isEmpty()){
            Task poll = queue.poll();
            current += poll.need;
            result.add(Arrays.asList(poll.id,current));
        }
        return result;
    }

    public static class Task implements Comparable<Task> {
        int id;
        int p;
        int need;
        int start;

        public Task(int id,int p,int need,int start){
            this.id = id;
            this.p = p;
            this.need = need;
            this.start = start;
        }

        @Override
        public int compareTo(Task o) {
            if (this.p == o.p){
                return this.start - o.start;//优先级相同，先进先出
            }
            return  o.p-this.p;//谁优先级高，谁先出
        }
    }
}
