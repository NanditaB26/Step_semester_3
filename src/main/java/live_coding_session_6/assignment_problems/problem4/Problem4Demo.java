
package live_coding_session_6.assignment_problems.problem4;

public class Problem4Demo {

    public static String batchPrint(GymMember[] members) {
        StringBuilder announcement = new StringBuilder();

        for (GymMember member : members) {
            announcement.append(member.displayInfo());

            if (member instanceof PremiumMember) {
                PremiumMember premium = (PremiumMember) member;

                announcement.append(" [Trainer via downcast: ")
                        .append(premium.getTrainerName())
                        .append("]");
            }

            announcement.append(" | ");
        }

        return announcement.toString();
    }

    public static void main(String[] args) {
        GymMember[] members = {
            new GymMember("MEM6", 1000),
            new PremiumMember("MEM7", 2000, "Coach Riya")
        };

        System.out.println(batchPrint(members));
    }
}
