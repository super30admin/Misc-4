# problem 1 

class TopVotedCandidate:

    def __init__(self, persons: List[int], times: List[int]):
        self.count_map = {}
        self.leader_map = {}
        curr_leader = persons[0]
        self.persons = persons 
        self.times = times
        self.count_map[curr_leader] = 1
        self.leader_map[times[0]] = curr_leader

        for i in range(1,len(persons)):
            person = persons[i]
            time = times[i]

            prevtime = times[i-1]
            prevleader = curr_leader

            for j in range(prevtime+1,time):
                self.leader_map[j] = prevleader

            self.count_map[person] = self.count_map.get(person,0)+1
            if self.count_map[person] >= self.count_map[curr_leader]:
                curr_leader=person 
            self.leader_map[time]= curr_leader

            

    def q(self, t: int) -> int:
        if t in self.leader_map:
            return self.leader_map[t]
        return self.leader_map[self.times[-1]]
        # perform bst


# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)