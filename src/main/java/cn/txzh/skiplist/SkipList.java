package cn.txzh.skiplist;

/**
 * 跳跃表的最低层有全量的数据
 */
@SuppressWarnings("unchecked")
public class SkipList<T> {
    // 最高层数
    private final int MAX_LEVEL;

    // 当前层数
    private int listLevel;

    // 表头
    private SkipListNode<T> listHead;

    // 表尾
    private SkipListNode<T> NIL;

    // 生成randomLevel用到的概率值
    private final double P;

    // 论文里给出的最佳概率值
    private static final double OPTIMAL_P = 0.25;

    public SkipList() {
        // 0.25, 15
        this(OPTIMAL_P, (int) Math.ceil(Math.log(Integer.MAX_VALUE) / Math.log(1 / OPTIMAL_P)) - 1);
    }

    public SkipList(double probability, int maxLevel) {
        P = probability;
        MAX_LEVEL = maxLevel;

        listLevel = 1;
        listHead = new SkipListNode<T>(Integer.MIN_VALUE, null, maxLevel);
        NIL = new SkipListNode<T>(Integer.MAX_VALUE, null, maxLevel);
        for (int i = listHead.forward.length - 1; i >= 0; i--) {
            listHead.forward[i] = NIL;
        }
    }

    // 内部类
    class SkipListNode<T> {
        private int key;
        private T value;
        private SkipListNode[] forward;

        SkipListNode(int key, T value, int level) {
            this.key = key;
            this.value = value;
            this.forward = new SkipListNode[level];
        }

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public SkipListNode[] getForward() {
            return forward;
        }

        public void setForward(SkipListNode[] forward) {
            this.forward = forward;
        }
    }

    /**
     * 从链表表头开始，从最高层级的节点开始依次往低层节点查找，在同一层级节点查找时和普通有序
     * 链表是一样的，即顺序查找到第一个大于或等于当前查找数值searchKey的节点，当在本层级未找到时就
     * 下降一个层级继续查找，直到找到或发现链表中不包含searchKey的节点。
     * @param searchKey 查找键
     * @return
     */
    public T search(int searchKey) {
        SkipListNode<T> curNode = listHead;
        //从最高层依次查找到最低层，除了最低层其他层次是为了加快查找速度的，最终还是要回到最低层
        for (int i = listLevel - 1; i >= 0; i--) {
            while (curNode.forward[i].getKey() < searchKey) {
                curNode = curNode.forward[i];
            }
        }
        //如果存在searchKey的节点，则必定是当前节点的下一个节点
        curNode = curNode.forward[0];
        if (curNode.key == searchKey) {
            return curNode.value;
        } else {
            return null;
        }
    }

    public void insert(int searchKey, T newValue) {
        //update数组用于临时存放查找searchKey路径时每层最后的节点，主要目的是记录信息，使得
        //在插入节点时能像普通链表插入时一样修改新插入节点导致的各种指针变化
        SkipListNode<T>[] update = new SkipListNode[MAX_LEVEL];
        SkipListNode<T> curNode = listHead;

        for (int i = listLevel - 1; i >= 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
            // curNode.key < searchKey <= curNode.forward[i].key
            update[i] = curNode;
        }

        curNode = curNode.forward[0];

        if (curNode.key == searchKey) {
            curNode.value = newValue;
        } else {
            int level = randomLevel();
            if (listLevel < level) {
                for (int i = listLevel; i < level; i++) {
                    update[i] = listHead;
                }
                listLevel = level;
            }

            SkipListNode<T> newNode = new SkipListNode<T>(searchKey, newValue, level);
            //根据update数组保留的信息调整各个层级的相关指针指向
            for (int i = 0; i < level; i++) {
                newNode.forward[i] = update[i].forward[i];
                update[i].forward[i] = newNode;
            }
        }
    }

    public void delete(int searchKey) {
        SkipListNode<T>[] update = new SkipListNode[MAX_LEVEL];
        SkipListNode<T> curNode = listHead;

        for (int i = listLevel - 1; i >= 0; i--) {
            while (curNode.forward[i].key < searchKey) {
                curNode = curNode.forward[i];
            }
            // curNode.key < searchKey <= curNode.forward[i].key
            update[i] = curNode;
        }

        curNode = curNode.forward[0];

        if (curNode.key == searchKey) {
            for (int i = 0; i < listLevel; i++) {
                if (update[i].forward[i] != curNode) {
                    break;
                }
                update[i].forward[i] = curNode.forward[i];
            }

            while (listLevel > 0 && listHead.forward[listLevel - 1] == NIL) {
                listLevel--;
            }
        }
    }

    private int randomLevel() {
        int level = 1;
        while (level < MAX_LEVEL && Math.random() < P) {
            level++;
        }
        return level;
    }

    public void print() {
        for (int i = listLevel - 1; i >= 0; i--) {
            SkipListNode<T> curNode = listHead.forward[i];
            while (curNode != NIL) {
                System.out.print(curNode.key + "->");
                curNode = curNode.forward[i];
            }
            System.out.println("NIL");
        }
    }
}
