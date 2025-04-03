package dp;

import dp.util.DPVisualizationHelper;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * LeetCode #322 - Coin Change
 * https://leetcode.com/problems/coin-change/
 * 
 * Problem: You are given an integer array coins representing coins of different denominations 
 * and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. 
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 */
public class CoinChange {
    
    /**
     * Recursive solution with memoization and visualization
     */
    public static int coinChangeMemo(int[] coins, int amount) {
        return coinChangeMemoHelper(coins, amount, new Integer[amount + 1], 0);
    }
    
    private static int coinChangeMemoHelper(int[] coins, int amount, Integer[] memo, int depth) {
        if (amount == 0) {
            DPVisualizationHelper.visualizeRecursion(depth, "Found solution for amount 0");
            return 0;
        }
        if (amount < 0) {
            DPVisualizationHelper.visualizeRecursion(depth, "Invalid amount: " + amount);
            return -1;
        }
        if (memo[amount] != null) {
            DPVisualizationHelper.visualizeRecursion(depth, 
                "Using memoized value for amount " + amount + ": " + memo[amount]);
            return memo[amount];
        }
        
        DPVisualizationHelper.visualizeRecursion(depth, "Trying coins for amount: " + amount);
        int minCoins = Integer.MAX_VALUE;
        
        for (int coin : coins) {
            DPVisualizationHelper.visualizeRecursion(depth, "Trying coin: " + coin);
            int result = coinChangeMemoHelper(coins, amount - coin, memo, depth + 1);
            if (result >= 0) {
                minCoins = Math.min(minCoins, result + 1);
                DPVisualizationHelper.visualizeRecursion(depth, 
                    "Found solution using coin " + coin + ", total coins: " + minCoins);
            }
        }
        
        memo[amount] = minCoins == Integer.MAX_VALUE ? -1 : minCoins;
        DPVisualizationHelper.visualizeMemo(memo, amount,
            "Updated memo for amount " + amount + ": " + memo[amount]);
        return memo[amount];
    }
    
    /**
     * Bottom-up DP solution with visualization
     */
    public static int coinChangeDP(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        
        DPVisualizationHelper.visualize1DDP(dp, 0,
            "Initial DP table\ndp[0] = 0 (base case: 0 coins needed for amount 0)\n" +
            "All other values initialized to " + (amount + 1) + " (impossible value)\n" +
            "Available coins: " + Arrays.toString(coins));
        
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    int newValue = dp[i - coin] + 1;
                    if (newValue < dp[i]) {
                        dp[i] = newValue;
                        DPVisualizationHelper.visualize1DDP(dp, i,
                            "For amount " + i + ", using coin " + coin + ":\n" +
                            "dp[" + i + "] = dp[" + (i-coin) + "] + 1 = " + dp[i-coin] + " + 1 = " + dp[i]);
                    }
                }
            }
        }
        
        return dp[amount] > amount ? -1 : dp[amount];
    }
    
    /**
     * BFS solution with visualization
     */
    public static int coinChangeBFS(int[] coins, int amount) {
        if (amount == 0) return 0;
        
        boolean[] visited = new boolean[amount + 1];
        int[] levels = new int[amount + 1];
        visited[0] = true;
        
        int[] queue = new int[amount + 1];
        int front = 0, rear = 0;
        queue[rear++] = 0;
        
        System.out.println("\nBFS Approach:");
        System.out.println("Starting BFS from amount 0");
        System.out.println("Available coins: " + Arrays.toString(coins));
        
        while (front < rear) {
            int current = queue[front++];
            System.out.println("\nProcessing amount: " + current + 
                             " (level " + levels[current] + ")");
            
            for (int coin : coins) {
                int next = current + coin;
                
                if (next == amount) {
                    System.out.println("Found target amount " + amount + 
                                     " using coin " + coin + 
                                     " (total coins: " + (levels[current] + 1) + ")");
                    return levels[current] + 1;
                }
                
                if (next < amount && !visited[next]) {
                    visited[next] = true;
                    levels[next] = levels[current] + 1;
                    queue[rear++] = next;
                    System.out.println("Added amount " + next + 
                                     " to queue (using coin " + coin + 
                                     ", level " + levels[next] + ")");
                }
            }
        }
        
        System.out.println("\nNo solution found!");
        return -1;
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        
        System.out.println("Finding minimum coins needed for amount " + amount);
        System.out.println("Available coins: " + Arrays.toString(coins) + "\n");
        
        System.out.println("1. Recursive with Memoization (shows decision tree):");
        System.out.println("Result: " + coinChangeMemo(coins, amount));
        
        System.out.println("\n2. Dynamic Programming (builds solution bottom-up):");
        System.out.println("Result: " + coinChangeDP(coins, amount));
        
        System.out.println("\n3. BFS Approach (finds shortest path to amount):");
        System.out.println("Result: " + coinChangeBFS(coins, amount));
        
        // Test with impossible amount
        System.out.println("\nTesting impossible case:");
        amount = 3;
        coins = new int[]{2};
        System.out.println("Amount: " + amount);
        System.out.println("Coins: " + Arrays.toString(coins));
        System.out.println("Result: " + coinChangeDP(coins, amount)); // Should return -1
    }
}
