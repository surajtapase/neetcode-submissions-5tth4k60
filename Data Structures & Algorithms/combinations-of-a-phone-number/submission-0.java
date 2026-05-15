class Solution {
     // Mapping of digits to letters based on a standard phone keypad
    private static final String[] MAPPING = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "pqrs", // 7
        "tuv",  // 8
        "wxyz"  // 9
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        // Edge case: if input is empty, return an empty list
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        backtrack(result, new StringBuilder(), digits, 0);
        return result;
    }

    private void backtrack(List<String> result, StringBuilder current, String digits, int index) {
        // Base case: If the current combination length matches the input digits length
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }

        // Get the letters corresponding to the current digit
        String letters = MAPPING[digits.charAt(index) - '0'];

        for (char c : letters.toCharArray()) {
            // Choose: Add the letter to our current path
            current.append(c);
            
            // Explore: Recurse to the next digit in the string
            backtrack(result, current, digits, index + 1);
            
            // Un-choose: Remove the last letter to backtrack and try the next letter
            current.deleteCharAt(current.length() - 1);
        }
    }
}
