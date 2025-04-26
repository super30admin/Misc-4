class TopVotedCandidate:
    # T: O(n), S: O(n)
    def __init__(self, persons, times):
        self.times = times
        self.leaders = []
        vote_counts = defaultdict(int)
        leader = -1
        max_votes = 0

        for person in persons:
            vote_counts[person] += 1
            if vote_counts[person] >= max_votes:
                if person != leader:
                    leader = person
                    max_votes = vote_counts[person]
            self.leaders.append(leader)

    # T: O(log n), S: O(1)
    def q(self, t):
        idx = bisect_right(self.times, t) - 1
        return self.leaders[idx]


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)
