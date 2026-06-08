class PrefixTree {

    private class Node {
        Node[] children = new Node[26];
        boolean isWord = false;
    }

    private Node root;

    public PrefixTree() {
         root = new Node();
    }

    public void insert(String word) {
        Node curr = root;

        for (int i = 0; i < word.length(); i++){
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
        Node curr = root;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            int index = c - 'a';

            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        return curr.isWord;
    }

    public boolean startsWith(String prefix) {
        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            int index = c - 'a';

            // If the prefix path breaks, no word starts with this prefix
            if (curr.children[index] == null) {
                return false;
            }
            curr = curr.children[index];
        }
        // If we successfully traversed the prefix without breaking, it exists!
        return true;
    }
}
