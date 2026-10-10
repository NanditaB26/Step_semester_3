import java.util.HashMap;
import java.util.Map;

public class MostPopularCanteenOrder {

    public static Object[] mostPopular(String[] orders) {
        Map<String, Integer> count = new HashMap<>();

        for (String order : orders) {
            count.put(order, count.getOrDefault(order, 0) + 1);
        }

        String popularItem = orders[0];
        int maxCount = 0;

        for (String order : orders) {
            int currentCount = count.get(order);

            if (currentCount > maxCount) {
                maxCount = currentCount;
                popularItem = order;
            }
        }

        return new Object[]{popularItem, maxCount};
    }

    public static void main(String[] args) {
        String[] orders = {
            "dosa", "tea", "dosa", "idli", "tea", "dosa"
        };

        Object[] result = mostPopular(orders);
        System.out.println("(" + result[0] + ", " + result[1] + ")");

        String[] orders2 = {"tea", "coffee", "tea", "coffee"};
        Object[] result2 = mostPopular(orders2);
        System.out.println("(" + result2[0] + ", " + result2[1] + ")");
    }
}