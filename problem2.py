# problem 2 

class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        result = 0 
        st = []
        st.append(-1)

        for i in range(n):
            while st[-1] != -1 and heights[st[-1]] > heights[i]:
                popped_element = st.pop()
                width = i - st[-1] -1
                result = max(result,heights[popped_element]*width)
            st.append(i)

        while st[-1] != -1:
            popped_element = st.pop()
            width = n-st[-1]-1
            result = max(result,heights[popped_element]*width)
        return result