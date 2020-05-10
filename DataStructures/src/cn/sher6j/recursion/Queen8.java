package cn.sher6j.recursion;

/**
 * @author sher6j
 * @create 2020-05-10-12:47
 */
public class Queen8 {

    int max = 8; //定义有8个皇后

    int[] arr = new int[max]; //定义结果数组保存皇后放置的位置

    public static void main(String[] args) {

    }

    /**
     * 判断当放置第n个皇后时，检测该皇后是否和前面已经摆放的皇后冲突
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //同一列 || 同一对角线
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印皇后摆放的位置
     */
    public void print() {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
