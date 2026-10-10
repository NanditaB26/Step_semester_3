
package live_coding_session_5.assignment_problems.problem1;

public class LibraryMember {

    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode,
                         double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }

    public boolean verifyPin(String pin) {
        return membershipPin.equals(pin);
    }
}
