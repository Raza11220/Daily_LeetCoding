class Solution {
public:
    vector<vector<string>> solveNQueens(int n) {
        vector<vector<string>> res;
        vector<string> board(n, string(n, '.'));
        
        vector<int> cols(n, 0);
        vector<int> diag1(2*n, 0); // r - c + n
        vector<int> diag2(2*n, 0); // r + c
        
        solve(0, n, board, res, cols, diag1, diag2);
        return res;
    }
    
    void solve(int row, int n, vector<string>& board, 
               vector<vector<string>>& res,
               vector<int>& cols, vector<int>& diag1, vector<int>& diag2) {
        
        if (row == n) {
            res.push_back(board);
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n] || diag2[row + col])
                continue;
            
            // place queen
            board[row][col] = 'Q';
            cols[col] = 1;
            diag1[row - col + n] = 1;
            diag2[row + col] = 1;
            
            solve(row + 1, n, board, res, cols, diag1, diag2);
            
            // backtrack
            board[row][col] = '.';
            cols[col] = 0;
            diag1[row - col + n] = 0;
            diag2[row + col] = 0;
        }
    }
};