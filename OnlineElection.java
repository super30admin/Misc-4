// Time Complexity : O(n) for constructor, O(logn) for q method
// Space Complexity : O(2n) ~ O(n) for the maps
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We count votes for each member and maintain a map for it.Initially we consider member 0 as leader. Whenever,
we find the number of votes of incoming member is greater than or equal to the leader's votes, we update the
leader value and at each iteration, maintain a mapping of time to leader. Whenever q method is called, if we
have an entry for the input time, we return it using leader map, if not, we do binary search to identify the
closest earlier or equal time to that.
 */
class TopVotedCandidate {
    Map<Integer, Integer> leaderMap;
    Map<Integer, Integer> countMap;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        this.leaderMap = new HashMap<>();
        this.countMap = new HashMap<>();
        int leader = 0;

        for(int i = 0 ; i < persons.length ; i++) {
            int person = persons[i];
            int time = times[i];
            countMap.put(person, countMap.getOrDefault(person, 0) + 1);
            if(countMap.get(person) >= countMap.get(leader))
                leader = person;
            leaderMap.put(time, leader);
        }
    }

    public int q(int t) {
        if(leaderMap.containsKey(t))
            return leaderMap.get(t);

        int low = 0 , high = times.length - 1;

        while(low <= high) {
            int mid = low + (high - low) / 2;
            if(times[mid] > t)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return leaderMap.get(times[high]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
