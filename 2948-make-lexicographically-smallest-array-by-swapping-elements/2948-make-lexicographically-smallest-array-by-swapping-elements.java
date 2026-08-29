import java.util.*;

class Solution {

    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> a[0] - b[0]);

        int[] ans = new int[n];

        int i = 0;

        while (i < n) {

            List<Integer> indices = new ArrayList<>();
            List<Integer> values = new ArrayList<>();

            indices.add(arr[i][1]);
            values.add(arr[i][0]);

            int j = i + 1;

            while (j < n && arr[j][0] - arr[j - 1][0] <= limit) {
                indices.add(arr[j][1]);
                values.add(arr[j][0]);
                j++;
            }

            Collections.sort(indices);

            for (int k = 0; k < indices.size(); k++) {
                ans[indices.get(k)] = values.get(k);
            }

            i = j;
        }

        return ans;
    }
}