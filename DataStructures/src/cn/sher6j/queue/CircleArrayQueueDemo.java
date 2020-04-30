package cn.sher6j.queue;

import java.util.Scanner;

/**
 * @author sher6j
 * @create 2020-04-30-9:38
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        System.out.println("测试数组模拟环形队列：");

        //创建一个环形队列，数据的有效数据最多为maxSize-1，因为预留了一个rear空间作为约定
        CircleArrayQueue arrayQueue = new CircleArrayQueue(4);
        char key = ' '; //接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop) {
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列中");
            System.out.println("g(get)：从队列中取数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.println("请输入一个数：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res = arrayQueue.getQueue();
                        System.out.printf("取出的数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int res = arrayQueue.headQueue();
                        System.out.printf("队列头的数据为%d\n", res);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }

}

class CircleArrayQueue {
    private int maxSize; //表示队列的最大容量
    private int front; //队列头 --指向队列第一个数据的位置
    private int rear; //队列尾 --指向队列最后一个数据的下一个位置
    private int[] arr; //用于存放数据的数组

    public CircleArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
//        this.front = 0; //变量初始值即为0，可以不写
//        this.rear = 0;
    }

    //判断队列是否满
    public boolean isFull() {
        return (this.rear + 1) % this.maxSize == this.front;
    }

    //判断队列是否为空
    public boolean isEmpty() {
        return this.rear == this.front;
    }

    //添加数据到队列
    public void addQueue(int n) {
        //判断队列是否满
        if (isFull()) {
            System.out.println("队列满，不能加入数据");
            return;
        }
        this.arr[this.rear] = n; //加入数据
        this.rear = (this.rear + 1) % this.maxSize; //让rear后移，必须考虑取模
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里不能直接return，return后就不能操作front了，所以需要用临时变量接收front
//        return this.arr[this.front];
        int value = this.arr[this.front];
        this.front = (this.front + 1) % this.maxSize; //front后移要考虑取模
        return value;
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        //从front开始遍历，遍历有效元素的个数
        for (int i = this.front; i < this.front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % this.maxSize, arr[i % this.maxSize]);

        }
    }

    //返回当前队列有效数据的个数
    public int size() {
        return (this.rear + this.maxSize - this.front) % this.maxSize;
    }

    //显示队列的头数据，不是取数据
    public int headQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return this.arr[this.front];
    }
}
