//problem1
class TopVotedCandidate {
    int[] persons;
    int[] times;
    HashMap<Integer,Integer> map;
    HashMap<Integer,Integer> leadermap;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons=persons;
        this.times=times;
        this.map=new HashMap<>();
        this.leadermap=new HashMap<>();
        int leader=0;
        for(int i=0;i<persons.length;i++){
            int person=persons[i];
            int time=times[i];
            map.put(person,map.getOrDefault(person,0)+1);
            if(map.get(person)>=map.get(leader)){
                leader=person;
            }
            leadermap.put(time,leader);
        }
    }
    public int q(int t) {
        if(leadermap.containsKey(t)) return leadermap.get(t);
        int low=0,high=times.length-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(times[mid]>t){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return leadermap.get(times[high]);
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
//problem2
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n=heights.length;
        Stack<Integer> st=new Stack<>();
        st.push(-1);
        int res=0;
        for(int i=0;i<n;i++){
            while(st.peek()!=-1 && heights[i]<heights[st.peek()]){
                int index=st.pop();
                int width=i-st.peek()-1;
                res=Math.max(res,heights[index]*width);
            }
            st.push(i);
        }
        while(st.peek()!=-1){
                int index=st.pop();
                int width=n-st.peek()-1;
                res=Math.max(res,heights[index]*width);
        }
        return res;
    }
}
