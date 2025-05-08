# Time Complexity : O(logn)
# Space Complexity : O(n)
# Did this code successfully run on Leetcode : YES

# Any problem you faced while coding this : NO

class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.times = times
        self.leader_map = {} # time --> current leader
        self.vote_map = {} # person --> # of votes
        curr_leader = persons[0]
        for i in range(len(times)):
            self.vote_map[persons[i]] = 1 + self.vote_map.get(persons[i], 0)
            if self.vote_map[persons[i]] >= self.vote_map[curr_leader]:
                curr_leader = persons[i]
            self.leader_map[times[i]] = curr_leader

    def q(self, t: int) -> int:
        if t in self.leader_map:
            return self.leader_map[t]
        
        closest_time = self.bin_search(t)
        print(closest_time)
        return self.leader_map[closest_time]
    
    def bin_search(self, t):
        l,r = 0,len(self.times)-1
        while l < r:
            mid = l+(r-l)//2
            if self.times[mid] < t:
                l = mid + 1
            else:
                r = mid - 1
        
        if self.times[l] > t:
            return self.times[l-1]
        
        return self.times[l]

# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)