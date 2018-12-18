package rubix;

import static rubix.Packs.findOutPackCombinations;
import static rubix.Utils.printSeparator;
import static rubix.Utils.readOrders;
import static rubix.Utils.readPrices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeSet;

public class Bakers {

  public static Double calculateBill(Map<String, Double> prices, Map<String, Integer> orders) {
    Double bill = 0.0;

    for (String order : new TreeSet<>(orders.keySet())) {
      printSeparator();
      System.out.print("Order: " + order + ", Quantity: " + orders.get(order));

      List<Integer> packList = new ArrayList<>();

      // Collect available pack sizes for this type of bun
      for (String pack : prices.keySet()) {
        if (pack.startsWith(order)) {
          packList.add(Integer.parseInt(pack.split("~")[1]));
        }
      }

      // Store in an Array.
      int[] packs = new int[packList.size()];
      int i = 0;

      for (Integer integer : packList) {
        packs[i++] = integer;
      }

      // This call returns the best combination of packs to be used to serve this order. The
      // requirement is that, to save shipping space, each order should contain minimal number
      // of packs.

      // For example, invocation of findOutPackCombinations(Array[9, 5, 3], 13, "CF") would return
      // (CF~9, 0), (CF~5, 2), (CF~3, 1)
      Map<String, Integer> combinations = findOutPackCombinations(packs, orders.get(order), order);

      // Now calculate sub-total, total bill for the order.
      double sum = 0.0;

      if (combinations.size() > 0) {
        for (String pack : combinations.keySet()) {
          double subTotal = combinations.get(pack) * prices.get(pack);

          System.out.format("Pack type: -%10s, Required packs: %-5d X %5.2f, Subtotal: %8.2f%n"
              , pack, combinations.get(pack), prices.get(pack), subTotal);

          sum += subTotal;
          bill += subTotal;
        }
      } else {
        System.out.println("Sorry, we don't have required count of packs. Check your order!");
      }

      System.out.format("Sub-total: %53s %8.2f%n", " ", sum);
    }
    printSeparator();

    return Double.valueOf(String.format("%1.2f", bill));
  }

  public static void main(String[] args) {
    Properties properties = Utils.loadProperties();

    // Read Prices of pre-packed bunch of buns
    Map<String, Double> prices = readPrices(properties.getProperty("prices-file"));

    System.out.println("Prices of each of the packs");
    for (String item : new TreeSet<>(prices.keySet())) {
      System.out.format("%-10s => $%.2f\n", item,prices.get(item));
    }

    // Orders are stored in a file.
    Map<String, Integer> orders = readOrders(properties.getProperty("orders-file"));

    Double bill = calculateBill(prices, orders);
    System.out.format("Bill to be paid: %47s %8.2f%n", " ", bill);
  }
}