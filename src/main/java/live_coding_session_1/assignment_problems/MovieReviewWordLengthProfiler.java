package live_coding_session_1.assignment_problems;

import java.util.Scanner;

public class MovieReviewWordLengthProfiler {

    static void classifyWordLengths(String review) {

        String[] words = review.trim().split("\\s+");

        int shortCount = 0;
        int mediumCount = 0;
        int longCount = 0;

        for (int i = 0; i < words.length; i++) {

            String word = words[i].replaceAll("[^a-zA-Z]", "");

            int length = word.length();

            if (length >= 1 && length <= 4) {
                shortCount++;
            } else if (length >= 5 && length <= 8) {
                mediumCount++;
            } else if (length >= 9) {
                longCount++;
            }
        }

        System.out.println(
            "Short: " + shortCount +
            " | Medium: " + mediumCount +
            " | Long: " + longCount
        );
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter movie review: ");
        String review = sc.nextLine();

        if (review.trim().isEmpty()) {
            System.out.println("Review cannot be empty.");
        } else {
            classifyWordLengths(review);
        }

        sc.close();
    }
}