class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, new ArrayList<>(), used, result);
        return result;
    }

    private void backtrack(int[] nums, List<Integer> currentPath, boolean[] used, List<List<Integer>> result) {

        if (currentPath.size() == nums.length){
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for(int i = 0; i < nums.length; i++){
            if (used[i]){
                continue;
            }

            currentPath.add(nums[i]);
            used[i] = true;

            backtrack(nums, currentPath, used, result);

            used[i] = false;
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
