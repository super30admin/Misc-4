# Problem 2 : Largest Rectangle in Histogram
# Time Complexity : O(n) where n is the length of the heights list
# Space Complexity : O(n) where n is the length of the heights list
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None

# Your code here along with comments explaining your approach
from typing import List
class Solution:
    def largestRectangleArea(self, heights: List[int]) -> int:
        # define result variable to store the value of maximum area of rectangle
        result = 0
        # get the length of the heights list
        length = len(heights)
        # define montonic stack and append -1 to stack
        stack = [-1]

        # loop through heights list
        for i in range(length):
            # loop until the top element of stack is -1 and value of height at index (which is top of stack) is greater than the height at ith position
            while stack[-1] != -1 and heights[stack[-1]] > heights[i]:
                # if it is then pop the top index from the stack
                popped = stack.pop()
                # get the width as i - top element of the stack - 1
                width = i - stack[-1] - 1
                # get height from the height list at popped position
                height = heights[popped]
                # get the maximum between result area and (width * height)
                result = max(result, width * height)
            # append the i index in the stack
            stack.append(i)
        
        # loop till stack contians -1
        while stack[-1] != -1:
            # poped the top index from the stack
            popped = stack.pop()
            # get the width as length - top element of the stack - 1
            width = length - stack[-1] - 1
            # get height from the height list at popped position
            height = heights[popped]
            # get the maximum between result area and (width * height)
            result = max(result, width * height)
        # return result
        return result
