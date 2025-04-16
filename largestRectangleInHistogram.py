class Solution:
    # TC : O(n)
    # SC : O(n)
    def largestRectangleArea(self, heights: List[int]) -> int:
        maxarea = 0
        st = []
        for i in range(len(heights)+1):
            while st and (i==len(heights) or heights[st[-1]] > heights[i]):
                h = heights[st.pop()]
                w = i - st[-1]-1 if st else i
                maxarea = max(maxarea,h*w)
            st.append(i)
        return maxarea