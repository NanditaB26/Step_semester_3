
package live_coding_session_6.assignment_problems.problem4;

public class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(
            String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    public String getTrainerName() {
        return trainerName;
    }

    @Override
    public String displayInfo() {
        return "Premium | Trainer: " + trainerName
                + " | Sessions: " + getSessionsAttended();
    }
}
