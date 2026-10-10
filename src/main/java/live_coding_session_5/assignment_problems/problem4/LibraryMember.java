
package live_coding_session_5.assignment_problems.problem4;

public class LibraryMember {

    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswerHash;

    public LibraryMember() {
    }

    public String getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(String id) {
        if (membershipId == null && id != null) {
            membershipId = id;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPremiumMember() {
        return premiumMember;
    }

    public void setPremiumMember(boolean premium) {
        this.premiumMember = premium;
    }

    public void setSecurityAnswer(String answer) {
        if (answer == null) {
            securityAnswerHash = null;
        } else {
            securityAnswerHash = Integer.toHexString(answer.hashCode());
        }
    }

    public static void main(String[] args) {
        LibraryMember member = new LibraryMember();

        member.setMembershipId("LIB-8841");
        member.setName("Priya Nair");
        member.setPremiumMember(true);
        member.setMembershipId("FAKE-0000");
        member.setSecurityAnswer("BlueMountain");

        System.out.println(member.getMembershipId());
        System.out.println(member.getName());
        System.out.println(member.isPremiumMember());
    }
}
