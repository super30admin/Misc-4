// Time Complexity : O(n) + log(n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Approach : We maintain a leaderMap to retrive the leader at a specific timestamp. But to capture that we maintain one more frequency map
// that calculates the current leader. Based on that, we add the value to the leaderMap. For the query, if the timestamp is in the leaderMap's
// key, we return it's value as the leader, else we perform a binary search on the times array and get the leader.

class TopVotedCandidate {
    HashMap<Integer, Integer> leaderMap = new HashMap<>();//leader map to get the leader at a specific time
    int leader;
    int[] persons;
    int[] times;
    public TopVotedCandidate(int[] persons, int[] times) {
        HashMap<Integer, Integer> map = new HashMap<>(); //hashmap to find leader at current time
        this.persons = persons;
        this.times = times;
        for(int i = 0; i<persons.length;i++){
            int person = persons[i];
            map.put(person, map.getOrDefault(person,0)+1);
            if(map.get(person) >= map.get(leader)){ //update the leader
                this.leader = person;
            }
            leaderMap.put(times[i], leader);//update the leader map
        }
    }

    public int q(int t) {
        if(leaderMap.containsKey(t)){
            return leaderMap.get(t);
        }
        int low = 0;
        int high = persons.length-1;
        while(low <= high){ //binary search to find the leader for time t
            int mid = low + (high-low)/2;
            if(t < times[mid]){
                high = mid-1;
            } else{
                low = mid+1;
            }
        }
        return leaderMap.get(times[high]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */