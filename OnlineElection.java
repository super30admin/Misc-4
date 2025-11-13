// This algorithm first creates a map of winners at given timestamps
// For each time stamp query if it is already available we directly get it from this map
// If its not available we do a binary search on this key set and get the timestamp lesser than given query
class TopVotedCandidate {

    Map<Integer, Integer> winnersAtTimestamp = new LinkedHashMap();
    Map<Integer, Integer> votes = new HashMap();
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        this.times = times;
        int winnerSoFar = persons[0];
        votes.put(persons[0], 1);
        winnersAtTimestamp.put(times[0], winnerSoFar);

        for(int i=1;i<times.length;i++) {
            votes.put(persons[i], votes.getOrDefault(persons[i], 0)+1);
            if(votes.get(persons[i])>=votes.get(winnerSoFar)) {
                winnerSoFar = persons[i];
                winnersAtTimestamp.put(times[i], persons[i]);
            } else {
                winnersAtTimestamp.put(times[i], winnerSoFar);
            }
        }
    }
    
    public int q(int t) {
        if(winnersAtTimestamp.containsKey(t)) return winnersAtTimestamp.get(t);
        int time = getKey(t);
        return winnersAtTimestamp.get(time);
    }

    private int getKey(int t) {
        int i = 0, j = times.length-1;
        while(i<=j) {
            int mid = i+(j-i)/2;
            if(times[mid]<t) {
                i=mid+1;
            } else {
                j=mid-1;
            }
        }
        return times[j];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
