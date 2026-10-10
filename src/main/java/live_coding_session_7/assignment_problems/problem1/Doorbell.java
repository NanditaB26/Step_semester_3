package live_coding_session_7.assignment_problems.problem1;

public class Doorbell implements Ringable {
    private final String location;

    public Doorbell(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be blank");
        }
        this.location = location;
    }

    @Override
    public String ring() {
        return "Doorbell ringing at " + location;
    }
}