package validations;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import rubix.Bakers;

public class ValidateBill {

  @Test
  public void validateCase1() {
    Map<String, Double> prices = new HashMap<>();
    prices.put("VS5~3", 6.99);
    prices.put("VS5~5", 8.99);

    Map<String, Integer> orders = new HashMap<>();
    orders.put("VS5", 10);

    Double bill = Bakers.calculateBill(prices, orders);
    assertEquals(17.98, bill, 0);
  }

  @Test
  public void validateCase2() {
    Map<String, Double> prices = new HashMap<>();
    prices.put("MB11~2", 9.95);
    prices.put("MB11~5", 16.95);
    prices.put("MB11~8", 24.95);

    Map<String, Integer> orders = new HashMap<>();
    orders.put("MB11", 14);

    Double bill = Bakers.calculateBill(prices, orders);
    assertEquals(54.80, bill, 0);
  }

  @Test
  public void validateCase3() {
    Map<String, Double> prices = new HashMap<>();
    prices.put("CF~3", 5.95);
    prices.put("CF~5", 9.95);
    prices.put("CF~9", 16.99);

    Map<String, Integer> orders = new HashMap<>();
    orders.put("CF", 13);

    Double bill = Bakers.calculateBill(prices, orders);
    assertEquals(25.85, bill, 0);
  }
}