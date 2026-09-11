class Solution {
    public int maxProduct(int[] nums) {
        int smax = -1;
        int max = -1;
        for(int i=0;i<nums.length;i++){
            if(max < nums[i]){
                smax = max;
                max = nums[i];
            }
            else if(smax < nums[i]){
                smax = nums[i]; 
            }
        }
        int ans = (smax-1) * (max-1);

        return ans;
    }
}