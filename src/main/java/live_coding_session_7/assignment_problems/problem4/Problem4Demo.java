package live_coding_session_7.assignment_problems.problem4;

public class Problem4Demo {
    public static void main(String[] args) {
        Tablet t = new Tablet("TAB-5");

        System.out.println(t.operate());
        System.out.println(t.charge());
        System.out.println(t.charge(30));
    }
}