
package live_coding_session_6.class_problems.problem4;

public class Problem4Demo {

    public static String batchPrint(LibraryMember[] members) {
        StringBuilder report = new StringBuilder();

        for (LibraryMember member : members) {
            java.io.ByteArrayOutputStream buffer =
                    new java.io.ByteArrayOutputStream();

            java.io.PrintStream original = System.out;
            System.setOut(new java.io.PrintStream(buffer));

            member.displayInfo();

            System.setOut(original);
            report.append(buffer.toString());

            if (member instanceof StudentMember) {
                StudentMember student = (StudentMember) member;
                report.append("[Course via downcast: ")
                      .append(student.getCourse())
                      .append("] ");
            }

            report.append("| ");
        }

        return report.toString();
    }

    public static void main(String[] args) {
        LibraryMember[] members = {
            new LibraryMember("LB5", 0),
            new StudentMember("STU6", 0, "ECE")
        };

        System.out.println(batchPrint(members));
    }
}
