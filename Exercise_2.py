# S30 Problem #210 Largest rectangle in Histogram
#LeetCode #84 https://leetcode.com/problems/largest-rectangle-in-histogram/description/

# Author : Akaash Trivedi

# Did this code successfully run on Leetcode :  Yes #
# Any problem you faced while coding this : No

# brute force - Time Limit Exceeded
# take current index height and go left and right till height are equal or greater
# TC O(n^2)
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        mx = 0
        for i in range(n):
            l = i
            r = i
            while l >= 0 and heights[l] >= heights[i]:
                l -= 1
            l += 1
            while r < n and heights[r] >= heights[i]:
                r += 1
            r -= 1
            curr = heights[i] * (r - l + 1)
            mx = max(mx, curr)

        return mx

# monotonic stack - heights strictly increasing
# as height increases the rectangle increases
# as height decrease the last in element has to be resolved
# Time Complexity : O(2n)
# Space Complexity : O(n)

class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        n = len(heights)
        st = [-1]  # pushing the indices
        mx = 0

        for i in range(n):
            while st[-1] != -1 and heights[i] < heights[st[-1]]:
                popped = st.pop()
                # height[popped element idx] * (idx of current:to be pushed - st.peek() - width of one height)
                curr = heights[popped] * (i - st[-1] - 1)
                mx = max(mx, curr)
            st.append(i)

        # remaining elements in stack
        while st[-1] != -1:
            popped = st.pop()
            curr = heights[popped] * (n - st[-1] - 1)
            mx = max(mx, curr)

        return mx
