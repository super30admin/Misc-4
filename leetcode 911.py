"""
Time Complexity: O(n) + O(log n)
Space Complexity: O(n)
"""
class TopVotedCandidate:
    def __init__(self, persons, times):
        self.persons = persons
        self.times = times
        self.countMap = {}
        self.leaderMap = {}
        leader = 0
        for i in range(len(persons)):
            person = persons[i]
            time = times[i]

            self.countMap[person] = self.countMap.get(person, 0) + 1

            if self.countMap[person] >= self.countMap.get(leader, 0):
                leader = person
            self.leaderMap[time] = leader

    def q(self, t):
        if t in self.leaderMap:
            return self.leaderMap[t]

        low, high = 0, len(self.times) - 1

        while low <= high:
            mid = low + (high - low) // 2

            if self.times[mid] > t:
                high = mid - 1
            else:
                low = mid + 1

        return self.leaderMap[self.times[high]]     


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)