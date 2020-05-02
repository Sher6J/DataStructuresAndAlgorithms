package cn.sher6j.linkedlist;

/**
 * @author sher6j
 * @create 2020-05-02-22:13
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        System.out.println("双向链表的测试：");

        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 hero3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 hero4 = new HeroNode2(4, "公孙胜", "入云龙");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();

        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);

        System.out.println("原始链表：");
        doubleLinkedList.show();
        System.out.println("================");

        HeroNode2 newHeroNode = new HeroNode2(4, "林冲", "豹子头");
        doubleLinkedList.update(newHeroNode);

        System.out.println("修改过后：");
        doubleLinkedList.show();
        System.out.println("=================");

        doubleLinkedList.del(3);
        System.out.println("删除后：");
        doubleLinkedList.show();
        System.out.println("=================");

    }
}

//双向链表的类
class DoubleLinkedList {
    //头结点
    private HeroNode2 head = new HeroNode2(0,"","");

    /**
     * 获取头结点
     * @return
     */
    private HeroNode2 getHead() {
        return this.head;
    }

    /**
     * 添加节点到双向链表
     * 思路：当不考虑编号时
     *     1. 找到当前链表的最后节点
     *     2. 将最后这个节点的next指向新的节点，新的节点的pre指向原来的最后节点
     * @param heroNode
     */
    public void add(HeroNode2 heroNode) {
        //由于head节点不能动，所以需要一个辅助遍历节点temp
        HeroNode2 temp = this.head;

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
        heroNode.pre = temp;
    }

    /**
     * 根据heroNode的no修改节点，和单向链表一样
     * @param heroNode
     */
    public void update(HeroNode2 heroNode) {
        //判断是否空
        if (this.head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode2 temp = this.head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = heroNode.name;
            temp.nickName = heroNode.nickName;
        } else {
            System.out.printf("没有编号为%d节点，不能修改\n", heroNode.no);
        }
    }

    /**
     * 根据编号删除节点
     *     对于双向链表，不需要再找要删除节点的前一个节点，找到要删除的节点直接自我删除即可
     * @param no
     */
    public void del(int no) {

        if (this.head.next == null) {
            System.out.println("链表为空，不能删除");
            return;
        }

        HeroNode2 temp = head.next;
        boolean flag = false; //标志是否找到待删除节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == no) {
                //找到了待删除节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.pre.next = temp.next;
            //如果是最后一个节点就不要执行下面，否则会有空指针异常
            if (temp.next != null) {
                temp.next.pre = temp.pre;
            }
        } else {
            System.out.printf("要删除编号为%d的节点不存在", no);
        }
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
        HeroNode2 temp = this.head.next;
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
class HeroNode2{
    public int no; //排行
    public String name; //姓名
    public String nickName; //绰号
    public HeroNode2 pre; //指向前一个节点
    public HeroNode2 next; //指向下一个节点


    public HeroNode2(int no, String name, String nickName) {
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