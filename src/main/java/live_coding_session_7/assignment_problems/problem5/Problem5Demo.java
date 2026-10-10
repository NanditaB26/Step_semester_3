package live_coding_session_7.assignment_problems.problem5;

public class Problem5Demo {
    public static String getLocationIfTrackable(Object o) {
        if (o instanceof Trackable) {
            Trackable tracked = (Trackable) o;
            return tracked.getLocation();
        }

        return "Tracking not available";
    }

    public static void main(String[] args) {
        DeliveryDrone d = new DeliveryDrone("DR-1");
        ScoutDrone s = new ScoutDrone("SC-1");
        GroundRobot g = new GroundRobot("GR-1");

        System.out.println(getLocationIfTrackable(d));
        System.out.println(getLocationIfTrackable(s));
        System.out.println(getLocationIfTrackable(g));
    }
}