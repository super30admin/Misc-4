// In this problem, maintaining two hashmaps, one for storing number of times that person has occured until now, and then another 
// hashmap for storing the leader at current time, so whichever person in the person map has greater count, will become the leader.
// We will keep one leader variable, initially leader is first person, and then going forward we will check the count of the current
// person and the leader person, whichever greater will be the leader. And store that leader as the leader with current time as the
// key.

// Time Complexity : O(nlogn)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class TopVotedCandidate {
    HashMap<Integer, Integer> personsMap;
    HashMap<Integer, Integer> leaderMap;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        // Base case
        if (persons == null || persons.length == 0) {
            return;
        }
        this.times = times;
        // Two maps
        personsMap = new HashMap<>();
        leaderMap = new HashMap<>();
        // Take leader as first person initially
        int leader = persons[0];
        // Loop over and fill both the maps
        for (int i = 0; i < persons.length; i++) {
            // Take the current person
            int p = persons[i];
            // Put the count or increase the count of current person in the map
            personsMap.put(p, personsMap.getOrDefault(p, 0) + 1);
            // Compare if current person is having frequency greater or equal to leader
            if (personsMap.get(p) >= personsMap.get(leader)) {
                // Make current person as the leader
                leader = p;
            }
            // Put in leader map, the current time and leader at this time
            leaderMap.put(times[i], leader);
        }

    }

    public int q(int t) {
        // Check if the time for which we have to find the leader, if it is directly
        // present in the leader map then return the leader
        if (leaderMap.containsKey(t)) {
            return leaderMap.get(t);
        } else {
            // Else do a binary search for a time which is just lower then the asked
            // time(t). Eg. if in map we have time 1 and 5, and t=3, the leader at time 3
            // would be same as leader at time 1, becoz new leader was changed at time 5. So
            // we need to find 1.
            int low = 0;
            int high = times.length - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (times[mid] > t) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            // high will be at correct index in times, after we finish binary search.
            return leaderMap.get(times[high]);
        }
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */