import java.util.HashMap;

//  TC- O(N) + O(log N)-binarySearch, SC-O(n+m) m-candidates.
public class TopVotedCandidate {

        // Map to store the leading candidate at each time point
        HashMap<Integer, Integer> leaders = new HashMap<>();

        // Array to store all the voting times
        int[] time;

        public TopVotedCandidate(int[] persons, int[] times) {
            time = times;

            // Map to count the votes received by each person
            HashMap<Integer, Integer> votesCount = new HashMap<>();
            int leader = -1;
            for (int i = 0; i < persons.length; i++) {

                // Update the vote count for the current person
                votesCount.put(persons[i], votesCount.getOrDefault(persons[i], 0) + 1);

                // Update leader if current person has equal or more votes than current leader
                // If equal, the most recent vote determines the leader (tie goes to the latest)
                if (i == 0 || votesCount.get(leader) <= votesCount.get(persons[i])) {
                    leader = persons[i];
                }

                // Store the leader at the current time
                leaders.put(times[i], leader);
            }

        }

        public int q(int t) {
            // If the exact time exists in the map, return the leader directly
            if (leaders.containsKey(t)) return leaders.get(t);

            // Binary search to find the latest time less than or equal to t
            int low = 0;
            int high = time.length - 1;

            while (low <= high) {
                int mid = low + (high - low) / 2;

                if (time[mid] == t) {
                    return leaders.get(t);
                } else if (time[mid] < t) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            // Return the leader at the latest time <= t
            return leaders.get(time[high]);
        }
    }

/**
 HashMap	    Key	                Value	                Role
 votesCount      CandidateID	     Vote count	            Tracks votes each candidate gets
 leaders	        Time t	        Current leader at t	    Records leader at every timestamp
 */
}
