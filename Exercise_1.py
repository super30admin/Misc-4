# S30 Problem #209 Online election
#LeetCode #911 https://leetcode.com/problems/online-election/description/

# Author : Akaash Trivedi
# Did this code successfully run on Leetcode :  Yes #
# Any problem you faced while coding this : No


# creater a count map for each persons vote
# make a leaders map of to count the leader at time a new vote comes in
# if time is not in leadersmap then use binary search to find the previous time vote was counted
# time complexity: O(n  + log n) log n to process query time, n for preprocessing maps
# space complexity: O(n) count and leader map

class TopVotedCandidate:
    def __init__(self, persons: List[int], times: List[int]):
        self.leadermap = {}
        self.countmap = {}
        self.leader = 0
        self.times = times

        for i in range(len(persons)):
            curr = persons[i]
            self.countmap[curr] = self.countmap.get(curr, 0) + 1
            if self.countmap[curr] >= self.countmap[self.leader]:
                self.leader = curr
            self.leadermap[times[i]] = self.leader

    def q(self, t: int) -> int:
        if t in self.leadermap:
            return self.leadermap[t]
        else:
            nearestPrevTime = self.binarySearch(self.times, t)
            return self.leadermap[nearestPrevTime]

    def binarySearch(self, times, tgt):
        low = 0
        high = len(times) - 1
        while low <= high:
            mid = low + (high - low) // 2
            if times[mid] <= tgt:
                # target time is greater than mid
                low = mid + 1
            else:
                # target time is less then mid
                high = mid - 1
        return times[high]

# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)