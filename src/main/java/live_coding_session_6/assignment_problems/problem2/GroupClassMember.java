
package live_coding_session_6.assignment_problems.problem2;

public class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(
            String memberId, int monthlyFee, String className) {
        super(memberId, monthlyFee);

        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Class name cannot be blank"
            );
        }

        this.className = className;
    }

    @Override
    public String displayInfo() {
        return "Group Class Member | Class: " + className
                + " | Sessions: " + getSessionsAttended();
    }
}
