// This algorithm uses a stack based approach and iterating twice over the heights array
// For the first iteration we resolve all the heights whenever a height goes down
// We keep adding the heights as long as its ascending order and when we encounter lesser height, we evaluate the area based popping previous ones and increasing the width
// In the reverse iteration, we do it similar way
class Solution {
    public int largestRectangleArea(int[] heights) {
        int max = 0;
        Stack<Integer> stack = new Stack();
        stack.push(-1);
        for(int i=0;i<heights.length;i++) {
            while(stack.peek()!=-1 && heights[stack.peek()]>heights[i]) {
                int present = stack.pop();
                int width = i-stack.peek()-1;
                max = Math.max(max, width*(heights[present]));
            }
            stack.push(i);
        }

        int n = heights.length;
        while(stack.peek()!=-1) {
            int present = stack.pop();
            int width = n-stack.peek()-1;
            max = Math.max(max, width*heights[present]);
        }
        return max;
    }
}
