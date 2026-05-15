class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);

        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }

    private void backtrack(int[] candidates, int target, int start, List<Integer> curr, List<List<Integer>> res) {
        if (target == 0){
            res.add(new ArrayList<>(curr));
            return;
        }

        for (int i = start; i < candidates.length; i++){
            if (candidates[i] > target){
                break;
            }

            if(i > start && candidates[i] == candidates[i - 1]){
                continue;
            }

            curr.add(candidates[i]);

            backtrack(candidates, target - candidates[i], i + 1, curr, res);

            curr.remove(curr.size() - 1);
        }
    }
}
