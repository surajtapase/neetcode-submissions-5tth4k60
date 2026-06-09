class Solution {
    
    // Trie Node representation definition
    private class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null; // Stores the complete word string if it ends here
    }

    public List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        
        // Step 1: Build the Trie out of all target words
        TrieNode root = buildTrie(words);
        
        // Step 2: Iterate through every cell on the board to initiate a DFS
        int rows = board.length;
        int cols = board[0].length;
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                dfs(board, r, c, root, result);
            }
        }
        
        return result;
    }

    private void dfs(char[][] board, int r, int c, TrieNode currNode, List<String> result) {
        // Boundary check and visited cell validation
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] == '#') {
            return;
        }

        char ch = board[r][c];
        int index = ch - 'a';
        
        // If the character path doesn't exist in the Trie, prune this branch
        if (currNode.children[index] == null) {
            return;
        }

        // Advance to the next child level in our Trie
        currNode = currNode.children[index];
        
        // If we matched a complete word, grab it and add it to our answers
        if (currNode.word != null) {
            result.add(currNode.word);
            currNode.word = null; // De-duplicate: prevent adding the same word multiple times
        }

        // 1. Mark current cell as visited using a placeholder
        board[r][c] = '#';

        // 2. Explore all 4 adjacent directions (Up, Down, Left, Right)
        dfs(board, r + 1, c, currNode, result);
        dfs(board, r - 1, c, currNode, result);
        dfs(board, r, c + 1, currNode, result);
        dfs(board, r, c - 1, currNode, result);

        // 3. Backtrack: Restore the original character for other search routes
        board[r][c] = ch;
    }

    // Helper function to insert strings into our Prefix Tree
    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();
        for (String w : words) {
            TrieNode curr = root;
            for (int i = 0; i < w.length(); i++) {
                int index = w.charAt(i) - 'a';
                if (curr.children[index] == null) {
                    curr.children[index] = new TrieNode();
                }
                curr = curr.children[index];
            }
            curr.word = w; // Store the full string at the leaf node
        }
        return root;
    }
}