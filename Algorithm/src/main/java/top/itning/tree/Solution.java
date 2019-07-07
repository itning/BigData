package top.itning.tree;

/**
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
 *
 * @author itning
 * @date 2019/7/7 18:06
 * @see <a href='https://blog.csdn.net/DERRANTCM/article/details/45457557'>csdn blog</a>
 */
public class Solution {
    public static void main(String[] args) {
        int[] pre = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] in = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode treeNode = new Solution().reConstructBinaryTree(pre, in);
        System.out.println(treeNode);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        if (pre == null || in == null || pre.length != in.length || in.length < 1) {
            return null;
        }
        return construct(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    /**
     * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二节树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
     *
     * @param pre      前序遍历
     * @param preStart 前序遍历的开始位置
     * @param preEnd   前序遍历的结束位置
     * @param in       中序遍历
     * @param inStart  中序遍历的开始位置
     * @param inEnd    中序遍历的结束位置
     * @return 树的根结点
     */
    private TreeNode construct(int[] pre, int preStart, int preEnd, int[] in, int inStart, int inEnd) {
        // 开始位置大于结束位置说明已经没有需要处理的元素了
        if (preStart > preEnd) {
            return null;
        }
        // 取前序遍历的第一个数字，就是当前的根结点
        int value = pre[preStart];
        int index = inStart;
        // 在中序遍历的数组中找根结点的位置
        while (index <= inEnd && in[index] != value) {
            index++;
        }

        // 如果在整个中序遍历的数组中没有找到，说明输入的参数是不合法的，抛出异常
        if (index > inEnd) {
            throw new RuntimeException("Invalid input");
        }

        // 创建当前的根结点，并且为结点赋值
        TreeNode node = new TreeNode(value);

        // 递归构建当前根结点的左子树，左子树的元素个数：index-inStart+1个
        // 左子树对应的前序遍历的位置在[preStart+1, preStart+index-inStart]
        // 左子树对应的中序遍历的位置在[inStart, index-1]
        node.left = construct(pre, preStart + 1, preStart + index - inStart, in, inStart, index - 1);
        // 递归构建当前根结点的右子树，右子树的元素个数：inEnd-index个
        // 右子树对应的前序遍历的位置在[preStart+index-inStart+1, preEnd]
        // 右子树对应的中序遍历的位置在[index+1, inEnd]
        node.right = construct(pre, preStart + index - inStart + 1, preEnd, in, index + 1, inEnd);

        // 返回创建的根结点
        return node;
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
