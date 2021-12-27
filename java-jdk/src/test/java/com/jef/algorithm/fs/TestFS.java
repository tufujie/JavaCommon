package com.jef.algorithm.fs;

import com.jef.algorithm.TreeNode;
import com.jef.util.PrintUtil;

import org.junit.jupiter.api.Test;

/**
 * 测试深度优先遍历
 *
 * @author Jef
 * @date 2021/12/27
 */
public class TestFS {

    @Test
    public void testDFS() {
        TreeNode node1 = TreeNode.getBasicTreeNode();
        NodeFS.dfs(node1);
        PrintUtil.printSplitLine();

        NodeFS.dfsWithStack(node1);
    }

    @Test
    public void testBFS() {
        TreeNode node1 = TreeNode.getBasicTreeNode();
        NodeFS.bfs(node1);
    }

}