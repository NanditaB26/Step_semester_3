
public class FitZoneMembershipDesk {

    interface MembershipPlan {
        String getName();
        int getMonths();
        double getDiscount();

        default double calculateFee() {
            return 1000.0 * getMonths() * (1 - getDiscount());
        }
    }

    static class MonthlyPlan implements MembershipPlan {
        public String getName() { return "Monthly"; }
        public int getMonths() { return 1; }
        public double getDiscount() { return 0; }
    }

    static class QuarterlyPlan implements MembershipPlan {
        public String getName() { return "Quarterly"; }
        public int getMonths() { return 3; }
        public double getDiscount() { return 0.10; }
    }

    static class AnnualPlan implements MembershipPlan {
        public String getName() { return "Annual"; }
        public int getMonths() { return 12; }
        public double getDiscount() { return 0.25; }
    }

    static class Member {
        private final String name;
        private Membership membership;

        Member(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }

        void buyMembership(MembershipPlan plan) {
            membership = new Membership(this, plan);
        }

        void checkIn() {
            if (membership == null) {
                System.out.println("Check-in denied: no membership.");
                return;
            }
            membership.checkIn();
        }

        void freezeMembership() {
            if (membership != null) {
                membership.freeze();
            }
        }

        void unfreezeMembership() {
            if (membership != null) {
                membership.unfreeze();
            }
        }

        void expireMembership() {
            if (membership != null) {
                membership.expire();
            }
        }
    }

    enum Status {
        ACTIVE, FROZEN, EXPIRED
    }

    static class Membership {
        private final Member member;
        private final MembershipPlan plan;
        private Status status = Status.ACTIVE;

        Membership(Member member, MembershipPlan plan) {
            this.member = member;
            this.plan = plan;

            System.out.printf(
                "%s membership created for %s. Fee: ₹%.2f. Status: Active.%n",
                plan.getName(), member.getName(), plan.calculateFee()
            );
        }

        void checkIn() {
            if (status == Status.ACTIVE) {
                System.out.println(member.getName() + " checked in successfully.");
            } else {
                System.out.println(
                    "Check-in denied: " + member.getName()
                    + "'s membership is " + status + "."
                );
            }
        }

        void freeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot freeze an Expired membership.");
            } else if (status == Status.FROZEN) {
                System.out.println("Membership is already Frozen.");
            } else {
                status = Status.FROZEN;
                System.out.println(
                    member.getName() + "'s membership frozen. Status: Frozen."
                );
            }
        }

        void unfreeze() {
            if (status == Status.EXPIRED) {
                System.out.println("Cannot unfreeze an Expired membership.");
            } else if (status == Status.FROZEN) {
                status = Status.ACTIVE;
                System.out.println(
                    member.getName() + "'s membership unfrozen. Status: Active."
                );
            } else {
                System.out.println("Membership is already Active.");
            }
        }

        void expire() {
            if (status == Status.EXPIRED) {
                System.out.println("Membership is already Expired.");
                return;
            }

            status = Status.EXPIRED;
            System.out.println(
                member.getName() + "'s membership expired. Status: Expired."
            );
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        asha.buyMembership(new QuarterlyPlan());
        ravi.buyMembership(new MonthlyPlan());

        asha.checkIn();
        asha.freezeMembership();
        asha.checkIn();

        ravi.expireMembership();
        ravi.freezeMembership();
    }
}
