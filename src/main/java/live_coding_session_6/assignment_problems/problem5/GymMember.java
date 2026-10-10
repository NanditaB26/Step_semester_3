
package live_coding_session_6.assignment_problems.problem5;

public class GymMember {
    private static int counter = 2000;

    public final String membershipNumber;

    private int monthlyFee;
    private int feesPaid;
    private String lastPaymentMode;

    public GymMember(int monthlyFee) {
        if (monthlyFee <= 0) {
            throw new IllegalArgumentException(
                "Monthly fee must be positive"
            );
        }

        this.monthlyFee = monthlyFee;

        counter++;
        membershipNumber = "GYM-" + counter;
    }

    public void payFee(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException(
                "Payment amount must be positive"
            );
        }

        feesPaid += amount;
    }

    public void payFee(int amount, String mode) {
        lastPaymentMode = mode;
        payFee(amount);
    }

    public int getFeesPaid() {
        return feesPaid;
    }

    public String getLastPaymentMode() {
        return lastPaymentMode;
    }

    public static boolean isValidReferralCode(String code) {
        if (code == null || code.length() != 4) {
            return false;
        }

        return code.charAt(0) == 'G'
                && Character.isDigit(code.charAt(1))
                && Character.isDigit(code.charAt(2))
                && Character.isUpperCase(code.charAt(3))
                && code.charAt(3) >= 'A'
                && code.charAt(3) <= 'Z';
    }

    public static int getMembersEnrolled() {
        return counter - 2000;
    }

    public static String processWeeklyCheckIn(GymMember[] members) {
        int processed = 0;
        int nullSkipped = 0;
        int group = 0;
        int individual = 0;

        for (GymMember member : members) {
            if (member == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (member instanceof GroupClassMember) {
                group++;
            } else {
                individual++;
            }
        }

        return processed + " processed | "
                + nullSkipped + " null skipped | "
                + group + " group | "
                + individual + " individual";
    }
}
