import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 227解题思路：栈操作，对于 * / 出栈前面一个数字，跟后面的数字进行计算操作。（本题已知的前提条件是s有效，只包含四个符号，所以难度已经降低了）
 *
 * @author croakzh <croakzh@gmail.com>
 **/
public class LeetCode_227 {

    /**
     * 😅，不想看到空格，我懒，比较死的方法。
     * 读题：
     * 1. s有效
     * 2. 只包含 + - * /
     * 思考：
     * 1. 使用栈进行操作
     * 2. 空格的问题
     **/
    public int calculateV1(String str) {
        String[] nums = str.split("[\\*/+-]");
        String[] marks = str.split("[0-9]+");

        Deque<String> deque = new ArrayDeque<>();
        deque.addFirst(nums[0].trim());
        for (int i = 1; i < nums.length; i++) {
            /**
             * 如果是乘法或者是除法
             */
            String mark = marks[i].trim();
            String num = nums[i].trim();
            if ("*".equals(mark) || "/".equals(mark)) {
                String priorNum = deque.pollFirst();
                if ("*".equals(mark)) {
                    deque.addFirst((Integer.parseInt(priorNum) * Integer.parseInt(num)) + "");
                } else {
                    deque.addFirst((Integer.parseInt(priorNum) / Integer.parseInt(num)) + "");
                }
            } else {
                deque.addFirst(mark);
                deque.addFirst(num);
            }
        }

        int result = Integer.parseInt(deque.pollLast());
        while (!deque.isEmpty()) {
            String unit = deque.pollLast();
            if ("+".equals(unit) || "-".equals(unit)) {
                if ("+".equals(unit)) {
                    result += Integer.parseInt(deque.pollLast());
                } else {
                    result -= Integer.parseInt(deque.pollLast());
                }
            }
        }
        return result;
    }

    /**
     * 官方解答
     * @param str 运算式子
     * @return 计算结果
     */
    public int calculateV2(String str) {
        Deque<Integer> stack = new LinkedList<>();
        // 第一个字符操作总是 +
        char mark = '+';
        Integer tempNum = 0;
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                tempNum = tempNum * 10 + (ch - '0');
            }

            if ((!Character.isDigit(ch) && ch != ' ') || i == str.length() - 1) {
                switch (mark) {
                    case '+':
                        stack.push(tempNum);
                        break;
                    case '-':
                        stack.push(-tempNum);
                        break;
                    case '*':
                        stack.push(stack.pop() * tempNum);
                        break;
                    default:
                        stack.push(stack.pop() / tempNum);
                }
                tempNum = 0;
                mark = ch;
            }

        }

        int result = 0;
        while (!stack.isEmpty()) {
            result += stack.pop();
        }
        return result;
    }

}
