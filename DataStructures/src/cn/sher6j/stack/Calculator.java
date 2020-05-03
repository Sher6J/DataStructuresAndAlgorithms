package cn.sher6j.stack;

/**
 * 用栈实现计算器
 * @author sher6j
 * @create 2020-05-03-17:21
 */
public class Calculator {
    public static void main(String[] args) {
        String expression = "3+2*6-2";
        //创建两个栈，一个数栈一个符号栈
        ArrayStack2 numStack = new ArrayStack2(10);
        ArrayStack2 operStack = new ArrayStack2(10);

        int index = 0;//用于扫描
        int num1 = 0;
        int num2 = 0;
        int oper = 0;
        int res = 0;
        char ch = ' '; //每次扫描得到的char

        while (true) {
            ch = expression.substring(index, index + 1).charAt(0);

            if (operStack.isOper(ch)) {
                if (operStack.isEmpty()) {

                } else {

                }
            }
        }
    }
}

class ArrayStack2 {
    private int maxSize; //栈的最大容量
    private int[] stack; //数组模拟栈，数据放在数组中
    private int top = -1; //top表示栈顶，初始为-1

    public ArrayStack2(int maxSize) {
        this.maxSize = maxSize;
        stack = new int[this.maxSize];
    }

    /**
     * 判断栈是否满
     * @return
     */
    public boolean isFull() {
        return top == maxSize - 1;
    }

    /**
     * 判断栈是否空
     * @return
     */
    public boolean isEmpty() {
        return top == -1;
    }

    /**
     * 入栈
     * @param value
     */
    public void push(int value) {
        if (this.isFull()) {
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top] = value;
    }

    /**
     * 出栈
     * @return
     */
    public int pop() {
        if (this.isEmpty()) {
            throw new RuntimeException("栈空，没有数据");
        }
        int value = stack[top];
        top--;
        return value;
    }

    /**
     * 遍历栈，遍历时需要从栈顶开始显示数据
     */
    public void show() {
        if (isEmpty()) {
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n", i, stack[i]);
        }
    }

    /**
     * 返回运算符的优先级，数字越大，优先级越高
     * @param oper
     * @return
     */
    public int priority(int oper) {
        if (oper == '*' || oper == '/') {
            return 1;
        } else if (oper == '+' || oper == '-') {
            return 0;
        } else {
            return -1;
        }
    }

    /**
     * 判断是否是运算法
     * @param val
     * @return
     */
    public boolean isOper(char val) {
        return val == '+' || val == '-' || val == '*' || val == '/';
    }

    /**
     * 计算方法
     * @param num1 栈中靠近栈顶的数
     * @param num2 栈中靠近栈底的数
     * @param oper
     * @return
     */
    public int cal(int num1, int num2, int oper) {
        int res = 0; //存放计算结果
        switch (oper) {
            case '+':
                res = num1 + num2;
                break;
            case '-':
                res = num2 - num1;
                break;
            case '*':
                res = num1 * num2;
                break;
            case '/':
                res = num2 / num1;
                break;
        }
        return res;
    }
}


