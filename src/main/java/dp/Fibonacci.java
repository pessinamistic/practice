package dp;

import dp.util.DPVisualizationHelper;
import java.util.HashMap;
import java.util.Map;

/**
 * Classic Fibonacci Problem to demonstrate Dynamic Programming concepts
 * Problem: Find the nth number in the Fibonacci sequence
 * Fibonacci sequence: 0, 1, 1, 2, 3, 5, 8, 13, 21, ...
 * where each number is the sum of the two preceding ones
 */
public class Fibonacci {
    
    /**
     * Simple recursive solution with visualization
     */
    public static long fibRecursive(int n) {
        return fibRecursiveWithVisualization(n, 0);
    }
    
    private static long fibRecursiveWithVisualization(int n, int depth) {
        DPVisualizationHelper.visualizeRecursion(depth, "Computing fib(" + n + ")");
        
        if (n <= 1) {
            DPVisualizationHelper.visualizeRecursion(depth, "Base case: fib(" + n + ") = " + n);
            return n;
        }
        
        long result = fibRecursiveWithVisualization(n - 1, depth + 1) + 
                     fibRecursiveWithVisualization(n - 2, depth + 1);
        
        DPVisualizationHelper.visualizeRecursion(depth, "fib(" + n + ") = " + result);
        return result;
    }
    
    /**
     * Top-down DP solution with memoization and visualization
     */
    public static long fibMemoization(int n) {
        Integer[] memo = new Integer[n + 1];
        return fibMemoHelper(n, memo, 0);
    }
    
    private static long fibMemoHelper(int n, Integer[] memo, int depth) {
        DPVisualizationHelper.visualizeRecursion(depth, "Computing fib(" + n + ")");
        
        if (n <= 1) return n;
        if (memo[n] != null) {
            DPVisualizationHelper.visualizeRecursion(depth, "Using memoized value for fib(" + n + ") = " + memo[n]);
            return memo[n];
        }
        
        memo[n] = (int) (fibMemoHelper(n - 1, memo, depth + 1) + 
                        fibMemoHelper(n - 2, memo, depth + 1));
        
        DPVisualizationHelper.visualizeMemo(memo, n, "Updated memoization table after computing fib(" + n + ")");
        return memo[n];
    }
    
    /**
     * Bottom-up DP solution with tabulation and visualization
     */
    public static long fibTabulation(int n) {
        if (n <= 1) return n;
        
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        
        DPVisualizationHelper.visualize1DDP(dp, 1, "Initial DP table");
        
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            DPVisualizationHelper.visualize1DDP(dp, i, 
                "Computing dp[" + i + "] = dp[" + (i-1) + "] + dp[" + (i-2) + "] = " + 
                dp[i-1] + " + " + dp[i-2] + " = " + dp[i]);
        }
        
        return dp[n];
    }
    
    /**
     * Space-optimized solution with visualization
     */
    public static long fibOptimized(int n) {
        if (n <= 1) return n;
        
        long prev2 = 0;
        long prev1 = 1;
        long current = 0;
        
        System.out.println("\nSpace-optimized approach:");
        System.out.println("Initial state: prev2=" + prev2 + ", prev1=" + prev1);
        
        for (int i = 2; i <= n; i++) {
            current = prev1 + prev2;
            prev2 = prev1;
            prev1 = current;
            System.out.println("Step " + i + ": current=" + current + 
                             ", prev1=" + prev1 + ", prev2=" + prev2);
        }
        
        return current;
    }

    public static void main(String[] args) {
        int n = 6; // Using a smaller number to better visualize the process
        
        System.out.println("Computing Fibonacci number at position " + n + ":\n");
        
        System.out.println("1. Recursive Approach:");
        System.out.println("Result: " + fibRecursive(n));
        
        System.out.println("\n2. Memoization Approach:");
        System.out.println("Result: " + fibMemoization(n));
        
        System.out.println("\n3. Tabulation Approach:");
        System.out.println("Result: " + fibTabulation(n));
        
        System.out.println("\n4. Space-Optimized Approach:");
        System.out.println("Result: " + fibOptimized(n));
    }
}
