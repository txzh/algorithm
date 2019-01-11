package cn.txzh.algorithm.test.skiplist;

import cn.txzh.skiplist.SkipList;

public class SkipListTest {
    public static void main(String[] args) {
        SkipList<Integer> sl = new SkipList<Integer>();
        sl.insert(20, 20);
        sl.insert(5, 5);
        sl.insert(10, 10);
        sl.insert(1, 1);
        sl.insert(100, 100);
        sl.insert(80, 80);
        sl.insert(60, 60);
        sl.insert(30, 30);
        sl.print();
        Integer search = sl.search(10);
        System.out.println(search);
   /*     System.out.println("---");
        sl.delete(20);
        sl.delete(100);
        sl.print();*/
    }
}
