class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        // Step 1: Sorting is mandatory to group duplicates together
        Arrays.sort(nums);
        
        backtrack(0, nums, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int i, int[] nums, List<Integer> curr, List<List<Integer>> res) {
            // Base case: If we have processed all elements
            if (i == nums.length) {
                res.add(new ArrayList<>(curr));
                return;
            }

            // Choice 1: INCLUDE nums[i]
            curr.add(nums[i]);
            backtrack(i + 1, nums, curr, res);
            
            // Backtrack: Remove the element to explore the "Exclude" path
            curr.remove(curr.size() - 1);

            // Choice 2: EXCLUDE nums[i]
            // To prevent duplicate subsets, if we skip a number, 
            // we must skip all identical numbers at this same level.
            while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
                i++;
            }
            backtrack(i + 1, nums, curr, res);
        }

}
