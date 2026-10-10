public class HotWeatherAlertWindows {

    public static int countAlerts(int[] readings, int k, double threshold) {
        if (k <= 0 || k > readings.length) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < k; i++) {
            sum += readings[i];
        }

        int alerts = 0;

        if ((double) sum / k >= threshold) {
            alerts++;
        }

        for (int i = k; i < readings.length; i++) {
            sum += readings[i];
            sum -= readings[i - k];

            if ((double) sum / k >= threshold) {
                alerts++;
            }
        }

        return alerts;
    }

    public static void main(String[] args) {
        int[] readings = {30, 32, 35, 29, 31, 34};

        System.out.println(countAlerts(readings, 3, 31.0));
    }
}