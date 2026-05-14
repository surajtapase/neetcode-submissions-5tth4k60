class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    private void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int start){
        result.add(new ArrayList<>(currentSubset));

        // Step 2: Array mein aage badhte hue choices explore karein
        for (int i = start; i < nums.length; i++) {
            // Include the element
            currentSubset.add(nums[i]);
            
            // Recurse to the next element
            backtrack(result, currentSubset, nums, i + 1);
            
            // Backtrack: Remove the element to explore the "exclude" path
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}
