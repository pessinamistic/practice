/* (C) Shashank Shekhar : Lucipurr Inc. */
package arrays;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class TwoSumTest {

  @Test
  public void testBasicCase() {
    TwoSum solution = new TwoSum();
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    int[] expected = {0, 1};
    assertArrayEquals(expected, solution.twoSum(nums, target));
  }

  @Test
  public void testNonSequentialIndices() {
    TwoSum solution = new TwoSum();
    int[] nums = {3, 2, 4};
    int target = 6;
    int[] expected = {1, 2};
    assertArrayEquals(expected, solution.twoSum(nums, target));
  }

  @Test
  public void testSameNumbers() {
    TwoSum solution = new TwoSum();
    int[] nums = {3, 3};
    int target = 6;
    int[] expected = {0, 1};
    assertArrayEquals(expected, solution.twoSum(nums, target));
  }

  @Test
  public void testLargerArray() {
    TwoSum solution = new TwoSum();
    int[] nums = {1, 5, 8, 3, 2, 9, 7, 6};
    int target = 10;
    int[] result = solution.twoSum(nums, target);
    // Since multiple valid answers might exist, we'll verify the sum
    assertEquals(target, nums[result[0]] + nums[result[1]]);
    assertNotEquals(result[0], result[1]); // Indices should be different
  }

  @Test
  public void testNegativeNumbers() {
    TwoSum solution = new TwoSum();
    int[] nums = {-1, -2, -3, -4, -5};
    int target = -8;
    int[] result = solution.twoSum(nums, target);
    assertEquals(target, nums[result[0]] + nums[result[1]]);
    assertNotEquals(result[0], result[1]);
  }
}
