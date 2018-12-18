package rubix;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class Utils {

  public static void printSeparator() {
    for (int i = 1; i <= 80; i++) {
      System.out.print("-");
    }
    System.out.println();
  }

  public static Properties loadProperties() {
    Properties prop = new Properties();

    try {
      prop.load(new FileInputStream("src/main/resources/config.properties"));
    } catch (IOException e) {
      System.out.println("Unable to read config.properties file.");
      e.printStackTrace();
      System.exit(1);
    }

    return prop;
  }

  /**
   * Read Prices Data and store in a Map
   * @param filePath ... Path of the file that stores prices of packages
   * @return ... Returns a Map of item. Each item is a package and its price.
   */
  public static Map<String, Double> readPrices(String filePath) {
    HashMap<String, Double> prices = new HashMap<>();
    Path inPath = Paths.get(filePath);
    List<String> lines = null;

    try {
      lines = Files.readAllLines(inPath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.out.println("Unable to read filePath");
      e.printStackTrace();
      System.exit(1);
    }

    List<String[]> pricesList = lines.stream()
        .map(line -> line.split(","))
        .map(array -> array[1].toUpperCase() + "~" + array[2].split("@")[0].trim() + "," +
            array[2].split("@")[1].replace("$", "").trim())
        .map(line -> line.split(","))
        .collect(Collectors.toList());

    pricesList.forEach(price -> prices.put(price[0], Double.parseDouble(price[1])));

    return prices;
  }

  /**
   * Read Orders from a file.
   * @param filePath ... File containing orders.
   * @return ... A Map
   */
  public static Map<String, Integer> readOrders(String filePath) {
    HashMap<String, Integer> orders = new HashMap<>();
    Path inPath = Paths.get(filePath);
    List<String> lines = null;

    try {
      lines = Files.readAllLines(inPath, StandardCharsets.UTF_8);
    } catch (IOException e) {
      System.out.println("Unable to read " + filePath);
      e.printStackTrace();
      System.exit(1);
    }

    List<String[]> orderList = lines.stream()
        .map(line -> line.split(" "))
        .collect(Collectors.toList());

    orderList.forEach(order -> orders.put(order[0].toUpperCase(), Integer.parseInt(order[1])));

    return orders;
  }

  /**
   * Prints array elements in separate lines.
   *
   * @param a ... Array to be printed
   */
  private static <T> void printArray(T[] a) {
    for (T element : a) {
      System.out.print("[");
      System.out.print(element);
      System.out.println("]");
    }
  }
}