package rubix;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Packs {
  // These actually represent columns in the matrix.
  private static final int PACKS_REQUIRED = 0;
  private static final int PACK_SIZES = 1;
  private static final int PACKS = 2;
  private static final int REMAINDER = 3;

  // The arrays are not defined as we don't know sizes yet.
  private static char[] status = null;
  private static int[][] matrix = null;

  // To set a Common flag to all rows.
  private static void setFlag(char flag) {
    for (int row = 0; row < status.length; row++) {
      status[row] = flag;
    }
  }

  /**
   * Checks if the `status` array has all 'E'. That means, we have been able to identify best
   * possible combination of packs.
   * @param status ... Array that holds status of each pack [For internal purpose only]
   * @return ... returns a Boolean value.
   */
  private static boolean checkFlag(char[] status) {
    boolean result = true;

    for (char stat : status) {
      if (stat != 'E') {
        result = false;
        break;
      }
    }

    return result;
  }

  private static void printMatrix() {
 /*   System.out.println("==== Matrix ==== ");

    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[i].length; j++) {
        System.out.format("%-10d", matrix[i][j]);
      }

      System.out.format("%-10c", status[i]);

      System.out.println();
    }
    System.out.println();*/
  }

  /**
   * This method calculates No. of Packs and remainder for each of the packagings. It updates
   * the matrix.
   */
  private static void performCals() {
    for (int row = 0; row < matrix.length; row++) {

      // Update No. of Packs only the flag for the row is NOT 'I' [I for Ignore]. The flag tells the
      // algorithm to use a specific "number" instead of "actual divisor". For instance, "15" can be
      // divided by "2 packs" of "7 buns". but, we want to keep only "1 one" pack instead of "2".
      if (status[row] != 'I') {
        matrix[row][PACKS] = matrix[row][PACKS_REQUIRED] / matrix[row][PACK_SIZES];
      }

      // Instead of using "%", use the following formula
      if (matrix[row][PACKS_REQUIRED] - (matrix[row][PACK_SIZES] * matrix[row][PACKS]) >= 0) {
        matrix[row][REMAINDER] =
            matrix[row][PACKS_REQUIRED] - (matrix[row][PACK_SIZES] * matrix[row][PACKS]);
      } else {
        matrix[row][PACKS] = matrix[row][PACKS_REQUIRED] / matrix[row][PACK_SIZES];
        matrix[row][REMAINDER] = matrix[row][PACKS_REQUIRED] % matrix[row][PACK_SIZES];
      }

      // If we get a result of zero, that means, current no. of packs are good to go.
      // To Indicate this, keep "E" in the last col for all rows.
      if (matrix[row][REMAINDER] == 0) {
        setFlag('E');
        return;
      }

      // Copy the remainder of current row to the next row first column.
      if (row != (matrix.length - 1)) {
        matrix[row + 1][PACKS_REQUIRED] = matrix[row][REMAINDER];
      }
    }
  }

  private static void findCombincation() {
    performCals();
    printMatrix();

    while (true) {
      // Check if there is a remainder of zero. In other terms, check the 'status' array. if all
      // rows are 'E', that means, we got the best combination of packs.
      if (checkFlag(status)) {
        break;
      }

      // Sometimes, with the given combination of packs, we may not be able to meet customer's
      // requirement. E.g., Customer wants 7 packs, we have [6 packs, 3 packs]. In such case, the
      // matrix will have zeros in all rows except the last row.
      boolean isItPossible = false;

      for (int row = 0; row < matrix.length - 1; row++) {
        if (matrix[row][PACKS] != 0) {
          isItPossible = true;
        }
      }

      if (!isItPossible) {
        for(int row = 0; row < matrix.length; row++)
          matrix[row][PACKS] = 0;

        break;
      }

      setFlag('S');

      // We have not figured out good combination.
      for (int row = matrix.length - 2; row >= 0; row--) {
        if (matrix[row][PACKS] != 0) {
          matrix[row][PACKS] = matrix[row][PACKS] - 1;
          status[row] = 'I';
          break;
        }
      }

      performCals();
      printMatrix();
    }
  }

  private static void checkForOptimumCombination(int customerRequirement, int[] packSizes) {
    matrix = new int[packSizes.length][4];
    matrix[0][0] = customerRequirement;

    for (int i = 0; i < packSizes.length; i++) {
      matrix[i][1] = packSizes[i];
    }

    // Status matrix
    //   E - End
    //   C - Calculate
    //   I - Ignore
    //   S - Start
    status = new char[packSizes.length];
    setFlag('S');

    printMatrix();

    // If each of the packs is more than what customer wants, the request cannot be satisfied
    // For instance, there are packages [8, 6, 5] and customer wants 4 buns, we cannot satisfy
    // this.
    int count = 0;
    for (int size : packSizes) {
      if (size > customerRequirement) {
        count++;
      }
    }

    if (count == packSizes.length) {
      return;
    }

    findCombincation();
  }

  public static Map<String, Integer> findOutPackCombinations(int[] packSizes,
      int customerRequirement,
      String order) {
    // Sort the Pack sizes in Descending order. The packs with higher number of buns should be
    // considered first to save space.
    Integer[] a2 = Arrays.stream(packSizes).boxed().toArray(Integer[]::new);
    Arrays.sort(a2, Collections.reverseOrder());

    for (int i = 0; i < a2.length; i++) {
      packSizes[i] = a2[i];
    }

    System.out.print(" Pack Sizes: [");

    for (int size : packSizes) {
      System.out.print(size + " ");
    }

    System.out.println("]");

    Map<String, Integer> prices = new HashMap<>();

    checkForOptimumCombination(customerRequirement, packSizes);

    // By now best combination of packs have been identified. For each each pack, return pack size
    // and count of packs.
    for (int[] aMatrix : matrix) {
      if (aMatrix[2] > 0) {
        prices.put(order + "~" + aMatrix[1], aMatrix[2]);
      }
    }

    return prices;
  }
}