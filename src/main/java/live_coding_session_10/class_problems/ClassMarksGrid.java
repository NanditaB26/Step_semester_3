
import java.util.*;

public class ClassMarksGrid {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<String> names = new ArrayList<>();
        List<int[]> marksList = new ArrayList<>();

        while (sc.hasNextLine()) {
            String line = sc.nextLine().trim();
            if (line.isEmpty()) continue;

            int open = line.indexOf('[');
            int close = line.indexOf(']');

            if (open < 0 || close < 0 || close <= open) continue;

            String name = line.substring(0, open).trim();
            String[] values = line.substring(open + 1, close).split(",");

            if (values.length != 3) continue;

            int[] marks = new int[3];

            for (int i = 0; i < 3; i++) {
                marks[i] = Integer.parseInt(values[i].trim());
            }

            names.add(name);
            marksList.add(marks);
        }

        int[] subjectTotals = new int[3];
        int highestTotal = -1;
        String topper = "";
        int count = names.size();

        StringBuilder totals = new StringBuilder("Totals ");

        for (int i = 0; i < count; i++) {
            int[] marks = marksList.get(i);
            int total = 0;

            for (int j = 0; j < 3; j++) {
                total += marks[j];
                subjectTotals[j] += marks[j];
            }

            if (i > 0) totals.append(", ");
            totals.append(names.get(i)).append(" ").append(total);

            if (total > highestTotal) {
                highestTotal = total;
                topper = names.get(i);
            }
        }

        System.out.println(totals);

        System.out.printf(Locale.US, "averages %.2f, %.2f, %.2f%n",
                count == 0 ? 0.0 : (double) subjectTotals[0] / count,
                count == 0 ? 0.0 : (double) subjectTotals[1] / count,
                count == 0 ? 0.0 : (double) subjectTotals[2] / count);

        if (count > 0) {
            System.out.println("topper " + topper + " (" + highestTotal + ")");
        }

        sc.close();
    }
}
