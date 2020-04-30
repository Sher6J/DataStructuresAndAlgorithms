package cn.sher6j.linkedlist;

/**
 * 单链表
 * @author sher6j
 * @create 2020-04-30-18:36
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //测试

        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "公孙胜", "入云龙");

        SingleLinkedList singleLinkedList = new SingleLinkedList();

        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);

        singleLinkedList.show();
    }
}

//定义SingleLinkedList
class SingleLinkedList {
    //初始化一个头结点，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 添加节点到单向链表
     * 思路：当不考虑编号时
     *     1. 找到当前链表的最后节点
     *     2. 将最后这个节点的next指向新的节点
     * @param heroNode
     */
    public void add(HeroNode heroNode) {
        //由于head节点不能动，所以需要一个辅助遍历节点temp
        HeroNode temp = this.head;

        //遍历链表找到最后，退出while循环时，temp指向链表最后
        while (true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //没到链表随后
            temp = temp.next;
        }

        temp.next = heroNode;
    }

    /**
     * 显示链表[遍历]
     */
    public void show() {
        //判断链表是否为空
        if (this.head.next == null) {
            System.out.println("链表为空");
            return;
        }
        //由于head节点不能动，所以需要一个辅助遍历节点temp
        HeroNode temp = this.head.next;
        while (true) {
            //判断是否到链表最后
            if (temp == null) {
                break;
            }
            //输出节点信息
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

//定义HeroNode，每个HeroNode对象就是一个节点
class HeroNode{
    public int no; //排行
    public String name; //姓名
    public String nickName; //绰号
    public HeroNode next; //指向下一个节点

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
