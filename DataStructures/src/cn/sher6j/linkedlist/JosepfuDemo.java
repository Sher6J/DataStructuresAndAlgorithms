package cn.sher6j.linkedlist;

/**
 * @author sher6j
 * @create 2020-05-03-10:41
 */
public class JosepfuDemo {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addNode(5);
        circleSingleLinkedList.show();
    }
}


//环形单向链表
class CircleSingleLinkedList {
    //创建一个first节点
    private Node first = null;

    /**
     * 添加nums个节点构成环形链表
     * @param nums 添加节点个数
     */
    public void addNode(int nums) {
        if (nums < 1) {
            System.out.println("num值不正确");
            return;
        }
        Node current = new Node();//辅助节点，帮助构建环形链表
        for (int i = 1; i <= nums; i++) {
            Node node = new Node(i);
            //如果是第一个节点
            if (i == 1) {
                first = node;
                first.setNext(first); //构成一个环，环中只有一个节点
                current = first; //current永远指向当前环的最后一个节点
            } else {
                current.setNext(node);
                node.setNext(first);
                current = node;
            }
        }
    }

    /**
     * 遍历
     */
    public void show() {
        if (first == null) {
            System.out.println("链表为空");
            return;
        }
        Node current = first;
        while (true) {
            System.out.printf("节点的编号%d\n", current.getNo());
            if (current.getNext() == first) {
                break;
            }
            current = current.getNext();
        }
    }


}

//节点
class Node {
    private int no;
    private Node next;

    public Node() {
    }

    public Node(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}
