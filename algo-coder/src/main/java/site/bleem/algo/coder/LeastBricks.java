package site.bleem.algo.coder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastBricks {

    public static void main(String[] args) {
        List<List<Integer>> list = Arrays.asList(
                Arrays.asList(1, 2, 2, 1),
                Arrays.asList(3, 1, 2),
                Arrays.asList(2, 4),
                Arrays.asList(3, 1, 2),
                Arrays.asList(1, 3, 1, 1)
        );//[[1,2,2,1],[3,1,2],[1,3,2],[2,4],[3,1,2],[1,3,1,1]]
        leastBricks(list);
    }
    public static int leastBricks(List<List<Integer>> wall) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<wall.size();i++){
            int sum=0;
            for(int j=0;j<wall.get(i).size()-1;j++){
                sum+=wall.get(i).get(j);
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }
        int max=0;
        for(Map.Entry<Integer,Integer> entry:map.entrySet()){
            max=Math.max(entry.getValue(),max);
        }
        return wall.size()-max;
    }
}
