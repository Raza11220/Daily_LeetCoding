class Solution {
public:
    int slidingPuzzle(vector<vector<int>>& board) {
        
        string start = "";
        for (auto &row : board) {
            for (int x : row) {
                start += to_string(x);
            }
        }

        string target = "123450";

        // Neighbor positions for each index
        vector<vector<int>> moves = {
            {1,3},    // 0
            {0,2,4},  // 1
            {1,5},    // 2
            {0,4},    // 3
            {1,3,5},  // 4
            {2,4}     // 5
        };

        queue<string> q;
        unordered_set<string> visited;

        q.push(start);
        visited.insert(start);

        int steps = 0;

        while (!q.empty()) {
            int size = q.size();

            while (size--) {
                string curr = q.front();
                q.pop();

                if (curr == target)
                    return steps;

                int zero = curr.find('0');

                for (int next : moves[zero]) {
                    string temp = curr;
                    swap(temp[zero], temp[next]);

                    if (!visited.count(temp)) {
                        visited.insert(temp);
                        q.push(temp);
                    }
                }
            }

            steps++;
        }

        return -1;
    }
};