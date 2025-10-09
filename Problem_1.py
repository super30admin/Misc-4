'''
911 Online Election
https://leetcode.com/problems/online-election/description/

You are given two integer arrays persons and times. In an election, the ith vote was cast for persons[i] at time times[i].

For each query at a time t, find the person that was leading the election at time t. Votes cast at time t will count towards our query. In the case of a tie, the most recent vote (among tied candidates) wins.

Implement the TopVotedCandidate class:
TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned rules.


Example 1:
Input
["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
[[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
Output
[null, 0, 1, 1, 0, 0, 1]
Explanation
TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
topVotedCandidate.q(15); // return 0
topVotedCandidate.q(24); // return 0
topVotedCandidate.q(8); // return 1

Constraints:
1 <= persons.length <= 5000
times.length == persons.length
0 <= persons[i] < persons.length
0 <= times[i] <= 10^9
times is sorted in a strictly increasing order.
times[0] <= t <= 10^9
At most 10^4 calls will be made to q.

Solution:
1. Hash map + binary search
We count votes for each person using a count_votes hash map <person id: num votes>.

We maintain another hash map leader_map <time: winning person> to track the leader at each time given in the times[] array.

During a query (who is winning at time t?), we perform binary search on the keys of leader_map to find the leader.

https://youtu.be/Ozr1wl_rPAY?t=313

            Time        Space
__init__:   O(N)         O(N)
q():        log(N)       O(1)
'''
from typing import List
from collections import defaultdict

class TopVotedCandidate:
    def __init__(self, persons: List[int], times: List[int]):
        self.count_votes = defaultdict(int) # <person id: num votes>
        self.leader_map = defaultdict(int) # <time: person id>
        self.times = times
        if not persons:
            return
        N = len(persons)
        leader = persons[0]
        for i in range(N):
            person = persons[i]
            self.count_votes[person] += 1
            if self.count_votes[person] >= self.count_votes[leader]:
                leader = person
            self.leader_map[times[i]] = leader

    def q(self, t: int) -> int:
        if t in self.leader_map:
            return self.leader_map[t]

        low = 0
        high = len(self.times) - 1
        while low <= high:
            mid = low + (high - low) // 2
            if self.times[mid] > t:
                high  = mid -1
            else:
                low = mid + 1
        return self.leader_map[self.times[high]]

def run_TopVotedCandidate():
    tests = [ (["TopVotedCandidate", "q", "q", "q", "q", "q", "q"],
                [ [[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8] ],
                [None, 0, 1, 1, 0, 0, 1]),
    ]
    for test in tests:
        operations, inputs, ans = test[0], test[1], test[2]
        outputs = []
        for operation, input in zip(operations, inputs):
            if operation == "TopVotedCandidate":
                sol = TopVotedCandidate(input[0], input[1])
                output = None
            elif operation == "q":
                output = sol.q(input[0])
            print(f"{operation}({input}): {output}")
            outputs.append(output)

        print(f"\nOperations = {operations}")
        print(f"inputs = {inputs}")
        print(f"outputs = {outputs}")
        success = (ans==outputs)
        print(f"Pass: {success}")
        if not success:
            print("Failed")
            return

run_TopVotedCandidate()