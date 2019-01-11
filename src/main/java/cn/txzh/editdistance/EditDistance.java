package cn.txzh.editdistance;

import cn.txzh.util.StringUtils;

/**
 * 运用动态规划算法计算两个字符串之间的编辑距离<br/>
 * <p>
 *     给定两个字符串s1 及s2，两者的编辑距离（edit distance）定义为将s1 转换成s2 的最小编辑操
 *   作（edit operation）数。通常，这些编辑操作包括： (i) 将一个字符插入字符串； (ii) 从字符串中
 *  删除一个字符； (iii) 将字符串中的一个字符替换成另外一个字符。 对于这些操作，编辑距离有
 *  时也称为Levenshtein距离（Levenshtein distance）
 * </p>
 */
public class EditDistance {

    public static int calculateEditDistance(String str1, String str2) {
        if (StringUtils.isEmpty(str1) || StringUtils.isEmpty(str2)) {
            return -1;
        }
        int str1Length = str1.length();
        int str2Length = str2.length();
        int[][] twoDimensionArray = new int[str1Length+1][str2Length+1];

        for (int i = 0; i <= str1Length; i++) {
            twoDimensionArray[i][0] = i;
        }

        for (int j = 0; j <= str2Length; j++) {
            twoDimensionArray[0][j] = j;
        }

        for (int i = 0; i < str1Length; i++) {
            for (int j = 0; j < str2Length; j++) {
                int r = 0;
                if (str1.charAt(i) != str2.charAt(j)) {
                    r = 1;
                }

                int firstAppend = twoDimensionArray[i][j+1] + 1;
                int secondAppend = twoDimensionArray[i+1][j] + 1;
                int replace = twoDimensionArray[i][j] + r;

                int min  = Math.min(firstAppend, secondAppend);
                min = Math.min(min, replace);

                twoDimensionArray[i+1][j+1] = min;

            }
        }

        return twoDimensionArray[str1Length][str2Length];
    }

    public static void main(String[] args) {
        System.out.println(calculateEditDistance("zhaolei", "zhaolie"));
    }
}
