public class Solution {
    // TC => O(n)
    // SC => O(n)
    public int LargestRectangleArea(int[] heights){
         if(heights == null || heights.Length == 0){
            return 0;
        }

        int max = 0;
        Stack<int> stack = new Stack<int>();
        stack.Push(-1);
        int i = 0;
        while(i < heights.Length){
            if(stack.Peek() == -1 || heights[i] >= heights[stack.Peek()]){
                stack.Push(i);
            }
            else{
                while(stack.Count > 1 && heights[i] < heights[stack.Peek()]){
                    int height = heights[stack.Pop()];
                    int width = i - stack.Peek() - 1;
                    max = Math.Max(max, (height * width));
                }
                
                stack.Push(i);
            }
            i++;
        }

        while(stack.Count > 1){
            int height = heights[stack.Pop()];
            int width = i - stack.Peek() - 1;
            max = Math.Max(max, (height * width));
        }
        return max;
    }
    // TC => O(n2)
    // SC => O(1)
    public int LargestRectangleArea1(int[] heights) {
        if(heights == null || heights.Length == 0){
            return 0;
        }

        int max = 0;
        for(int i = 0; i< heights.Length; i++){
            int min = heights[i];
            for(int j = i; j < heights.Length;j++){
                min = Math.Min(min, heights[j]);
                max = Math.Max(max, min * (j - i + 1));
            }
        }
        return max;
    }
}