/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    int maxPath = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateGain(root);
        return maxPath;    
    }

    private int calculateGain(TreeNode node){
        if(node == null) return 0;

        int leftGain = Math.max(calculateGain(node.left),0);
        int rightGain = Math.max(calculateGain(node.right),0);

        int currentPathSum = node.val + leftGain + rightGain;
        maxPath = Math.max(maxPath, currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }
}
