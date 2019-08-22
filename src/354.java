// You have a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.

// What is the maximum number of envelopes can you Russian doll? (put one inside other)

// Note:
// Rotation is not allowed.

// Example:

// Input: [[5,4],[6,4],[6,7],[2,3]]
// Output: 3 
// Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).


class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        Comparator<int[]> localeComparator = new Comparator<int[]>() {
			public int compare(int[] a, int[] b) {
				return a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]; 
			}
		};
		Arrays.sort(envelopes, localeComparator);
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i = 0; i < n-1; i++){
            int[] a = envelopes[i];
            map.put(i, new ArrayList<>());
            for(int j = i + 1; j < n; j++){
                int[] b = envelopes[j];
                if(b[0] > a[0] && b[1] > a[1]) map.get(i).add(j);
            }
        }
        
        int[] dp = new int[n];
        for(int i = 0; i < n; i++){
            if(map.containsKey(i)){
                dp[i] = Math.max(dp[i], 1);
                for(int j : map.get(i)){
                    dp[j] = Math.max(dp[i] + 1, dp[j]);
                }
            }
        }
        
        int max = 0;
        for(int i: dp) max = Math.max(i, max);
        return max;
    }
}