// Time Complexity : O(log range of t)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
//  at each time start we store the leader using a hashmap/ array
// using binary search we find the time that is just greater than t (high pointer)
// we can return t if its in the map already in O(1)

class TopVotedCandidate {

    HashMap<Integer, Integer> leaders;
    HashMap<Integer, Integer> count;
    int[] times;

    public TopVotedCandidate(int[] persons, int[] times) {
        leaders = new HashMap<>();
        count = new HashMap<>();
        this.times = times;

        int leader = 0;
        count.put(0, 0);

        for (int i = 0; i < persons.length; i++) {
            int curr = persons[i];
            int time = times[i];

            count.put(curr, count.getOrDefault(curr, 0) + 1);

            if (count.get(curr) >= count.get(leader))
                leader = curr;
            leaders.put(time, leader);
        }
    }

    public int q(int t) {
        if (leaders.containsKey(t))
            return leaders.get(t);

        int low = 0;
        int high = times.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (times[mid] < t) {
                low = mid + 1;
            } else
                high = mid - 1;
        }

        return leaders.get(times[high]);
    }
}
