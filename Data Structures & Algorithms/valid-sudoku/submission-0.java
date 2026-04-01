class Solution {
    public boolean isValidSudoku(char[][] board) {
        HashSet<String> seen = new HashSet<>();

        for(int r = 0; r < 9; r++){
            for(int c = 0; c < 9; c++){
                char ch = board[r][c];

                if (ch == '.') continue;

                if(!seen.add(ch + "row" + r) ||
                   !seen.add(ch + "col" + c) ||
                   !seen.add(ch + "box" + (r/3) + (c / 3))){
                    return false;
                   }
            }
        }
        return true;
    }
}
