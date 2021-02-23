package com.zhangyuzhou.tree;

import com.alibaba.fastjson.JSON;

import java.util.Objects;

public class BinaryTree {

    private class TreeNode {

        private int key = 0;

        private Object data = null;

        private boolean isVisited = false;

        private TreeNode leftChild = null;

        private TreeNode rightChild = null;

        public TreeNode() {
        }

        public TreeNode(int key, Object data) {
            this.key = key;
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
        }
    }

    private TreeNode root = null;

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public BinaryTree() {
        this.root = new TreeNode(1, "A");
    }

    public void createBinaryTree(TreeNode root) {
        TreeNode nodeB = new TreeNode(2, "B");
        TreeNode nodeC = new TreeNode(3, "C");
        TreeNode nodeD = new TreeNode(4, "D");
        TreeNode nodeE = new TreeNode(5, "E");
        TreeNode nodeF = new TreeNode(6, "F");

        root.leftChild = nodeB;
        root.rightChild = nodeC;

        nodeB.leftChild = nodeD;
        nodeB.rightChild = nodeE;

        nodeC.leftChild = nodeF;
    }

    /**
     * 计算树的节点数
     *
     * @param treeNode
     * @return
     */
    private int size(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return 0;
        }
        return 1 + size(treeNode.leftChild) + size(treeNode.rightChild);
    }

    private int height(TreeNode treeNode) {
        if (Objects.isNull(treeNode)) {
            return 0;
        }

        int heightA = height(treeNode.leftChild);
        int heightB = height(treeNode.rightChild);
        return heightA < heightB ? heightA + 1 : heightB + 1;
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode root = binaryTree.root;
        binaryTree.createBinaryTree(root);

    }
}
