package com.jef.algorithm;

/**
 * 二叉搜索树的范围和
 * 10,5,15,3,7,null,18  7, 15
 * 10,5,15,3,7,13,18,1,null,6  6,10
 * @author Jef
 * @date 2021/5/30
 */
public class RangeSumBST {



    public static void main(String[] args) {
        TreeNode treeNodeOne = new TreeNode(10);
        TreeNode treeNodeTwo = new TreeNode(5);
        TreeNode treeNodeThree = new TreeNode(15);
        TreeNode treeNodeFour = new TreeNode(3);
        TreeNode treeNodeFive = new TreeNode(7);
        TreeNode treeNodeSix = new TreeNode(0);
        TreeNode treeNodeSeven = new TreeNode(18);
        treeNodeOne.right = treeNodeTwo;
        treeNodeTwo.left = treeNodeOne;
        treeNodeTwo.right = treeNodeThree;
        treeNodeThree.left = treeNodeTwo;
        treeNodeThree.right = treeNodeFour;
        treeNodeFour.left = treeNodeThree;
        treeNodeFour.right = treeNodeFive;
        treeNodeFive.left = treeNodeFour;
        treeNodeFive.right = treeNodeSix;
        treeNodeSix.left = treeNodeFive;
        treeNodeSix.right = treeNodeSeven;
        treeNodeSeven.left = treeNodeSix;
        /*treeNodeOne = new TreeNode(10, null, treeNodeTwo);
        treeNodeTwo = new TreeNode(5, treeNodeOne, treeNodeThree);
        treeNodeThree = new TreeNode(15, treeNodeTwo, treeNodeFour);
        treeNodeFour = new TreeNode(3, treeNodeThree, treeNodeFive);
        treeNodeFive = new TreeNode(7, treeNodeFour, treeNodeSix);
        treeNodeSix = new TreeNode(0, treeNodeFive, treeNodeSeven);
        treeNodeSeven = new TreeNode(18, treeNodeSix, null);*/
        System.out.println(new RangeSumBST().rangeSumBST(treeNodeOne, 7, 15));
    }

    /**
     * 个人解法
     * @author Jef
     * @date 2021/5/30
     * @return int
     */
    public int rangeSumBST(TreeNode root, int low, int high) {
        int num = 0;
        while (root.right != null) {
            if (root.val >= low && root.val <= high) {
                num += root.val;
            }
            root = root.right;
        }
        if (root.val >= low && root.val <= high) {
            num += root.val;
        }
        return num;
    }


}

