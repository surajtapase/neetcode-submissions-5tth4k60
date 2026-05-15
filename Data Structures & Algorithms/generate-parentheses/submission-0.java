class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();

        backtrack (result, new StringBuilder(), 0, 0, n);
        return result;
    }

    private void backtrack(List<String> res, StringBuilder current, int open, int close, int n){
        if (current.length() == n * 2){
            res.add(current.toString());
            return;
        }

        if (open < n){
            current.append("(");
            backtrack(res, current, open + 1, close, n);
            current.deleteCharAt(current.length() - 1);
        }

        if (close < open) {
            current.append(")");

            backtrack(res,current, open, close + 1, n);

            current.deleteCharAt(current.length() - 1);
        }
    }
}
