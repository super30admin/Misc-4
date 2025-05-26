// tc:O(n) sc:O(n)

import java.util.*;
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights ==null || heights.length ==0){
            return 0;
        }
        int max =0;
        Stack<Integer> s = new Stack<>();
        s.push(-1);
        int i=0;

        while(i < heights.length){
            if(s.peek() == -1 || heights[i] >= heights[s.peek()]){
                s.push(i);
                i++;
            }
            else{
                int popped = s.pop();
                max = Math.max(max, heights[popped] * (i-s.peek() -1));
            }

        }

        while(s.size() >1){
            int popped = s.pop();
            max = Math.max(max, heights[popped] * (i-s.peek() -1));
        }
        return max;
    }
}