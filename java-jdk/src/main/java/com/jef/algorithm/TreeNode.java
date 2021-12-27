package com.jef.algorithm;

/**
 * @author Jef
 * @date 2021/6/3
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {

    }

    TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 获取基础树节点
     *
     * @param
     * @return com.jef.algorithm.TreeNode
     * @author Jef
     * @date 2021/12/27
     */
    public static TreeNode getBasicTreeNode() {
        TreeNode node6 = new TreeNode(6, null, null);
        TreeNode node7 = new TreeNode(7, null, null);

        TreeNode node4 = new TreeNode(4, null, null);
        TreeNode node5 = new TreeNode(5, null, null);

        TreeNode node2 = new TreeNode(2, node4, node5);
        TreeNode node3 = new TreeNode(3, node6, node7);
        // 根节点
        TreeNode root = new TreeNode(1, node2, node3);
        return root;
    }
}