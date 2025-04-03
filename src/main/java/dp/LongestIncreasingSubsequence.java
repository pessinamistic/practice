package dp;

import dp.util.DPVisualizationHelper;
import java.util.Arrays;

/**
 * LeetCode #300 - Longest Increasing Subsequence
 * https://leetcode.com/problems/longest-increasing-subsequence/
 * 
 * Problem: Given an integer array nums, return the length of the longest strictly increasing subsequence.
 */
public class LongestIncreasingSubsequence {
    
    /**
     * Recursive solution with visualization
     */
    public static int lengthOfLISRecursive(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return lengthOfLISRecursiveHelper(nums, Integer.MIN_VALUE, 0, 0);
    }
    
    private static int lengthOfLISRecursiveHelper(int[] nums, int prev, int currentPos, int depth) {
        if (currentPos == nums.length) {
            DPVisualizationHelper.visualizeRecursion(depth, "Reached end, returning 0");
            return 0;
        }
        
        // Don't take current element
        DPVisualizationHelper.visualizeRecursion(depth, 
            "At position " + currentPos + ", prev=" + prev + ", current=" + nums[currentPos]);
        int exclude = lengthOfLISRecursiveHelper(nums, prev, currentPos + 1, depth + 1);
        
        // Take current element if it's greater than previous
        int include = 0;
        if (nums[currentPos] > prev) {
            DPVisualizationHelper.visualizeRecursion(depth, 
                "Including " + nums[currentPos] + " (> " + prev + ")");
            include = 1 + lengthOfLISRecursiveHelper(nums, nums[currentPos], currentPos + 1, depth + 1);
        }
        
        int result = Math.max(include, exclude);
        DPVisualizationHelper.visualizeRecursion(depth, 
            "At position " + currentPos + ": max(include=" + include + ", exclude=" + exclude + ") = " + result);
        return result;
    }
    
    /**
     * Dynamic Programming solution with visualization
     */
    public static int lengthOfLISDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1); // Each number is an increasing subsequence of length 1
        
        DPVisualizationHelper.visualize1DDP(dp, -1, 
            "Initial DP table (each element starts with length 1)\n" +
            "Array: " + Arrays.toString(nums));
        
        int maxLen = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    int newLen = dp[j] + 1;
                    if (newLen > dp[i]) {
                        dp[i] = newLen;
                        DPVisualizationHelper.visualize1DDP(dp, i,
                            "Found new increasing subsequence ending at index " + i + 
                            "\nCurrent number " + nums[i] + " > " + nums[j] + 
                            " at position " + j + "\nUpdating dp[" + i + "] = dp[" + j + "] + 1 = " + newLen);
                    }
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        
        return maxLen;
    }
    
    /**
     * Binary Search solution with visualization
     */
    public static int lengthOfLISBinarySearch(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        // tails[i] = smallest value that can end an increasing subsequence of length i+1
        int[] tails = new int[nums.length];
        tails[0] = nums[0];
        
        System.out.println("Initial tails array with first element: " + Arrays.toString(tails));
        int len = 1;
        
        for (int i = 1; i < nums.length; i++) {
            System.out.println("\nProcessing number: " + nums[i]);
            
            if (nums[i] < tails[0]) {
                // New smallest value
                tails[0] = nums[i];
                System.out.println("Found new smallest value: " + nums[i]);
            } else if (nums[i] > tails[len - 1]) {
                // Extend longest subsequence
                tails[len++] = nums[i];
                System.out.println("Extending sequence with: " + nums[i]);
                System.out.println("New tails array: " + Arrays.toString(Arrays.copyOf(tails, len)));
            } else {
                // Binary search to find the insertion position
                int pos = Arrays.binarySearch(tails, 0, len, nums[i]);
                if (pos < 0) {
                    pos = -(pos + 1);
                }
                tails[pos] = nums[i];
                System.out.println("Replacing tail at position " + pos + " with " + nums[i]);
                System.out.println("New tails array: " + Arrays.toString(Arrays.copyOf(tails, len)));
            }
        }
        
        return len;
    }
    
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        
        System.out.println("Finding Longest Increasing Subsequence in array: " + Arrays.toString(nums) + "\n");
        
        System.out.println("1. Recursive Approach (shows all decisions):");
        System.out.println("Result: " + lengthOfLISRecursive(nums));
        
        System.out.println("\n2. Dynamic Programming Approach (builds solution bottom-up):");
        System.out.println("Result: " + lengthOfLISDP(nums));
        
        System.out.println("\n3. Binary Search Approach (maintains array of sequence ends):");
        System.out.println("Result: " + lengthOfLISBinarySearch(nums));
    }
}
