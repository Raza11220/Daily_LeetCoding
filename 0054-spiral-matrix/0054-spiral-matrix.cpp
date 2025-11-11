class Solution {
public:
    vector<int> spiralOrder(vector<vector<int>>& matrix) {
        int m = matrix.size(), n = matrix[0].size(); // Fixed: use matrix instead of mat
        int srow = 0, scol = 0, erow = m-1, ecol = n-1;
        vector<int> ans;

        while(srow <= erow && scol <= ecol) {
            // Top row
            for(int j = scol; j <= ecol; j++) {
                ans.push_back(matrix[srow][j]);
            }

            // Right column
            for(int i = srow + 1; i <= erow; i++) {
                ans.push_back(matrix[i][ecol]);
            }

            // Bottom row (if there's more than one row)
            if (srow < erow) {
                for(int j = ecol - 1; j >= scol; j--) {
                    ans.push_back(matrix[erow][j]);
                }
            }

            // Left column (if there's more than one column)
            if (scol < ecol) {
                for(int i = erow - 1; i > srow; i--) {
                    ans.push_back(matrix[i][scol]);
                }
            }

            srow++;
            erow--;
            scol++;
            ecol--;
        }
        return ans;
    }
};