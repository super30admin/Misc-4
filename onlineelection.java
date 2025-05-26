//tc: O(logn)
// sc: O(n)
import java.util.*;

class TopVotedCandidate {
    int[] persons;
    int[] times;
    HashMap<Integer, Integer> votesperperson;
    HashMap<Integer, Integer> votesattime;
    int leader;
    public TopVotedCandidate(int[] persons, int[] times) {
        this.persons = persons;
        this.times = times;
        leader = persons[0];
        votesperperson= new HashMap<>();
        votesattime= new HashMap<>();

        for(int i=0; i<persons.length; i++){
            votesperperson.put(persons[i], votesperperson.getOrDefault(persons[i],0)+1); 
            if(votesperperson.get(persons[i]) >= votesperperson.get(leader)){
                leader = persons[i];
            }
            votesattime.put(times[i], leader);
        }
    }
    
    public int q(int t) {
        if(votesattime.containsKey(t)){
            return votesattime.get(t);
        }
        int low =0;
        int high = times.length-1;

        while(low <= high){
            int mid = low+(high-low)/2;
            if(times[mid] > t){
                high = mid-1;
            }else {
                low = mid+1;
            }
        }
        return votesattime.get(times[high]);
    }
}
