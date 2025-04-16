class TopVotedCandidate:
    # hashmap and binary search
    # TC : O(log n)
    # SC : O(n) for 2 hashmaps

    def __init__(self, persons: List[int], times: List[int]):
        self.countvotes = defaultdict(int)
        self.leaderMap = defaultdict(int)
        self.times = times
        if persons is None:
            return
        leader = persons[0]
        for i in range(len(persons)):
            person = persons[i]
            self.countvotes[person] += 1
            if self.countvotes[person] >= self.countvotes[leader]:
                leader = person
            self.leaderMap[times[i]] = leader
        

    def q(self, t: int) -> int:
        if t in self.leaderMap.keys():
            return self.leaderMap[t]
        low,high = 0, len(self.times)-1
        while low <= high:
            mid = low + (high-low)//2
            if t > self.times[mid]:
                low = mid +1
            else:
                high = mid - 1
        return self.leaderMap[self.times[high]]
        


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)