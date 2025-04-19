class Sample{
/************************PROBLEM-1******************/
    class TopVotedCandidate {
        Map<Integer,Integer> leaderMap;
        Map<Integer,Integer> countMap;
        int[] times;
        //TC:0(n)
        //SC:0(n)
        public TopVotedCandidate(int[] persons, int[] times) {
            if(persons==null || persons.length==0){
                return;
            }

            leaderMap=new HashMap<>();
            countMap=new HashMap<>();
            this.times=times;

            int leader=persons[0];
            for(int i=0;i<times.length;i++){
                countMap.put(persons[i],countMap.getOrDefault(persons[i],0)+1);
                if(countMap.get(persons[i])>=countMap.get(leader)){
                    leader=persons[i];
                }
                leaderMap.put(times[i],leader);
            }
        }
        //TC:0(logn)
        //SC:0(1)
        public int q(int t) {
            if(leaderMap.containsKey(t)){
                return leaderMap.get(t);
            }

            int low=0,high=times.length-1;
            while(low<=high){
                int mid=low+(high-low)/2;
                if(t<times[mid]){
                    high=mid-1;
                }else{
                    low=mid+1;
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

/************************PROBLEM-2******************/

}