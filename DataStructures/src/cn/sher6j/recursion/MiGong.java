package cn.sher6j.recursion;

/**
 * @author sher6j
 * @create 2020-05-10-11:42
 */
public class MiGong {
    public static void main(String[] args) {
        //创建一个二维数组，模拟迷宫
        int[][] map = new int[8][7];
        //使用1表示墙
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        System.out.println("地图为：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        setWay2(map, 1, 1);

        System.out.println("小球走过并标识的地图：");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

    }

    //使用递归回溯给小球招路
    /**
     * 小球能到map[6][5]位置，则说明通路找到
     * 约定：0表示该点没有走过，1表示为墙，2表示通路可以走，3表示已经探测过走不通
     * 走迷宫时，策略：下右上左，走不通再回溯
     * @param map 地图
     * @param i   出发位置的横坐标
     * @param j   出发位置的纵坐标
     * @return    找到通路返回true，找不到返回false
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; //假定该点可以走通
                if (setWay(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    map[i][j] = 3; //走不通，死路
                    return false;
                }
            } else { //map[i][j] != 0, 可能是1， 2， 3
                return false;
            }
        }
    }

    /**
     * 修改策略上右下左
     * @param map
     * @param i
     * @param j
     * @return
     */
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2; //假定该点可以走通
                if (setWay2(map, i - 1, j)) { //向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) { //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) { //向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) { //向左走
                    return true;
                } else {
                    map[i][j] = 3; //走不通，死路
                    return false;
                }
            } else { //map[i][j] != 0, 可能是1， 2， 3
                return false;
            }
        }
    }
}
