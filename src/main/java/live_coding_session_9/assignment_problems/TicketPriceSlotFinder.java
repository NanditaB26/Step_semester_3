public class TicketPriceSlotFinder {

    public static int findSlot(int[] prices, int newPrice) {
        int low = 0;
        int high = prices.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (prices[mid] == newPrice) {
                return mid;
            } else if (prices[mid] < newPrice) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return low;
    }

    public static void main(String[] args) {
        int[] prices = {100, 200, 300, 400};

        System.out.println(findSlot(prices, 200));
        System.out.println(findSlot(prices, 350));
        System.out.println(findSlot(prices, 500));
        System.out.println(findSlot(prices, 50));
    }
}