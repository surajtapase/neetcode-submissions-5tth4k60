class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        List<List<String>> result = new ArrayList<>();

        boolean[][] dp = new boolean[n][n];

        for (int len = 1; len <= n; len++){
            for (int i = 0; i <= n - len; i++){
                int j = i + len - 1;
                if(s.charAt(i) == s.charAt(j)){
                    if (len <= 2 ||dp[i + 1][j - 1]){
                        dp[i][j] = true;
                    }
                }
            }
        }

        backtrack(s, 0, dp, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, boolean[][] dp, List<String> currentList, List<List<String>> result){
        if (start == s.length()) {
            result.add(new ArrayList<>(currentList));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            // If the substring from 'start' to 'end' is a palindrome
            if (dp[start][end]) {
                // Choose: Add the substring to our current partition list
                currentList.add(s.substring(start, end + 1));
                
                // Explore: Recurse starting from the next character
                backtrack(s, end + 1, dp, currentList, result);
                
                // Un-choose: Backtrack to explore other potential partitions
                currentList.remove(currentList.size() - 1);
            }
        }
    }
}
