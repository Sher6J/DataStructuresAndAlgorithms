package cn.sher6j.queue;

import java.util.Scanner;

/**
 * @author sher6j
 * @create 2020-04-29-13:03
 */
public class ArrayQueueDemo {
    public static void main(String[] args) {
        //测试队列
        //创建一个队列
        ArrayQueue arrayQueue = new ArrayQueue(3);
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


//使用数组模拟队列  编写一个ArrayQueue类，数组只能使用一次，不能够复用
class ArrayQueue {
    private int maxSize; //表示队列的最大容量
    private int front; //队列头
    private int rear; //队列尾
    private int[] arr; //用于存放数据的数组

    //创建队列的构造器
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1; //指向队列头部，指向队列第一个元素的前一个位置
        this.rear = -1; //指向队列尾部
    }

    //判断队列是否满
    public boolean isFull() {
        return this.rear == this.maxSize - 1;
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
        this.rear++; //让rear后移
        this.arr[this.rear] = n;
    }

    //获取队列的数据，出队列
    public int getQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        this.front++;
        return this.arr[this.front];
    }

    //显示队列的所有数据
    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空，没有数据");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);

        }
    }

    //显示队列的头数据，不是取数据
    public int headQueue() {
        //判断队列是否空
        if (isEmpty()) {
            throw new RuntimeException("队列空，不能取数据");
        }
        return this.arr[this.front + 1];
    }
}
