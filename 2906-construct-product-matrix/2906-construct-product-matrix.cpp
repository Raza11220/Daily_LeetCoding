class Solution {
public:
    vector<vector<int>> constructProductMatrix(vector<vector<int>>& grid) {
        int n = grid.size();
        int m = grid[0].size();
        int MOD = 12345;
        
        int size = n * m;
        vector<int> arr;
        
        // Flatten grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr.push_back(grid[i][j] % MOD);
            }
        }
        
        // Prefix product
        vector<int> prefix(size, 1);
        prefix[0] = arr[0];
        for (int i = 1; i < size; i++) {
            prefix[i] = (prefix[i - 1] * arr[i]) % MOD;
        }
        
        // Suffix product
        vector<int> suffix(size, 1);
        suffix[size - 1] = arr[size - 1];
        for (int i = size - 2; i >= 0; i--) {
            suffix[i] = (suffix[i + 1] * arr[i]) % MOD;
        }
        
        // Result
        vector<vector<int>> res(n, vector<int>(m));
        
        for (int i = 0; i < size; i++) {
            int left = (i == 0) ? 1 : prefix[i - 1];
            int right = (i == size - 1) ? 1 : suffix[i + 1];
            
            int val = (1LL * left * right) % MOD;
            
            res[i / m][i % m] = val;
        }
        
        return res;
    }
};