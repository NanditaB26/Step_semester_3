
package live_coding_session_6.class_problems.problem5;

public class Problem5Demo {
    public static void main(String[] args) {
        LibraryMember m1 = new LibraryMember(3);

        System.out.println(m1.memberNumber);
        System.out.println(LibraryMember.getMembersEnrolled());

        System.out.println(LibraryMember.isValidRenewalCode("R12A"));
        System.out.println(LibraryMember.isValidRenewalCode("R1A"));
        System.out.println(LibraryMember.isValidRenewalCode("X12A"));

        m1.borrowBook();
        m1.borrowBook("Fiction");
        System.out.println(m1.getBooksBorrowed());

        LibraryMember[] members = {
            new FacultyMember(5, "Physics"),
            null,
            new LibraryMember(3)
        };

        System.out.println(LibraryMember.processNightlyAudit(members));
    }
}
