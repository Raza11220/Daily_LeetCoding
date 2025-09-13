import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        // Use a HashMap to store number and its index
        HashMap<Integer, Integer> map = new HashMap<>();
        
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i]; // number we need to find
            if (map.containsKey(complement)) {
                // Found the pair
                return new int[] { map.get(complement), i };
            }
            map.put(nums[i], i); // store current number and index
        }
        return new int[] {}; // just in case, though problem guarantees a solution
    }

    // Main method to test
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] result1 = sol.twoSum(nums1, target1);
        System.out.println(result1[0] + ", " + result1[1]); // Output: 0, 1

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        int[] result2 = sol.twoSum(nums2, target2);
        System.out.println(result2[0] + ", " + result2[1]); // Output: 1, 2

        int[] nums3 = {3, 3};
        int target3 = 6;
        int[] result3 = sol.twoSum(nums3, target3);
        System.out.println(result3[0] + ", " + result3[1]); // Output: 0, 1
    }
}

