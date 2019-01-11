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

}
