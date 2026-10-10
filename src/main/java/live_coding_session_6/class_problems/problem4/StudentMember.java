
package live_coding_session_6.class_problems.problem4;

public class StudentMember extends LibraryMember {
    private String course;

    public StudentMember(String memberId, int booksBorrowed, String course) {
        super(memberId, booksBorrowed);
        this.course = course;
    }

    @Override
    public void displayInfo() {
        System.out.print("Student | Books: " + booksBorrowed + " ");
    }

    public String getCourse() {
        return course;
    }
}
