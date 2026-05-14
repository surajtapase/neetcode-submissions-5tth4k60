class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();

        backtrack(0, nums, target, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int index, int[] nums, int target, List<Integer> current, List<List<Integer>> result) {
        if (target == 0){
            result.add(new ArrayList<>(current));
            return;
        }

        if(target < 0 || index == nums.length){
            return;
        }

        current.add(nums[index]);
        backtrack(index, nums, target - nums[index], current, result);

        current.remove(current.size() - 1);

        backtrack(index + 1, nums, target, current, result);
    }

}
