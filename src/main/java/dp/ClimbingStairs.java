package dp;

import dp.util.DPVisualizationHelper;

/**
 * LeetCode #70 - Climbing Stairs
 * https://leetcode.com/problems/climbing-stairs/
 * 
 * Problem: You are climbing a staircase. It takes n steps to reach the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {
    
    /**
     * Recursive solution with visualization
     */
    public static int climbStairsRecursive(int n) {
        return climbStairsRecursiveHelper(n, 0);
    }
    
    private static int climbStairsRecursiveHelper(int n, int depth) {
        DPVisualizationHelper.visualizeRecursion(depth, "Computing ways(" + n + ")");
        
        if (n < 0) {
            DPVisualizationHelper.visualizeRecursion(depth, "Invalid step: " + n);
            return 0;
        }
        if (n == 0) {
            DPVisualizationHelper.visualizeRecursion(depth, "Found valid way!");
            return 1;
        }
        
        int ways = climbStairsRecursiveHelper(n - 1, depth + 1) + 
                  climbStairsRecursiveHelper(n - 2, depth + 1);
        
        DPVisualizationHelper.visualizeRecursion(depth, "ways(" + n + ") = " + ways);
        return ways;
    }
    
    /**
     * Top-down DP solution with memoization and visualization
     */
    public static int climbStairsMemo(int n) {
        Integer[] memo = new Integer[n + 1];
        return climbStairsMemoHelper(n, memo, 0);
    }
    
    private static int climbStairsMemoHelper(int n, Integer[] memo, int depth) {
        DPVisualizationHelper.visualizeRecursion(depth, "Computing ways(" + n + ")");
        
        if (n < 0) return 0;
        if (n == 0) {
            DPVisualizationHelper.visualizeRecursion(depth, "Found valid way!");
            return 1;
        }
        if (memo[n] != null) {
            DPVisualizationHelper.visualizeRecursion(depth, "Using memoized value for ways(" + n + ") = " + memo[n]);
            return memo[n];
        }
        
        memo[n] = climbStairsMemoHelper(n - 1, memo, depth + 1) + 
                  climbStairsMemoHelper(n - 2, memo, depth + 1);
        
        DPVisualizationHelper.visualizeMemo(memo, n, 
            "Updated memoization table after computing ways(" + n + ")");
        return memo[n];
    }
    
    /**
     * Bottom-up DP solution with tabulation and visualization
     */
    public static int climbStairsDP(int n) {
        if (n <= 1) return 1;
        
        int[] dp = new int[n + 1];
        dp[0] = 1; // One way to climb 0 stairs
        dp[1] = 1; // One way to climb 1 stair
        
        DPVisualizationHelper.visualize1DDP(dp, 1, 
            "Initial DP table\ndp[0] = 1 (base case: one way to climb 0 stairs)\n" +
            "dp[1] = 1 (base case: one way to climb 1 stair)");
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            DPVisualizationHelper.visualize1DDP(dp, i,
                "Computing dp[" + i + "] = dp[" + (i-1) + "] + dp[" + (i-2) + "] = " +
                dp[i-1] + " + " + dp[i-2] + " = " + dp[i] + "\n" +
                "This represents the number of ways to climb " + i + " stairs");
        }
        
        return dp[n];
    }
    
    /**
     * Space-optimized solution with visualization
     */
    public static int climbStairsOptimized(int n) {
        if (n <= 1) return 1;
        
        int prev2 = 1; // Ways to climb 0 stairs
        int prev1 = 1; // Ways to climb 1 stair
        int current = 0;
        
        System.out.println("\nSpace-optimized approach:");
        System.out.println("Initial state: prev2=" + prev2 + " (ways for 0 stairs)");
        System.out.println("              prev1=" + prev1 + " (ways for 1 stair)");
        
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
            System.out.println("Step " + i + ": Found " + current + " ways to climb " + i + " stairs");
            System.out.println("        prev1=" + prev1 + " (ways for " + i + " stairs)");
            System.out.println("        prev2=" + prev2 + " (ways for " + (i-1) + " stairs)");
        }
        
        return current;
    }
    
    public static void main(String[] args) {
        int n = 4; // Using a small number to better visualize the process
        
        System.out.println("Finding number of ways to climb " + n + " stairs:\n");
        
        System.out.println("1. Recursive Approach (shows all possible combinations):");
        System.out.println("Result: " + climbStairsRecursive(n));
        
        System.out.println("\n2. Memoization Approach (caches results):");
        System.out.println("Result: " + climbStairsMemo(n));
        
        System.out.println("\n3. Tabulation Approach (builds solution bottom-up):");
        System.out.println("Result: " + climbStairsDP(n));
        
        System.out.println("\n4. Space-Optimized Approach (uses only two variables):");
        System.out.println("Result: " + climbStairsOptimized(n));
    }
}
