
package live_coding_session_6.assignment_problems.problem2;

public class EliteMember extends PremiumMember {
    private String lockerNumber;

    public EliteMember(
            String memberId,
            int monthlyFee,
            String trainerName,
            String lockerNumber) {
        super(memberId, monthlyFee, trainerName);

        if (lockerNumber == null || lockerNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Locker number cannot be blank"
            );
        }

        this.lockerNumber = lockerNumber;
    }

    @Override
    public String displayInfo() {
        return "Elite Member | Trainer: " + trainerName
                + " | Locker: " + lockerNumber
                + " | Sessions: " + getSessionsAttended();
    }
}
