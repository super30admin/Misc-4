public class TopVotedCandidate {
    // TC => O(n)
    // SC => O(n)
    Dictionary<int, int> countVotes;
    Dictionary<int, int> leaderMap;
    int[] times;
     // TC => O(n)
    // SC => O(n)
    public TopVotedCandidate(int[] persons, int[] times) {
        if(persons == null || persons.Length == 0){
            return;
        }
        countVotes = new Dictionary<int, int>();
        leaderMap = new Dictionary<int, int>();
        int leader = persons[0];
        this.times = times;
        for(int i = 0; i< persons.Length; i++){
            int person = persons[i];
            countVotes.TryAdd(person, 0);
            countVotes[person]++;

            if(countVotes[person] >= countVotes[leader]){
                leader = person;
            }

            int time = times[i];
            leaderMap.Add(time, leader);
        }
    }
    // TC => O(1) Best O(logn) Worst
    // SC => O(n)
    public int Q(int t) {
        if(leaderMap.ContainsKey(t)){
            return leaderMap[t];
        }
        int low = 0;
        int high = times.Length - 1;
        while(low <= high){
            int mid = low + (high - low)/2;
            if(t > times[mid]){
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return leaderMap[times[high]];
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.Q(t);
 */