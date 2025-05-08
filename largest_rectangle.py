# Time Complexity : O(n)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : YES

# Any problem you faced while coding this : NO

class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        stack = []
        max_area = -math.inf

        for ix, height in enumerate(heights):
            cur_pos = ix
            while stack and height < stack[-1][0]:
                prev_height, index = stack.pop()
                cur_area = prev_height * (ix - index)
                max_area = max(max_area, cur_area)
                cur_pos = index
            stack.append((height, cur_pos))
        
        cur_pos = len(heights)
        while stack:
            prev_height, index = stack.pop()
            cur_area = prev_height * (cur_pos - index)
            max_area = max(max_area, cur_area)

        return max_area