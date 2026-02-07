/**
 * Smart City Security Problem
 * 
 * Given n cities in a line with populations and security units, maximize protected population.
 * 
 * Rules:
 * - A unit at city i can move left to city i-1 (one move only)
 * - A city is protected if it has a unit
 * 
 * Input: 
 * - population[]: population of each city
 * - unit: "1" = has unit, "0" = no unit
 * 
 * Output: Maximum protected population
 * 
 * EXAMPLES: 
 * Input: population = [20,10,9,30,20,19], unit = "011011" → 80
 * Input: population = [5,4,5,1], unit = "0111" → 14
 * 
 */
import java.util.Arrays;

public class smartCitySecurity {
    public static int maxPopulation(int[] population, int[] security, int start, int n, int[][] dp, int flag) {
        if(start >= n) return 0;
        if(dp[start][flag] != -1) return dp[start][flag];
        int moveUnit = 0;
        if(start+1 < n && (security[start] == 0 || flag == 1) && security[start+1] == 1){
            moveUnit = population[start] + maxPopulation(population, security, start+1, n, dp, 1);
        }
        int stayUnit = maxPopulation(population, security, start+1, n, dp, 0);
        if(flag == 0) stayUnit += security[start]*population[start];
        return dp[start][flag] = Math.max(moveUnit,stayUnit);
    }
    public static int maxProtectedPopulation(int[] population, String unit) {
        int n = population.length;
        int security[] = new int[n];
        int dp[][] = new int[n][2];
        for(int i=0;i<n;i++) Arrays.fill(dp[i],-1);
        for(int i=0;i<n;i++){
            security[i] = unit.charAt(i) == '1' ? 1 : 0;
        }
        return maxPopulation(population, security,0 ,n, dp,0);
    }

    public static void main(String[] args) {
        int[] population1 = {20, 10, 9, 30, 20, 19};
        String unit1 = "011011";
        System.out.println(maxProtectedPopulation(population1, unit1)); // Output: 80

        int[] population2 = {5, 4, 5, 1};
        String unit2 = "0111";
        System.out.println(maxProtectedPopulation(population2, unit2)); // Output: 14
    }

}
