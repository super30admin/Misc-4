//TC: O(n): each index is pushed and popped at most once; O(1) work per operation.
//SC: O(n): stack stores up to n indices in the worst case.

//Use a mono-increasing stack of indices with sentinel -1.
//Scan left→right: while current height < height[stack.top], pop h and compute area = h * (i - stack.peek() - 1); then push i.
//After the scan, pop remaining indices and compute areas with width = n - stack.peek() - 1; track the maximum.

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int result = 0;

        Stack<Integer> st = new Stack<>();
        st.push(-1);

        for(int i=0; i<n; i++){

            while(st.peek() != -1 && heights[st.peek()] > heights[i]){
                int popped = st.pop();
                int width = i - st.peek() -1;

                result = Math.max(result, heights[popped] * width);
            }
            
            st.push(i);
        }

        while(st.peek() != -1){
            int popped = st.pop();
            int width = n - st.peek() -1;

            result = Math.max(result, heights[popped] * width);
        }

        return result;
    }
}
