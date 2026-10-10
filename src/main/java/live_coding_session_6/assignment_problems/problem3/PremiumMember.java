
package live_coding_session_6.assignment_problems.problem3;

public class PremiumMember extends GymMember {
    private String trainerName;

    public PremiumMember(
            String memberId, int monthlyFee, String trainerName) {
        super(memberId, monthlyFee);
        this.trainerName = trainerName;
    }

    @Override
    protected void chargeLateFee(int amount) {
        super.chargeLateFee(amount / 2);
    }
}
