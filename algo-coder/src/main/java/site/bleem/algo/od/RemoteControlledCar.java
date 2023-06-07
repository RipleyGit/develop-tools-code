package site.bleem.algo.od;

/**
 * 假设在平面直角坐标系（上北-Y轴正方向，下南-Y轴负方向，左西-X轴负方向，右东-X轴正方向）上，一个遥控小车最初位于原点 (0, 0) 处，且面朝北方。
 * 遥控小车可以接受下列三条指令之一：
 * “G”：直走 1 个单位
 * “L”：左转 90 度
 * “R”：右转 90 度
 * 给定一批指令，遥控小车按顺序执行每个指令后，请计算遥控小车最终所处的位置。
 * 输入
 * 字符串表示的一批遥控指令，仅由字符 G、L、R组成，长度范围[1,100]
 * 输出
 * 小车最终所处位置的坐标
 * 样例
 * 输入样例 1 复制
 * GG
 * 输出样例 1
 * (0,2)
 */
public class RemoteControlledCar {

    public static String car(String command){
        int x = 0;
        int y = 0;
        int fangx = 0;
        for (int i = 0; i < command.length(); i++) {
            String charAt = String.valueOf(command.charAt(i));
            int i1 = fangx % 4;
            if (i1 == 0){
                fangx = 0;
            }
            switch (charAt){
                case "G":
                {
                    switch (i1){
                        case 0:
                            y++;
                            break;
                        case 1:
                        case -3:
                            x++;break;
                        case 2:
                        case -2:
                            y--;
                            break;
                        case -1:
                        case 3:
                            x--;
                            break;
                        default:
                    }
                }
                    break;
                case "L":
                    fangx --;
                    break;
                case "R":
                    fangx ++;
                    break;
                default:
            }
        }
        return "("+x+","+y+")";
    }

    public static void main(String[] args) {
        String gg = car("GLGLGLG");
        System.out.printf(gg);
        String xxx = car("GRGRGRG");
        System.out.printf(xxx);
    }

}
