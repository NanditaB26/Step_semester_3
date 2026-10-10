
package live_coding_session_6.class_problems.problem2;

public class HonorsStudentMember extends StudentMember {
    private int bonusLimit;

    public HonorsStudentMember(
            String memberId, int borrowLimit, String course, int bonusLimit) {
        super(memberId, borrowLimit, course);
        this.bonusLimit = bonusLimit;
    }

    @Override
    public void displayInfo() {
        System.out.println(
            "Honors Student Member | Course: " + course
            + " | Bonus Limit: " + bonusLimit
            + " | Books Borrowed: " + booksBorrowed
        );
    }
}
