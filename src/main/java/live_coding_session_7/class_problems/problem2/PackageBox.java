package live_coding_session_7.class_problems.problem2;

public class PackageBox implements Printable {
    private final String trackingId;

    public PackageBox(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty()) {
            throw new IllegalArgumentException("Tracking ID cannot be blank");
        }
        this.trackingId = trackingId;
    }

    @Override
    public String printLabel() {
        return "Package label: " + trackingId;
    }
}