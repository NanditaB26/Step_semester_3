package live_coding_session_7.assignment_problems.problem5;

public class ScoutDrone extends Drone {
    public ScoutDrone(String id) {
        super(id);
    }

    @Override
    public String fly() {
        return "Scout drone " + id + " is scouting";
    }
}