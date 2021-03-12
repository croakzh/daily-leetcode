/**
 * @author croakzh <croakzh@gmail.com>
 **/
public class LeetCode_331 {

    /**
     * "9,3,4,#,#,1,#,#,2,#,6,#,#"
     * "1,#"
     * "9,#,#,1"
     * <p>
     * <p>
     * 验证是否是正确的二叉树的前序序列化
     * <p>
     * 思考：
     * 1. 元素个数
     * 2. 空节点之下不能挂载有效元素
     *
     * @param preorder 格式有效的字符串
     * @return true/false
     */
    public boolean isValidSerialization(String preorder) {
        String[] orders = preorder.split(",");
        int temp = 1;
        for (String order : orders) {
            if (temp == 0) {
                return false;
            }

            if ("#".equals(order)) {
                temp--;
            } else {
                temp++;
            }
        }

        return temp == 0;
    }


}
