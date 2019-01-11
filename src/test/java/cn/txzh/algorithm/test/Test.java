package cn.txzh.algorithm.test;

import java.util.BitSet;

/**
 * Created by leizhao_3 on 2019/1/9.
 */
public class Test {
    public static void main(String[] args) {
        BitSet bitSet = new BitSet();
        bitSet.set(3);
        bitSet.set(4);
        bitSet.clear(3);
        System.out.println(bitSet);
        System.out.println(bitSet.length());
    }

}
