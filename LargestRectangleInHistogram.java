// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Approach : We maintain a monotonic increasing stack as we need to calculate the area of the rectangle when a lesser height comes than the
// current peek element of the stack. At this point, we calculate the area until we pop all the elements that are greater than the incoming
// one, which is the behaviour of monotonic stack. We push -1 to the stack intially so that we keep track of the first element and capture
// the maximum area whenever we calculate the area.

class Solution {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> st = new Stack<>(); //monotonic increasing stack
        int result = 0;
        int max = 0;
        int n = heights.length;
        st.push(-1);
        for (int i = 0; i < n; i++) {
            while (st.peek() != -1 && heights[i] < heights[st.peek()]) { //capture the max height while traversing the array
                int height = heights[st.pop()];
                result = height * (i - st.peek() - 1); //height*width
                max = Math.max(result, max);
            }
            st.push(i);

        }
        while (st.peek() != -1) { //capture the max height with heights left in the stack
            int height = heights[st.pop()];
            result = height * (n - st.peek() - 1);  //height*width
            max = Math.max(result, max);
        }

        return max;
    }
}