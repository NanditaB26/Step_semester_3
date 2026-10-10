
package live_coding_session_6.assignment_problems.problem5;

public class Problem5Demo {
    public static void main(String[] args) {
        GymMember m1 = new GymMember(1000);

        System.out.println(m1.membershipNumber);
        System.out.println(GymMember.getMembersEnrolled());

        System.out.println(
                GymMember.isValidReferralCode("G45B")
        );
        System.out.println(
                GymMember.isValidReferralCode("G4B")
        );
        System.out.println(
                GymMember.isValidReferralCode("X45B")
        );

        m1.payFee(500);
        m1.payFee(500, "UPI");

        System.out.println(m1.getFeesPaid());

        GymMember[] members = {
            new GroupClassMember(1500, "Zumba"),
            null,
            new GymMember(1000)
        };

        System.out.println(
                GymMember.processWeeklyCheckIn(members)
        );
    }
}
