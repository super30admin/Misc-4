//TC: Build O(n + R): for each vote plus filling all gaps R = Σ(max(0, times[i]-times[i-1]-1)) ≈ times[n-1]-times[0]+1; q(t) is O(1) average via HashMap.
//SC: O(R + u): leaderMap stores a leader for each time unit in the covered range (R); countMap holds up to u unique persons (u ≤ n).

//Iterate votes in time order: track counts, keep current leader (break ties with >=), and write leader at each timestamp; for gaps, fill every intervening time with the previous leader.
//After updating counts, if this person’s count >= leader’s, update leader and record it at the current time.
//Query: return leaderMap[t] if present; otherwise return the leader at the last recorded time (assumes t ≥ times[n-1]).

class TopVotedCandidate {
    int[] persons;
    int[] times;
    HashMap<Integer, Integer> countMap;
    HashMap<Integer, Integer> leaderMap;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.countMap = new HashMap<>();
        this.leaderMap = new HashMap<>();
        this.persons = persons;
        this.times = times;

        int leader = persons[0];
        countMap.put(leader, 1);
        leaderMap.put(times[0], leader);

        for (int i = 1; i < persons.length; i++) {
            int person = persons[i];
            int time = times[i];

            int prev = times[i - 1];
            int oldLeader = leader;
            
            for (int j = prev + 1; j < time; j++) {           
                leaderMap.put(j, oldLeader);
            }

            countMap.put(person, countMap.getOrDefault(person, 0) + 1);
            if (countMap.get(person) >= countMap.get(leader)) {
                leader = person;                               
            }
            leaderMap.put(time, leader);                      
        }
    }

    public int q(int t) {
        if (leaderMap.containsKey(t)) return leaderMap.get(t);
        return leaderMap.get(times[times.length - 1]);         
    }
}
