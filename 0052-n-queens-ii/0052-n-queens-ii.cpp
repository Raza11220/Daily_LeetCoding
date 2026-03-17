class Solution {
public:
    int totalNQueens(int n) {
        int count = 0;
        
        vector<int> cols(n, 0);
        vector<int> diag1(2*n, 0); // r - c + n
        vector<int> diag2(2*n, 0); // r + c
        
        solve(0, n, count, cols, diag1, diag2);
        return count;
    }
    
    void solve(int row, int n, int &count,
               vector<int>& cols,
               vector<int>& diag1,
               vector<int>& diag2) {
        
        if (row == n) {
            count++;
            return;
        }
        
        for (int col = 0; col < n; col++) {
            if (cols[col] || diag1[row - col + n] || diag2[row + col])
                continue;
            
            // place queen
            cols[col] = 1;
            diag1[row - col + n] = 1;
            diag2[row + col] = 1;
            
            solve(row + 1, n, count, cols, diag1, diag2);
            
            // backtrack
            cols[col] = 0;
            diag1[row - col + n] = 0;
            diag2[row + col] = 0;
        }
    }
};