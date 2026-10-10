
package live_coding_session_6.assignment_problems.problem2;

public class PremiumMember extends GymMember {
    protected String trainerName;

    public PremiumMember(
            String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);

        if (trainerName == null || trainerName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                "Trainer name cannot be blank"
            );
        }

        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium Member | Trainer: " + trainerName
                + " | Sessions: " + getSessionsAttended();
    }
}
