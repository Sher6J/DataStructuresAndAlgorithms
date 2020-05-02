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

        //加入
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);

        //加入按编号
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);

        System.out.println("修改前：");
        singleLinkedList.show();
        System.out.println("=============");

        HeroNode newHero1 = new HeroNode(1, "宋公明", "呼保义");
        singleLinkedList.update(newHero1);

        System.out.println("修改后：");
        singleLinkedList.show();
        System.out.println("=============");

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后：");
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
     * 按序添加英雄，根据排名将其插入到指定位置
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        HeroNode temp = this.head; //辅助接点用来遍历
        //由于是单链表，找到temp应该是添加位置的前一个节点，否则插入不进去
        boolean flag = false; //标志添加的编号是否存在
        while (true) {
            if (temp.next == null) {//temp已经在链表的最后
                break;
            }
            if (temp.next.no > heroNode.no) {//位置找到，就在temp的后面插入
                break;
            } else if (temp.next.no == heroNode.no) { //已经存在
                flag = true;//说明编号存在
                break;
            }
            temp = temp.next;
        }
        //判断flag的值
        if (flag == true) { //编号存在，不能添加
            System.out.printf("该节点编号%d已经存在，不能加入\n", heroNode.no);
        } else {
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 根据heroNode的no修改节点
     * @param heroNode
     */
    public void update(HeroNode heroNode) {
        //判断是否空
        if (this.head.next == null) {
            System.out.println("链表为空");
            return;
        }
        HeroNode temp = this.head.next;
        boolean flag = false; //表示是否找到该节点
        while (true) {
            if (temp == null) {
                break;
            }
            if (temp.no == heroNode.no) {
                flag = true;
                break;
            }
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
     *     比较时应该是temp.next.no和传入的no比较
     * @param no
     */
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; //标志是否找到待删除节点
        while (true) {
            if (temp.next == null) { //链表最后
                break;
            }
            if (temp.next.no == no) {
                //找到了待删除节点的前一个节点temp
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.next = temp.next.next;
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
