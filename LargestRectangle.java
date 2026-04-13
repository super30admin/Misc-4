// Time Complexity : O(2n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
// Using monotonic increasing stack we resolve all the heights less than currHeight at i
// resolving index at st.pop => height[pop] * ( i(right boundry) - st.peek() (left boundry) -1)
// resolve for the last element seperatly till st is out of indices

class Solution {
    public int largestRectangleArea(int[] heights) {

        int max = 0;
        int n = heights.length;
        ArrayDeque<Integer> st = new ArrayDeque<>();
        st.push(-1);

        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && heights[i] < heights[st.peek()]) {
                int pop = st.pop();
                max = Math.max(max, heights[pop] * (i - st.peek() - 1));
            }
            st.push(i);
        }

        while (st.peek() != -1) {
            int pop = st.pop();
            max = Math.max(max, heights[pop] * (n - st.peek() - 1));
        }

        return max;
    }
}