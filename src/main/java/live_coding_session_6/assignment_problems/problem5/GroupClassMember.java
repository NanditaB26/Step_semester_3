
package live_coding_session_6.assignment_problems.problem5;

public class GroupClassMember extends GymMember {
    private String className;

    public GroupClassMember(int monthlyFee, String className) {
        super(monthlyFee);

        if (className == null || className.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Class name cannot be blank"
            );
        }

        this.className = className;
    }
}
