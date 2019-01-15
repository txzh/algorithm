package cn.txzh.resort;

import cn.txzh.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * 给定一组有序对象，现规定某属性值相同的对象不能排在一起，怎么实现？
 */
public class Resort {

    public static void reSort(List<ResortElement> list, int distance) {
        if (CollectionUtils.isEmpty(list) || distance < 1) {
            return;
        }
        int size = list.size();
        for (int index = 1; index < size; index++) {
            ResortElement resortElement = list.get(index);
            int lookBackCount = index > distance ? distance : index; //计算向前回溯的个数
            int end = index - lookBackCount;
            //从当前索引位置向上和checkCount-1个元素做比较
            boolean existSimilar = false;
            for (int i = index - 1; i >= end; i--) {
                if (resortElement.similar(list.get(i))) {
                    existSimilar = true;
                    break;
                }
            }

            int tempIndex = index;

            //如果存在相似的重排序元素，就从当前索引处向下寻找可以和当前索引处的元素排在一起的对象，如果找到就交互该对象和当前对象的次序
            if (existSimilar) {

                while (existSimilar) {
                    tempIndex++;
                    if (tempIndex >= size) {
                        break;
                    }
                    boolean tempExistSimilar = false;
                    ResortElement tempElement = list.get(tempIndex);
                    for (int i = index - 1; i >= end; i--) {
                        if (tempElement.similar(list.get(i))) {
                            tempExistSimilar = true;
                            break;
                        }
                    }
                    if (!tempExistSimilar) {
                        existSimilar = false;
                    }
                }

                if (tempIndex < size) {
                    Collections.swap(list, index, tempIndex);
                }
             }
        }
    }

    /**
     *  改进以保证元素原来的顺序
     * @param list 待排序列表
     * @param distance 间隔距离
     * @param debug 打印调试日志
     */
    public static void reSort2(List<ResortElement> list, int distance, boolean debug) {
        if (CollectionUtils.isEmpty(list) || distance < 1) {
            return;
        }
        int size = list.size();
        for (int index = 1; index < size - 1; index++) {
            ResortElement resortElement = list.get(index);
            int lookBackCount = index > distance ? distance : index; //计算向前回溯的个数
            int end = index - lookBackCount;
            //从当前索引位置向上和checkCount-1个元素做比较
            boolean existSimilar = false;
            for (int i = index - 1; i >= end; i--) {
                if (resortElement.similar(list.get(i))) {
                    existSimilar = true;
                    break;
                }
            }

            int tempIndex = index;

            //如果存在相似的重排序元素，就从当前索引处向下寻找可以和当前索引处的元素排在一起的对象，如果找到就交互该对象和当前对象的次序
            if (existSimilar) {

                while (existSimilar) {
                    tempIndex++;
                    if (tempIndex >= size) {
                        break;
                    }
                    boolean tempExistSimilar = false;
                    ResortElement tempElement = list.get(tempIndex);
                    for (int i = index - 1; i >= end; i--) {
                        if (tempElement.similar(list.get(i))) {
                            tempExistSimilar = true;
                            break;
                        }
                    }
                    if (!tempExistSimilar) {
                        existSimilar = false;
                    }
                }

                if (tempIndex < size) {//将index到tempIndex之间的房屋移动到tempIndex之下，而不是交换index和tempIndex
//                    Collections.swap(list, index, tempIndex);
                    if (index == size - 2) {//如果是倒数第二个元素，则直接交换最后两个元素的位置
                        Collections.swap(list, index, index + 1);
                    } else {
                        list.set(index, list.get(tempIndex));
                        int indexPlusOne = index + 1;
                        for (int j = tempIndex; j > indexPlusOne; j--) {
                            list.set(j, list.get(j-1));
                        }
                        list.set(indexPlusOne, resortElement);
                    }
                    if (debug) {
                        System.out.println("--------------index="+ index+"----------------------");
                        for (ResortElement tempElement : list) {
                            System.out.println(tempElement);
                        }
                        System.out.println("------------------------------------");
                    }
                }
            }
        }
    }

}
