package cn.txzh.iterative;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by leizhao_3 on 2019/4/30.
 */
public class Lesson3_1 {
    public static double getSquareRoot(int n, double deltaThreshold, int maxTry) {
        if (n < 1) {
            return -1.0;
        }
        double min = 1.0;
        double max = (double) n;
        for (int i = 0; i < maxTry; i++) {
            double middle = (min + max)/2;
            double square = middle*middle;

        }
        return 0.0;
    }

    public static long[] rewards = {1, 2, 5, 10};

    public static void get(long totalReward, ArrayList<Long> result) {
        if (totalReward == 0) {
            System.out.println(result);
            return;
        }
        if (totalReward < 0) {
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Long> newResult = (ArrayList<Long>)result.clone();
                newResult.add(rewards[i]);
                get(totalReward - rewards[i], newResult);
            }
        }
    }

    public static int[] mergeSort(int[] toSort) {
        if (toSort == null) {
            return new int[0];
        }
        if (toSort.length == 1) {
            return toSort;
        }

        int mid = toSort.length/2;
        int[] left = Arrays.copyOfRange(toSort, 0, mid);
        int[] right = Arrays.copyOfRange(toSort, mid, toSort.length);

        left = mergeSort(left);
        right = mergeSort(right);

        return null;
    }
    public static void main(String[] args) {
        get(10, new ArrayList<Long>());
    }
}
