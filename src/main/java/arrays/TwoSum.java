/* (C) Shashank Shekhar : Lucipurr Inc. */
package arrays;

import java.util.HashMap;
import java.util.Map;

/**
 * Problem: Two Sum Given an array of integers nums and an integer target, return indices of the two
 * numbers such that they add up to target.
 *
 * <p>You may assume that each input would have exactly one solution, and you may not use the same
 * element twice.
 *
 * <p>You can return the answer in any order.
 *
 * <p>Example 1: Input: nums = [2,7,11,15], target = 9 Output: [0,1] Explanation: Because nums[0] +
 * nums[1] == 9, we return [0, 1].
 *
 * <p>Example 2: Input: nums = [3,2,4], target = 6 Output: [1,2]
 *
 * <p>Example 3: Input: nums = [3,3], target = 6 Output: [0,1]
 *
 * <p>Constraints: - 2 <= nums.length <= 104 - -109 <= nums[i] <= 109 - -109 <= target <= 109 - Only
 * one valid answer exists.
 */
public class TwoSum {
  /**
   * Find two numbers in the array that add up to target
   *
   * @param nums Input array of integers
   * @param target Target sum to find
   * @return Array of two indices whose corresponding values sum to target
   */
  public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int complement = target - nums[i];
      if (map.containsKey(complement)) {
        return new int[] {map.get(complement), i};
      }
      map.put(nums[i], i);
    }

    return new int[] {};
  }
}
