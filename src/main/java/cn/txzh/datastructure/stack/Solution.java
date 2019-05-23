package cn.txzh.datastructure.stack;

/**
 * Created by leizhao_3 on 2019/5/23.
 */
public class Solution {

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        Stack<Character> stack = new ArrayStack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                Character topChar = stack.pop();
                if (c == ')' && topChar != c) {
                    return false;
                }
                if (c == ']' && topChar != c) {
                    return false;
                }
                if (c == '}' && topChar != c) {
                    return false;
                }
            }
        }
        return true;
    }
}
