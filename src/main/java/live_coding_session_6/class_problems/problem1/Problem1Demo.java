
package live_coding_session_6.class_problems.problem1;

public class Problem1Demo {
    public static void main(String[] args) {
        StudentMember s = new StudentMember("STU10", 3, "CSE");

        s.borrowBook();
        s.borrowBook();
        System.out.println(s.getBooksBorrowed());

        String[] ids = {"STU1", "LB1", "STU2", " ", "STU3"};
        System.out.println(LibraryMember.enrollBatch(ids, 3));
    }
}
