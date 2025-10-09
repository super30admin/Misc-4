'''
84 Largest Rectangle In Histogram
https://leetcode.com/problems/largest-rectangle-in-histogram/

Given an array of integers heights representing the histogram's bar height where the width of each bar is 1, return the area of the largest rectangle in the histogram.

Example 1:
Input: heights = [2,1,5,6,2,3]
Output: 10
Explanation: The above is a histogram where width of each bar is 1.
The largest rectangle is shown in the red area, which has an area = 10 units.

Example 2:
Input: heights = [2,4]
Output: 4

Constraints:
1 <= heights.length <= 10^5
0 <= heights[i] <= 10^4

Solution:
1. Brute Force
We go over every possible starting index of a rectangle.
Then for each end index, we keep track of the smallest height in that range.
We calculate the area for that window and update the max area if it's bigger.
https://youtu.be/Ozr1wl_rPAY?t=2737
Time: O(N^2), Space: O(1) (TLE error)

2. Monotonic Stack
We go through each bar and keep track of increasing heights using a stack.
When we find a shorter bar, we pop from the stack and calculate the largest area using the popped height. At the end, we do the same for all remaining items in the stack to find the largest rectangle.
https://youtu.be/ysy1o-QEj3k?t=227
https://youtu.be/Ozr1wl_rPAY?t=3293 (lecture solution)
 Time: O(N), Space: O(N)

'''
from typing import List
from collections import defaultdict

def largestRectangleArea_bruteForce(heights: List[int]) -> int:
    ''' Time: O(N^2), Space: O(1) '''
    if not heights:
        return 0
    N = len(heights)
    area = 0
    for i in range(N):
        h = heights[i]
        for j in range(i,N):
            h = min(h, heights[j])
            area= max(area, h*(j-i+1))
    return area

# Done in lecture, more compact, slightly more difficult to understand
# def largestRectangleArea_Stack(heights: List[int]) -> int:
#     ''' Time: O(N), Space: O(N) '''
#     if not heights:
#         return 0
#     N = len(heights)
#     stack = []
#     stack.append(-1)
#     max_area = 0
#     i = 0
#     while i < N:
#         h = heights[i]
#         if stack[-1] == -1 or h > heights[stack[-1]]:
#             stack.append(i)
#             i += 1
#         else:
#             k = stack.pop()
#             this_area = heights[k] * (i - stack[-1] - 1)
#             max_area = max(max_area, this_area)

#     # At this point, set i = N
#     i = N
#     while len(stack) > 1:
#         k = stack.pop()
#         this_area = heights[k] * (i - stack[-1] - 1)
#         max_area = max(max_area, this_area)
#     return max_area

# makes use of O(N) left and right arrays, but this makes it easier to
# understand
def largestRectangleArea_Stack(heights: List[int]) -> int:
    ''' Time: O(N), Space: O(N) '''
    if not heights:
        return 0
    N = len(heights)

    # calculate nearest smaller element to the right of current bar at i
    stack = [] # top of stack preserves the next smaller elem to the right
    right = [0]*N
    for i in range(N-1,-1,-1):
        # pop all elements from stack that are greater than current bar's ht
        while stack and heights[stack[-1]] >= heights[i]:
            stack.pop()
        if stack:
            right[i] = stack[-1]
        else:
            right[i] = -1
        stack.append(i)

    # calculate nearest smaller element to the left of current bar at i
    stack = [] # top of stack preserves the next smaller elem to the left
    left = [0]*N
    for i in range(N):
        # pop all elements from stack that are greater than current bar's ht
        while stack and heights[stack[-1]] >= heights[i]:
            stack.pop()
        if stack:
            left[i] = stack[-1]
        else:
            left[i] = -1
        stack.append(i)

    # calculate largest rectangle
    max_area = 0
    for i in range(N):
        h = heights[i]

        # if right[i] < 0, width (right[i] - left[i] - 1) < 0
        # hence, set right[i] = N which indicates that we stretch the
        # right boundary of the rectangle till the last bar
        if right[i] == -1:
            right[i] = N

        w = right[i] - left[i] - 1
        area = h*w
        max_area = max(max_area, area)
    return max_area

def run_largestRectangleArea():
    tests = [([2,1,5,6,2,3], 10),
             ([2,4], 4),
    ]
    for test in tests:
        heights, ans = test[0], test[1]
        print(f"\nheights = {heights}")
        for method in ['brute-force', 'monotonic-stack']:
            if method == 'brute-force':
                area = largestRectangleArea_bruteForce(heights)
            elif method == 'monotonic-stack':
                area = largestRectangleArea_Stack(heights)
            print(f"Method {method}: max area = {area}")
            success = (ans == area)
            print(f"Pass: {success}")
            if not success:
                print("Failed")
                return

run_largestRectangleArea()