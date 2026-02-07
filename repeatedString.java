/**
 * PROBLEM: Minimum String Length After Prefix Pattern Removal
 * 
 * Given a string, remove segments that match any prefix to minimize the final length.
 * 
 * RULE: If substring[0..k-1] == substring[i..i+k-1], remove substring[i..i+k-1]
 * 
 * EXAMPLES:
 * Input: "ABCABCEABCABCE" → Output: 6
 * Input: "ABCABCEABCABC"  → Output: 11
 * Input: "AAAAAA"          → Output: 4
 * Input: "ABCABCABC"       → Output: 7
 * 
 * APPROACH: DP with memoization. Try all prefix lengths, choose minimum.
 * TIME: O(n²), SPACE: O(n)
 */

import java.util.Arrays;

public class repeatedString {
    public static int encodedString(String input, int start, int n, int[] dp){
        if(2*start > n){
            return n < start ? 0 : n-start;
        }
        if(dp[start] != -1) return dp[start];
        int replace = Integer.MAX_VALUE;
        String prefix = input.substring(0,start);
        String curr = input.substring(start, 2*start);
        if(prefix.equalsIgnoreCase(curr)){
            replace = 1 + encodedString(input,2*start,n,dp);
        }
        int keep = 1 + encodedString(input,start+1,n,dp);
        return dp[start] = Math.min(replace, keep);
    }
    public static int replaceSegment(String input){
        int n = input.length();
        int dp[] = new int[n];
        Arrays.fill(dp,-1);
        return 1 + encodedString(input,1,n,dp);
    }

    public static void main(String[] args) {
        String input1 = "ABCABCEABCABCE";
        String input2 = "ABCABCEABCABC";
        String input3 = "AAAAAA";
        String input4 = "ABCABCABC";

        System.out.println(replaceSegment(input1)); // Output: 6
        System.out.println(replaceSegment(input2)); // Output: 11
        System.out.println(replaceSegment(input3)); // Output: 4
        System.out.println(replaceSegment(input4)); // Output: 7
    }
}