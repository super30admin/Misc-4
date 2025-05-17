

//TC: O(n)
//SC: O(n)
class LargestRectHist {
  public int largestRectangleArea(int[] heights) {
      int max=0;
      int n=heights.length;
      Stack<Integer> stk=new Stack<>();
      //left boundry for 0th index
      stk.push(-1);
      for(int i=0;i<n;i++){
          int curr=heights[i];
          while(stk.peek()!=-1 && curr<heights[stk.peek()]){
              //resolve stk.peek because the rectangle of that ele has stopped extending on right
              int popped=stk.pop();
              int w = i-stk.peek()-1;
              int h = heights[popped];
              max=Math.max(max,w*h);
          }
          stk.push(i);
      }
      //after we have iterated over the heights array
      while(stk.peek()!=-1){
              //resolve stk.peek because the rectangle of that ele has stopped extending on right
              int popped=stk.pop();
              int w = n-stk.peek()-1;
              int h = heights[popped];
              max=Math.max(max,w*h);
      }
      return max;
  }
}