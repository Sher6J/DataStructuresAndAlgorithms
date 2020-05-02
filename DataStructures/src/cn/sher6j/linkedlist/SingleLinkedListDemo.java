package cn.sher6j.linkedlist;

import java.util.Stack;

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

        //测试反转
        System.out.println("反转后：");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.show();
        System.out.println("==============");

        //测试逆序打印单链表
        System.out.println("逆序打印单链表如下：");
        reversePrint(singleLinkedList.getHead());
        System.out.println("==============");

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
        System.out.println("删除后：");
        singleLinkedList.show();

        //测试有单链表有效节点长度
        System.out.println("链表的节点个数为" + getLength(singleLinkedList.getHead()));

        //测试查找倒数第k个节点
        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 1));
    }

    /**
     * 获取单链表的节点个数（如果是带头结点的链表，不统计头结点）
     * @param head 链表的头结点
     * @return     有效节点的个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode current = head.next;
        while (current != null) {
            length++;
            current = current.next;
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第 k 个节点
     *     1.先遍历链表，得到总长度size
     *     2.得到size后，从链表第一个开始遍历(size - index)个，就可以得到
     * @param head 单链表的头结点
     * @param index    倒数第 k 个的 k
     * @return         查找到返回该节点，没有该节点则返回空
     */
    public static HeroNode findLastIndexNode(HeroNode head, int index) {

        if (head.next == null) {
            return null; //没找到
        }

        int size = getLength(head);

        if (index <= 0 || index > size) {
            return null;
        }

        HeroNode current = head.next;

        for (int i = 0; i < size - index; i++) {
            current = current.next;
        }
        return current;
    }

    /**
     * 反转单链表
     * @param head
     */
    public static void reverseList(HeroNode head) {
        //链表为空或只有一个节点
        if (head.next == null || head.next.next == null) {
            return;
        }

        HeroNode current = head.next; //当前节点
        HeroNode next = null; //当前节点的下一个节点
        HeroNode temp = new HeroNode(0, "", "");

        //遍历原来的链表，没遍历一个节点，就将其取出，并放在新的链表temp的最前端
        while (current != null) {
            next = current.next;//保存当前节点的下一个节点
            //将节点插到新链表的最前端
            current.next = temp.next;//将current的下一个节点指向新的新的链表的最前端
            temp.next = current;//将current连接到新的链表上
            current = next;
        }

        //将head.next 指向 temp.next
        head.next = temp.next;

    }

    /**
     * 逆序打印单链表
     * 思路：
     *     思路1（不可取）：先将单链表进行反转操作，然后再遍历即可，这样的做的问题是会破坏原来的单链表的结构，不建议
     *     思路2：可以利用栈这个数据结构，将各个节点压入到栈中，然后利用栈的先进后出的特点，就实现了逆序打印的效果
     * @param head
     */
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }

        //建栈
        Stack<HeroNode> stack = new Stack<>();
        HeroNode current = head.next;
        //将链表的所有节点入栈
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        //出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }
}

//定义SingleLinkedList
class SingleLinkedList {
    //初始化一个头结点，不存放具体数据
    private HeroNode head = new HeroNode(0, "", "");

    /**
     * 返回单链表的头结点
     * @return
     */
    public HeroNode getHead() {
        return this.head;
    }

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
