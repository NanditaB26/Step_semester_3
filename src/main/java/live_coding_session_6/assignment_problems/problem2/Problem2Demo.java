
package live_coding_session_6.assignment_problems.problem2;

public class Problem2Demo {

    public static String classifyGeneration(GymMember member) {
        if (member instanceof EliteMember) {
            return "Multilevel descendant (3 generations deep)";
        } else if (member instanceof GroupClassMember) {
            return "Hierarchical sibling (independent branch)";
        } else if (member instanceof PremiumMember) {
            return "Direct premium descendant";
        } else {
            return "Base member";
        }
    }

    public static int getTotalSessionsAttended(GymMember[] members) {
        int total = 0;

        for (GymMember member : members) {
            total += member.getSessionsAttended();
        }

        return total;
    }

    public static void main(String[] args) {
        GymMember standard = new GymMember("MEM1", 1000);
        PremiumMember premium =
                new PremiumMember("MEM2", 2000, "Coach Riya");
        EliteMember elite =
                new EliteMember("MEM3", 3000, "Coach Arjun", "L12");
        GroupClassMember group =
                new GroupClassMember("MEM4", 1500, "Zumba");

        System.out.println(standard.displayInfo());
        System.out.println(premium.displayInfo());
        System.out.println(elite.displayInfo());
        System.out.println(group.displayInfo());

        System.out.println(classifyGeneration(elite));
        System.out.println(classifyGeneration(group));

        for (int i = 0; i < 3; i++) {
            premium.attendSession();
        }

        for (int i = 0; i < 2; i++) {
            elite.attendSession();
        }

        for (int i = 0; i < 4; i++) {
            group.attendSession();
        }

        GymMember[] members = {premium, elite, group};

        System.out.println(getTotalSessionsAttended(members));
    }
}
