import java.util.Stack;

public class LargestAreaRectangleInHistogram {
 //using monotonic stack.
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

/*
 // brute force TC-O(N^2) SC-O(1) -can get TLE(time limit exceeded)
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

        return area;
    }


 */