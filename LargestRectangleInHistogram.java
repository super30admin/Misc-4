// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We store heights in an increasing order in the stack until we find a greater height than the stack's peek.
Then, we continue to pop the stack and calculate max area formed using the popped heights. At the end, we
do the same by iterating through the remianing heights of the stack and return max area found so far.
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int maxArea = 0;

        for(int i = 0 ; i < n ; i++) {
            while(st.peek() != -1 && heights[st.peek()] > heights[i]) {
                int length = heights[st.pop()];
                int width = i - st.peek() - 1;
                maxArea = Math.max(maxArea, length * width);
            }
            st.push(i);
        }

        while(st.peek() != -1) {
            int length = heights[st.pop()];
            int width = n - st.peek() - 1;
            maxArea = Math.max(maxArea, length * width);
        }
        return maxArea;
    }
}