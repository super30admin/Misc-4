from typing import List
class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.persons = persons
        self.times = times
        self.countmap = {}
        self.leadermap = {}
        self.leader = 0
        for i in range(len(persons)):
            person = persons[i]
            time = times[i]
            self.countmap[person] = self.countmap.get(person,0)+1
            if self.countmap[person] >= self.countmap.get(self.leader,0):
                self.leader = person
            self.leadermap[time] = self.leader

    def q(self, t: int) -> int:
        if t in self.leadermap: return self.leadermap[t]
        low,high = 0,len(self.times)-1
        while low<=high:
            mid = low + (high-low)//2
            if self.times[mid]>t:
                high = mid-1
            else:
                low = mid+1
        return self.leadermap[self.times[high]]
        


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)