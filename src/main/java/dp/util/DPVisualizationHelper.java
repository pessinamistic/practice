/* (C) Shashank Shekhar : Lucipurr Inc. */
package dp.util;

import java.util.Arrays;

/**
 * Utility class to help visualize how Dynamic Programming tables are filled and how the algorithm
 * progresses step by step.
 */
public class DPVisualizationHelper {

  private static final String ANSI_RESET = "\u001B[0m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_GREEN = "\u001B[32m";
  private static final String ANSI_YELLOW = "\u001B[33m";
  private static final String ANSI_BLUE = "\u001B[34m";

  /** Helper method to repeat a string n times */
  private static String repeatString(String str, int times) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < times; i++) {
      sb.append(str);
    }
    return sb.toString();
  }

  /** Prints a 1D DP table with highlighting for the current position */
  public static void visualize1DDP(long[] dp, int currentIndex, String description) {
    System.out.println("\n" + description);
    System.out.println("Index:  " + createIndexString(dp.length));
    System.out.print("Value:  ");

    for (int i = 0; i < dp.length; i++) {
      if (i == currentIndex) {
        System.out.print(ANSI_GREEN + String.format("%-4d", dp[i]) + ANSI_RESET);
      } else {
        System.out.print(String.format("%-4d", dp[i]));
      }
    }
    System.out.println("\n" + repeatString("=", dp.length * 4));
  }

  /** Prints a 1D DP table with highlighting for the current position (int version) */
  public static void visualize1DDP(int[] dp, int currentIndex, String description) {
    System.out.println("\n" + description);
    System.out.println("Index:  " + createIndexString(dp.length));
    System.out.print("Value:  ");

    for (int i = 0; i < dp.length; i++) {
      if (i == currentIndex) {
        System.out.print(ANSI_GREEN + String.format("%-4d", dp[i]) + ANSI_RESET);
      } else {
        System.out.print(String.format("%-4d", dp[i]));
      }
    }
    System.out.println("\n" + repeatString("=", dp.length * 4));
  }

  /** Prints a 2D DP table with highlighting for the current position */
  public static void visualize2DDP(int[][] dp, int currentRow, int currentCol, String description) {
    System.out.println("\n" + description);

    // Print column indices
    System.out.print("     ");
    for (int j = 0; j < dp[0].length; j++) {
      System.out.print(String.format("%-4d", j));
    }
    System.out.println("\n" + "     " + repeatString("=", dp[0].length * 4));

    // Print each row
    for (int i = 0; i < dp.length; i++) {
      System.out.print(String.format("%-4d|", i));
      for (int j = 0; j < dp[i].length; j++) {
        if (i == currentRow && j == currentCol) {
          System.out.print(ANSI_GREEN + String.format("%-4d", dp[i][j]) + ANSI_RESET);
        } else if (dp[i][j] == Integer.MAX_VALUE || dp[i][j] == Integer.MIN_VALUE) {
          System.out.print(ANSI_RED + " ∞  " + ANSI_RESET);
        } else {
          System.out.print(String.format("%-4d", dp[i][j]));
        }
      }
      System.out.println();
    }
    System.out.println(repeatString("=", dp[0].length * 4 + 5));
  }

  /** Visualizes the state of a memoization table */
  public static void visualizeMemo(Integer[] memo, int currentKey, String description) {
    System.out.println("\n" + description);
    System.out.println("Key:    " + createIndexString(memo.length));
    System.out.print("Value:  ");

    for (int i = 0; i < memo.length; i++) {
      String value = memo[i] == null ? "?" : memo[i].toString();
      if (i == currentKey) {
        System.out.print(ANSI_YELLOW + String.format("%-4s", value) + ANSI_RESET);
      } else {
        System.out.print(String.format("%-4s", value));
      }
    }
    System.out.println("\n" + repeatString("=", memo.length * 4));
  }

  /** Creates a formatted index string for table headers */
  private static String createIndexString(int length) {
    StringBuilder indices = new StringBuilder();
    for (int i = 0; i < length; i++) {
      indices.append(String.format("%-4d", i));
    }
    return indices.toString();
  }

  /** Prints the current state of recursion */
  public static void visualizeRecursion(int depth, Object... args) {
    String indent = repeatString("  ", depth);
    System.out.print(ANSI_BLUE + indent + "→ ");
    for (Object arg : args) {
      System.out.print(arg + " ");
    }
    System.out.println(ANSI_RESET);
  }

  /** Prints an array with highlighting for specific indices */
  public static void visualizeArray(int[] arr, int[] highlightIndices, String description) {
    System.out.println("\n" + description);
    System.out.println("Index:  " + createIndexString(arr.length));
    System.out.print("Value:  ");

    for (int i = 0; i < arr.length; i++) {
      boolean highlight = Arrays.binarySearch(highlightIndices, i) >= 0;
      if (highlight) {
        System.out.print(ANSI_GREEN + String.format("%-4d", arr[i]) + ANSI_RESET);
      } else {
        System.out.print(String.format("%-4d", arr[i]));
      }
    }
    System.out.println("\n" + repeatString("=", arr.length * 4));
  }
}
