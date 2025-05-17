import java.util.HashMap;
import java.util.Map;

//TC: O(logn)
//SC: O(n)
class TopVotedCandidate {
  private Map<Integer,Integer> personmap=new HashMap<>();
  private Map<Integer,Integer> countmap=new HashMap<>();
  private int[] time;
  public TopVotedCandidate(int[] persons, int[] times) {
      this.time=times;
      int leader=0;
      for(int i=0;i<persons.length;i++){
          int p=persons[i];
          int t=time[i];
          personmap.put(p,personmap.getOrDefault(p,0)+1);
          if(personmap.get(leader)<=personmap.get(p)){
              leader=p;
          }
          countmap.put(t,leader);
      }
  }
  
  public int q(int t) {
      if(countmap.containsKey(t)){
          return countmap.get(t);
      }
      int l=0;
      int h=time.length-1;
      while(l<=h){
          int mid=l+(h-l)/2;
          if(time[mid]>t){
              h=mid-1;
          }else{
              l=mid+1;
          }
      }
      //high will be at time just smaller than t;
      return countmap.get(time[h]);
  }
}

/**
* Your TopVotedCandidate object will be instantiated and called as such:
* TopVotedCandidate obj = new TopVotedCandidate(persons, times);
* int param_1 = obj.q(t);
*/