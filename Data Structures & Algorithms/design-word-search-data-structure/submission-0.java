class WordDictionary {

    private class Node{
        Node[] children = new Node[26];
        boolean isWord = false;
    }

    private Node root;

    public WordDictionary() {
        root = new Node();

    }

    public void addWord(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }
            curr = curr.children[index];
        }
        curr.isWord = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int index, Node curr) {
        if (index == word.length()) {
            return curr.isWord;
        }

        char c = word.charAt(index);

        if (c == '.') {
            for (int i = 0; i < 26; i++){
                Node child = curr.children[i];

                if (child != null && dfs(word, index + 1, child)){
                    return true;
                }
            }
            return false;
        }
        else {
            int idx = c - 'a';
            Node child = curr.children[idx];
            if (child == null){
                return false;
            }
            return dfs(word, index + 1, child);
        }
    }
}
