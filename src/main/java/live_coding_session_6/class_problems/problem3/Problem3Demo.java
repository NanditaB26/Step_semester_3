
package live_coding_session_6.class_problems.problem3;

import java.util.Arrays;

public class Problem3Demo {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU5", 3, "CSE");

        s.chargeFine(100);

        System.out.println(s.getTotalFine());

        int[] history = s.getFineHistory();
        System.out.println(Arrays.toString(history));

        history[0] = 999;

        System.out.println(Arrays.toString(s.getFineHistory()));
    }
}
