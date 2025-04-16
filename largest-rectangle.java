// TC: O(n^2)
// SC: O(1)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

public class Solution {

    public int calculateArea(int[] heights, int start, int end) {
        if (start > end)
            return 0;

        int minIndex = start;
        for (int i = start; i <= end; i++)
            if (heights[minIndex] > heights[i])
                minIndex = i;

        int areaWithMin = heights[minIndex] * (end - start + 1);
        int areaLeft = calculateArea(heights, start, minIndex - 1);
        int areaRight = calculateArea(heights, minIndex + 1, end);

        return Math.max(areaWithMin, Math.max(areaLeft, areaRight));
    }

    public int largestRectangleArea(int[] heights) {
        return calculateArea(heights, 0, heights.length - 1);
    }
}
