class TopVotedCandidate {
    HashMap<Integer, Integer> leadersMap;
    int[] times;
    public TopVotedCandidate(int[] persons, int[] times) {
        leadersMap = new HashMap<>();
        int leader = 0;
        this.times = times;
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for(int i = 0; i < persons.length; i++){
            int p = persons[i];
            int t = times[i];
            countMap.put(p, countMap.getOrDefault(p, 0) + 1);
            if(countMap.get(p) >= countMap.get(leader)){
                leader = p;
            }
            leadersMap.put(t, leader);
        }
    }  
    public int q(int t) {
        if(leadersMap.containsKey(t))
            return leadersMap.get(t);
        int l = 0; 
        int h = times.length - 1;
        while(l <= h){
            int mid = l + (h - l)/ 2;
            if(times[mid] > t){
                h = mid - 1;
            }
            else{
                l = mid + 1;
            }
        }
        return leadersMap.get(times[h]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
