# Problem 1 : Online Election
# Time Complexity :
'''
__init__ - O(n) where n is the number of votes
q(t) - O(og n) where n is the number of votes
'''
# Space Complexity :
'''
__init__ - O(n) where n is the number of votes
q(t) - O(1)
'''
# Did this code successfully run on Leetcode : Yes
# Any problem you faced while coding this : None

# Your code here along with comments explaining your approach
from typing import List

class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.time = times
        # define hash map leaderMap which will store time as key and leader as value for that time
        self.leaderMap = {}
        # define hash map countMap which will store person as key and number of votes as value
        self.countMap = {}
        # define variable for storing the leader 
        leader = 0
        # loop through persons list
        for i in range(len(persons)):
            # get the value of persons and times list at ith position
            p = persons[i]
            t = times[i]
            # update the value of countMap ie number of votes for the p person
            self.countMap[p] = self.countMap.get(p, 0) + 1
            # compare the votes of p person with the votes of the leader if it is greater then set leader as p
            if self.countMap[p] >= self.countMap.get(leader, 0):
                leader = p
            # set leader as value for t key in leaderMap
            self.leaderMap[t] = leader

    def q(self, t: int) -> int:
        # check if time t is present in leaderMap
        if t in self.leaderMap:
            # if it is then return value of t 
            return self.leaderMap[t]
        # set lwo to 0 and high to length of time list
        low = 0
        high = len(self.time) - 1
        # loop till low <= high
        while low <= high:
            # get the middle value
            middle = low + (high - low) // 2
            # check if the value of time list at middle position is greater than t and if it is then set high to middle - 1
            if self.time[middle] > t:
                high = middle - 1
            else:
                # else set low to middle + 1
                low = middle + 1
        # return the value of time at high position in time list of leaderMap
        return self.leaderMap[self.time[high]]

# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
