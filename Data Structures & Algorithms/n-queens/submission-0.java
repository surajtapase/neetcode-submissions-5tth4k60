class Solution {
    // Global tracking sets for quick O(1) safety checks
    private Set<Integer> cols = new HashSet<>();
    private Set<Integer> posDiag = new HashSet<>(); // (row + col)
    private Set<Integer> negDiag = new HashSet<>(); // (row - col)
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }

        backtrack(0, n, board);
        return result;
    }

    private void backtrack(int r, int n, char[][] board) {
        if (r == n) {
            result.add(createBoardSnapshot(board));
            return;
        }

        for (int c = 0; c < n; c++) {
            // Check if placing a queen at (r, c) is invalid
            if (cols.contains(c) || posDiag.contains(r + c) || negDiag.contains(r - c)) {
                continue; // Under attack! Skip this column.
            }

        cols.add(c);
        posDiag.add(r + c);
        negDiag.add(r - c);
        board[r][c] = 'Q';

        backtrack(r + 1, n, board);

        cols.remove(c);
        posDiag.remove(r + c);
        negDiag.remove(r - c);
        board[r][c] = '.';
    }
}

private List<String> createBoardSnapshot(char[][] board) {
    List<String> snapshot = new ArrayList<>();
    for (int i = 0; i < board.length; i++) {
        snapshot.add(new String(board[i]));
    }
    return snapshot;
}
}
