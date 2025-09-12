from typing import List
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        st =[]
        st.append(-1)
        res = 0
        for i in range(n):
            while st[-1]!=-1 and heights[st[-1]] > heights[i]:
                popped = st.pop()
                width = i-st[-1]-1
                res = max(res,heights[popped]*width)
            st.append(i)

        while st[-1]!=-1:
            popped = st.pop()
            width = n-st[-1]-1
            res = max(res,heights[popped]*width)
        return res
        