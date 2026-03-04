public class problem2 {
    // Time Complexity: O(n) for longestOnes method
    // Space Complexity: O(1) for longestOnes method
 
    public int longestOnes(int[] nums, int k)
    {
        int n= nums.length;
        int slow=0;
        int max=0;
        for(int i=0;i<n;i++)
        {
            if(nums[i]==0)
            {
                k--;
            }
            if(k<0)
            {
                while(nums[slow]!=0)
                {
                    slow++;
                }
                slow++;
                k++;
            }
            max= Math.max(max, i-slow+1);
        }
        return max;
    }
}
