package cn.sher6.sparsearray;

/**
 * 稀疏数组与普通数组之间的相互转化
 * @author sher6j
 * @create 2020-04-28-21:15
 */
public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0表示没有棋子，1表示黑子，2表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始的二维数组
        System.out.println("原始的二维数组：");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //普通数组转化为稀疏数组
        int[][] sparseArr = arrayToSparseArray(chessArr1);

        System.out.println("===========================================");

        //输出稀疏数组
        System.out.println("得到的稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }

        System.out.println("===========================================");

        //稀疏数组转化为普通数组
        int[][] chessArr2 = sparseArrayToArray(sparseArr);

        System.out.println("恢复后的二维数组：");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }

    /**
     * 二维数组 转 稀疏数组
     *    1. 遍历  原始的二维数组，得到有效数据的个数 sum
     *    2. 根据sum 就可以创建 稀疏数组 sparseArr   int[sum + 1] [3]
     *    3. 将二维数组的有效数据数据存入到 稀疏数组
     * @param arr 原数组
     * @return    稀疏数组
     */
    public static int[][] arrayToSparseArray(int[][] arr) {
        //1.先遍历二维数组，得到非0数据的个数
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        //3.遍历二维数组，将非0的值存放到稀疏数组中
        int count = 0; //用于记录第几个非0数据
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }

        return sparseArr;
    }

    /**
     * 稀疏数组转原始的二维数组
     *    1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的  chessArr2 = int [11][11]
     *    2. 在读取稀疏数组后几行的数据（第二行开始），并赋给 原始的二维数组 即可.
     * @param sparseArr 稀疏数组
     * @return          转化后的数组
     */
    public static int[][] sparseArrayToArray(int[][] sparseArr) {
        int arr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        return arr;
    }
}
