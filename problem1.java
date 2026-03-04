import java.util.HashMap;

// Time Complexity: O(n) for constructor, O(log n) for q method
// Space Complexity: O(n) for storing leaderMap and countMap

public class problem1 {
    
    class TopVotedCandidate {
    HashMap<Integer, Integer> leaderMap;
    HashMap<Integer, Integer> countMap;
    int[] times;
    int[] persons;
    
    public TopVotedCandidate(int[] persons, int[] times) {
    
        this.leaderMap= new HashMap<>();
        this.countMap= new HashMap<>();
        this.times= times;
        this.persons= persons;

        int leader=0;
        for(int i=0; i<persons.length; i++) {
            {
                int person = persons[i];
                int time= times[i];

                countMap.put(person, countMap.getOrDefault(person, 0)+1);
                if(countMap.get(person) >= countMap.get(leader))
                {
                    leader= person;
                }

                leaderMap.put(time, leader);
            }
        }
    }

        public int q(int t) {
            
            if(leaderMap.containsKey(t)){return leaderMap.get(t);}
            int low=0, high = times.length-1;
            while(low<=high)
            {
                int mid= low+(high-low)/2;
                if(times[mid] > t){
                    high= mid-1;
                }
                else
                {
                    low=mid+1;
                }
            }
            return leaderMap.get(times[high]);
        
        }
    }
}
    
