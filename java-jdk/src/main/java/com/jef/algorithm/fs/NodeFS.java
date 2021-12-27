package com.jef.algorithm.fs;

import com.jef.algorithm.TreeNode;
import com.jef.util.PrintUtil;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 节点优先遍历
 *
 * @author Jef
 * @date 2021/12/27
 */
public class NodeFS {

    /**
     * 使用递归实现 dfs
     *
     * @param treeNode 树节点
     * @return void
     * @author Jef
     * @date 2021/12/27
     */
    public static void dfs(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        // 遍历节点
        process(treeNode);
        // 遍历左节点
        dfs(treeNode.left);
        // 遍历右节点
        dfs(treeNode.right);
    }

    /**
     * 遍历节点
     *
     * @param treeNode
     * @author Jef
     * @date 2021/12/27
     */
    private static void process(TreeNode treeNode) {
        PrintUtil.printf("value=%s", treeNode.val);
    }

    /**
     * 使⽤栈来实现 dfs
     *
     * @param root 根节点
     * @author Jef
     * @date 2021/12/27
     */
    public static void dfsWithStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        // 先把根节点压栈
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();
            // 遍历节点
            process(treeNode);
            // 先压右节点
            if (treeNode.right != null) {
                stack.push(treeNode.right);
            }
            // 再压左节点
            if (treeNode.left != null) {
                stack.push(treeNode.left);
            }
        }
    }

    /**
     * 使⽤队列实现 bfs
     *
     * @param root 根节点
     * @author Jef
     * @date 2021/12/27
     */
    public static void bfs(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> stack = new LinkedList<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.poll();
            process(node);
            TreeNode left = node.left;
            if (left != null) {
                stack.add(left);

            }
            TreeNode right = node.right;
            if (right != null) {
                stack.add(right);
            }
        }
    }

}