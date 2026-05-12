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

public class Codec {

    // Serializes a tree to a single string.
    // We use Pre-order Traversal (Root, Left, Right)
    public String serialize(TreeNode root) {
        if (root == null) return "N"; // NeetCode standard for null is often "null" or "N"
        
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (node == null) {
            sb.append("N,");
            return;
        }
        
        // Append root value, then recurse left and right
        sb.append(node.val).append(",");
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Deserializes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // Split the CSV string into an array of tokens
        String[] tokens = data.split(",");
        // Use a pointer (index array) to track our position during recursion
        int[] index = {0}; 
        return deserializeHelper(tokens, index);
    }

    private TreeNode deserializeHelper(String[] tokens, int[] index) {
        // Base case: if we reach 'N', it means it's a null node
        if (tokens[index[0]].equals("N")) {
            index[0]++;
            return null;
        }

        // Create the current node
        TreeNode root = new TreeNode(Integer.parseInt(tokens[index[0]]));
        index[0]++;

        // Recursively build the left and right subtrees
        // The order MUST match the serialization order (Pre-order)
        root.left = deserializeHelper(tokens, index);
        root.right = deserializeHelper(tokens, index);

        return root;
    }
}