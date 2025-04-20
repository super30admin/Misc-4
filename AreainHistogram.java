// In this bruteforce approach, trying to check subarray of all lengths starting from all positions, calculating the area and keeping the max.
// It is giving time limit exceeded.

// Time Complexity : O(n^2)
// Space Complexity : O(1)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int largestRectangleArea(int[] heights) {
        // Base case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // Take the max area as 0 initially
        int area = 0;
        // Loop
        for (int i = 0; i < heights.length; i++) {
            // The height of any combination of bars(to make rectangle) is given by the
            // height of smallest bar
            int height = heights[i];
            for (int j = i; j < heights.length; j++) {
                // So take min
                height = Math.min(height, heights[j]);
                // Area is given the by height * width
                area = Math.max(area, height * (j - i + 1));
            }
        }
        // Return area
        return area;
    }
}

// In this optimized approach, we are using the bucket sort. The intuition here
// is that when the bar height is increasing, you know the minimum
// which gives us the height of the rectangle will be same always and the width
// keeps on increasing, so the area will also increase. But if a
// bar with height shorter than previous bar comes than there is a possibility
// that it's is even shorter than our minimum value, so area might
// decrease. So, we store the indexes in stack if they are of more height than
// whaterver on top of stack. For a bar with shorter height, before
// putting that in stack, we process i.e we compute the area for previous bars,
// and then continue.

// Time Complexity : O(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no
class Solution {
    public int largestRectangleArea(int[] heights) {
        // Base case
        if (heights == null || heights.length == 0) {
            return 0;
        }
        // Area and monotonic stack
        int area = 0;
        Stack<Integer> s = new Stack<>();
        // Push -1 for the initial comparison
        s.push(-1);
        int i = 0;
        // Loop
        while (i < heights.length) {
            // If increasing order, put in stack
            if (s.peek() == -1 || heights[i] >= heights[s.peek()]) {
                s.push(i);
                // And move i
                i++;
            } else {
                // Else compute and dont move i becoz, the element after the popped element, we
                // might also need to process that is the current is still shorter than whatever
                // on top
                area = Math.max(area, heights[s.pop()] * (i - s.peek() - 1));
            }

        }
        // At end process all left in stack
        while (s.size() > 1) {
            area = Math.max(area, heights[s.pop()] * (i - s.peek() - 1));
        }
        return area;
    }
}