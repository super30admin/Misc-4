# Time Complexity: O(log N)
# Space Complexity: O(N)

class TopVotedCandidate:
    def __init__(self, persons: List[int], times: List[int]):
        self.times = times 
        self.leaders = []
        vote_count = {}
        leader = -1
        for i, person in enumerate(persons):
            vote_count[person] = vote_count.get(person, 0) + 1 
            if leader == -1 or vote_count[person] >= vote_count[leader]:
                leader = person

            self.leaders.append(leader)

    def q(self, t: int) -> int:
        index = bisect.bisect_right(self.times, t) - 1
        return self.leaders[index]