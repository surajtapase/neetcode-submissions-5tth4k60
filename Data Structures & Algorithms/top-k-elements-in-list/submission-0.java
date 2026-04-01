class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        for(int n : nums){
            freq.put(n, freq.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] buckets = new ArrayList[nums.length + 1];
        for(int key : freq.keySet()){
            int f = freq.get(key);
            if(buckets[f] == null) buckets[f] = new ArrayList<>();
            buckets[f].add(key);
        }

        int[] res = new int[k];
        int idx = 0;

        for (int i = buckets.length - 1; i>=0 && idx < k; i--){
            if(buckets[i] != null){
                for (int n : buckets[i]){
                    res[idx++] = n;
                    if(idx == k) break;
                }
            }
        }
        return res;
    }
}
